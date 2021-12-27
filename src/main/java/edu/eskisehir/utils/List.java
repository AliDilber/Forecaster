package edu.eskisehir.utils;

interface List<T> {
    boolean add(T data);

    boolean remove(T data);

    T get(int index);

    int size();

    void printNodes();
}
