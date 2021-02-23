package Model.AbstractDataTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLatch<V> implements LatchInterface<V>{
    private final AtomicInteger latchIndex;
    private final Map<Integer, V> latchTable;

    public MyLatch(){
        latchIndex = new AtomicInteger(0);
        latchTable = new HashMap<>();
    }

    @Override
    public synchronized int allocate(V value) {
        latchTable.put(latchIndex.incrementAndGet(), value);
        return latchIndex.get();
    }

    @Override
    public synchronized boolean contains(Integer index) {
        return latchTable.containsKey(index);
    }

    @Override
    public synchronized V getvalue(Integer index) {
        return latchTable.get(index);
    }

    @Override
    public synchronized void setValue(Integer index, V newValue) {
        latchTable.put(index, newValue);
    }

    @Override
    public synchronized Map<Integer, V> getContent() {
        return latchTable;
    }

    @Override
    public String toString() {
        StringBuilder stringValue = new StringBuilder();
        for(Integer key: latchTable.keySet()) {
            if(key != null)
                stringValue.append(key.toString()).append(" -> ").append(latchTable.get(key)).append('\n');
        }
        return stringValue.toString();
    }
}
