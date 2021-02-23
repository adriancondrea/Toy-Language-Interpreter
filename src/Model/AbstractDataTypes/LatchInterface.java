package Model.AbstractDataTypes;

import java.util.Map;

public interface LatchInterface<V> {
    int allocate(V value);
    boolean contains(Integer index);
    V getvalue(Integer index);
    void setValue(Integer index, V newValue);
    Map<Integer, V> getContent();
}
