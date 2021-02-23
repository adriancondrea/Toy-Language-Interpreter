package Model.AbstractDataTypes;

import CustomException.CollectionException;

import java.util.LinkedList;
import java.util.List;

public class MyList<T> implements ListInterface<T>{
    private final LinkedList<T> list;

    //default constructor
    public MyList() {list = new LinkedList<>(); }

    public MyList(List<T> list) {
        this.list = new LinkedList<>();
        this.list.addAll(list);
    }
    @Override
    public void add(T element) throws CollectionException {
        synchronized (list) {
            list.add(element);
        }
    }

    @Override
    public T pop() throws CollectionException {
        synchronized (list) {
            if (list.isEmpty())
                throw new CollectionException("list is empty!");
            return list.pop();
        }
    }

    @Override
    public List<T> getList() {
        synchronized (list) {
            return list;
        }
    }

    @Override
    public List<T> getElements() {
        return list;
    }

    @Override
    public String toString() {
        //StringBuilder - mutable sequence of characters, because strings are immutable in Java.
        StringBuilder listToString = new StringBuilder();
        synchronized (list) {
            for (T element : list) {
                if (element != null) {
                    listToString.append(element.toString());
                    listToString.append('\n');
                }
            }
        }
        if(listToString.toString().isEmpty())
            return "EMPTY\n";
        //convert the stringBuilder to string and return it
        return  listToString.toString();
        }
}
