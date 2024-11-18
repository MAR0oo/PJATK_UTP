/**
 *
 *  @author Osiak Marek S24671
 *
 */

package UTP1.zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator <T,K> {
    public List<T> list;


    public static <T,K> ListCreator<T,K> collectFrom(List<T> l){
        ListCreator<T,K> collection = new ListCreator<>();
        collection.list = l;
        return collection;
    }
    public ListCreator<T,K> when(Selector<T> x){
        List<T> tmpList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            if (x.select(element)){
                tmpList.add(element);
            }
        }
        list = tmpList;
        return this;
    }
    public List<K> mapEvery(Mapper<T,K> mapper){
        List<K> listReturn = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            K elementMap = mapper.map(element);
            listReturn.add(elementMap);
        }
        return listReturn;
    }
}  
