package UTP2.zad1;


import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class ListCreator <T,K>{

    public List<T> list;


    public static <T,K> ListCreator<T,K> collectFrom(List<T> l){
        ListCreator<T,K> collection = new ListCreator<>();
        collection.list = l;
        return collection;
    }

    public ListCreator<T,K> when(Predicate<T> x){
        List<T> tmpList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            if (x.test(element)){
                tmpList.add(element);
            }
        }
        list = tmpList;
        return this;
    }

    public <K> List<K> mapEvery(Function<T,K> function) {
        List<K> mappedList = new ArrayList<>();
        for (T element : list) {
            K mappedElement = function.apply(element);
            mappedList.add(mappedElement);
        }
        return mappedList;
    }
}
