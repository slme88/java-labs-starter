package edu.course.lab06;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Stack<T> {
    private final List<T> storage = new ArrayList<>();

    public void push(T value) {
        Objects.requireNonNull(value, "Null values are not allowed in Stack");
        storage.add(value);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop from an empty stack");
        }
        return storage.remove(storage.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek into an empty stack");
        }
        return storage.get(storage.size() - 1);
    }

    public int size() {
        return storage.size();
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }
}