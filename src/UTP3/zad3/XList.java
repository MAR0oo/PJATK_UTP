package UTP3.zad3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {


    @SafeVarargs
    public XList(T... elements) {
        super(Arrays.asList(elements));
    }

    public XList(Collection<T> collection) {
        super(collection);
    }
    @SafeVarargs
    public static <T> XList<T> of(T... elements) {
        return new XList<>(elements);
    }
    public static <T> XList<T> of(Collection<T> collection) {
        return new XList<>(collection);
    }

    public static XList<String> charsOf(String string) {
        return new XList<>(string.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList()));
    }

    public static XList<String> tokensOf(String string) {
        return tokensOf(string, "\\s+");
    }

    public static XList<String> tokensOf(String string, String delimiter) {
        return new XList<>(Arrays.asList(string.split(delimiter)));
    }

    public XList<T> union(Collection<T> collection) {
        XList<T> result = new XList<>(this);
        result.addAll(collection);
        return result;
    }
    public XList<T> union(T[] array) {
        XList<T> result = new XList<>(this);
        result.addAll(Arrays.asList(array));
        return result;
    }


    public XList<T> diff(Collection<T> collection) {
        return this.stream().filter(e -> !collection.contains(e)).collect(Collectors.toCollection(XList::new));
    }

    public XList<T> unique() {
        return new XList<>(new LinkedHashSet<>(this));
    }

    public XList<XList<T>> combine() {
        List<List<T>> lists = this.stream().map(e -> (List<T>) e).collect(Collectors.toList());
        XList<XList<T>> result = new XList<>();
        combineHelper(lists, result, 0, new Stack<>());
        return result;
    }

    private void combineHelper(List<List<T>> lists, XList<XList<T>> result, int depth, Stack<T> current) {
        if (depth == lists.size()) {
            result.add(new XList<>(current));
            return;
        }
        for (T t : lists.get(depth)) {
            current.push(t);
            combineHelper(lists, result, depth + 1, current);
            current.pop();
        }
    }

    public <R> XList<R> collect(Function<T, R> function) {
        return this.stream().map(function).collect(Collectors.toCollection(XList::new));
    }

    public String join() {
        return join("");
    }

    public String join(String separator) {
        return this.stream().map(Object::toString).collect(Collectors.joining(separator));
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < size(); i++) {
            consumer.accept(get(i), i);
        }
    }

    @FunctionalInterface
    public interface BiConsumer<T, U> {
        void accept(T t, U u);
    }
}
