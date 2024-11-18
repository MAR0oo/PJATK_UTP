package UTP3.zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    public static <T> Maybe<T> of(T value) {
        return new Maybe<>(value);
    }

    public void ifPresent(Consumer<? super T> cons) {
        if (value != null) {
            cons.accept(value);
        }
    }

    public <U> Maybe<U> map(Function<? super T, ? extends U> func) {
        if (value == null) {
            return empty();
        } else {
            return Maybe.of(func.apply(value));
        }
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("maybe is empty");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public T orElse(T defVal) {
        return value != null ? value : defVal;
    }

    public Maybe<T> filter(Predicate<? super T> pred) {
        if (value == null || pred.test(value)) {
            return this;
        } else {
            return empty();
        }
    }

    private static <T> Maybe<T> empty() {
        return new Maybe<>(null);
    }

    @Override
    public String toString() {
        return value != null ? "Maybe has value " + value : "Maybe is empty";
    }

}
