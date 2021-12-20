package edu.eskisehir.utils;

public class Node<T> { // our generic Node design

    public Node<T> nextSibling; // access modifiers both nextSibling and firstChild are set public to make things easier
    public Node<T> firstChild;
    double number;

    public Node(double number) {
        this.firstChild = null;
        this.nextSibling = null;
        this.number = number;
    }

    public boolean hasChild() {
        return firstChild != null;
    } // to check Node has child

    public String toString() {
        return String.format("%s", this.number);
    } // used in Interface

    public void addChild(Node<T> n) { // we are using this when we are defining relationships between nodes and adding nodes into Tree
        if (!this.hasChild()) {
            this.firstChild = n;
            return;
        }
        Node<T> sibling = this.firstChild.nextSibling;
        Node<T> prevSibling = this.firstChild;
        if (sibling == null) {
            prevSibling.nextSibling = n;
            return;
        }
        while (sibling != null) {
            prevSibling = prevSibling.nextSibling;
            sibling = sibling.nextSibling;
        }
        prevSibling.nextSibling = n;
    }

    public double getNumber() {
        return this.number;
    } // used in Interface

}