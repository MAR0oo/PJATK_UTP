package UTP3.zad1;

import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {
    private T data;

    public InputConverter(T data) {
        this.data = data;
    }

    @SafeVarargs
    public final <R> R convertBy(Function... functions) {
        Object result = data;
        for (Function function : functions) {
            result = function.apply(result);
        }
        return (R) result;
    }


}

