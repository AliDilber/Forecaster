package edu.eskisehir.utils;

import java.io.Serializable;

class Node<T> implements Serializable {

    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
