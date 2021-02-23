package Model.AbstractDataTypes;

import CustomException.CollectionException;

import java.util.Deque;

public interface StackInterface<T> {
    T pop() throws CollectionException;
    void push(T element);
    boolean isEmpty() throws CollectionException;
    T peek();
    public Deque<T> getContent();
}
