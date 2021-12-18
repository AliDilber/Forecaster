package edu.estu;

public class Node {
    private String name;
    Node next;
    Node previous;


    public Node(String n) {
        this.name = n;
        this.next = null;
        this.previous = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
