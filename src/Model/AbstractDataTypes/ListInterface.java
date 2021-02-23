package Model.AbstractDataTypes;

import CustomException.CollectionException;

import java.util.List;

public interface ListInterface<T> {
    void add(T element) throws CollectionException;
    T pop() throws CollectionException;
    List<T> getList();

    public List<T> getElements();
}
