package Model.AbstractDataTypes;

import java.util.Map;

public interface HeapInterface<V> {
    int allocate(V value);
    V getValue(int address);
    void putValueAtAddress(int address, V value);
    V deallocateAddress(int address);
    Map<Integer, V> getContent();
    void setContent(Map<Integer, V> heap);
    boolean allocatedAddress(int address);
}
