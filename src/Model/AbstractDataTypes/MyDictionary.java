package Model.AbstractDataTypes;

import CustomException.CollectionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDictionary<K, V> implements DictionaryInterface<K,V>{
    private final Map<K, V> map;
    //default constructor, initializes map as a HashMap
    public MyDictionary() { map = new HashMap<>();
    }

    @Override
    public void add(K key, V value) throws CollectionException {
        synchronized (map) {
            if (map.containsKey(key))
                throw new CollectionException("Element already exists!");
            map.put(key, value);
        }
    }

    @Override
    public V update(K key, V value) throws CollectionException {
        synchronized (map) {
            if (!map.containsKey(key))
                throw new CollectionException("Key to which we want to update value doesn't exist!");
            return map.put(key, value);
        }
    }

    @Override
    public V lookup(K key)throws CollectionException {
        V value;
        synchronized (map) {
            value = map.get(key);
        }
            if (value == null)
                throw new CollectionException("variable " + key.toString() + " is not defined!\n");
            return value;
    }

    @Override
    public boolean isDefined(K key) {
        synchronized (map) {
            return map.containsKey(key);
        }
    }

    @Override
    public String KeysToString() {
            StringBuilder dictionaryToString = new StringBuilder();
            synchronized (map) {
                for (K key : map.keySet()) {
                    if (key != null) {
                        dictionaryToString.append((key.toString()));
                        dictionaryToString.append('\n');
                    }
                }
            }
            if (dictionaryToString.toString().isEmpty())
                return "EMPTY\n";
            return dictionaryToString.toString();
    }

    @Override
    public V remove(K key) {
        synchronized (map) {
            return map.remove(key);
        }
    }

    @Override
    public Map<K, V> getContent() {
        synchronized (map) {
            return map;
        }
    }

    @Override
    public DictionaryInterface<K, V> deepCopy() {
        synchronized (map) {
            DictionaryInterface<K, V> copy = new MyDictionary<>();
            map.keySet().forEach(e -> copy.add(e, map.get(e)));
            return copy;
        }
    }

    @Override
    public List<V> getValues() {
        synchronized (map) {
            return new ArrayList<>(map.values());
        }
    }

    @Override
    public Map<K, V> getElements() {
        return map;
    }

    @Override
    public String toString() {
        StringBuilder dictionaryToString = new StringBuilder();
        synchronized (map) {
            for (K key : map.keySet()) {
                if (key != null) {
                    dictionaryToString.append((key.toString()));
                    dictionaryToString.append("->");
                    dictionaryToString.append(map.get(key).toString());
                    dictionaryToString.append('\n');
                }
            }
        }
        if(dictionaryToString.toString().isEmpty())
            return "EMPTY\n";
        return dictionaryToString.toString();
        }
}
