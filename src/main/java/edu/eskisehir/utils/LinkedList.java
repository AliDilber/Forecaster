package edu.eskisehir.utils;

import java.io.Serializable;

public class LinkedList<T> implements List<T>, Serializable {

    private class Node implements Serializable {
        private T data;
        private Node next;

        public Node(T data) {
            super();
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }
    }

    private Node head;
    private Node tail;
    private int nodeCounter;


    @Override
    public boolean add(T data) {
        boolean addFlag = false;

        //Creating new node with given data
        Node node = new Node(data);
        //Linked list is empty, adding first element
        if (this.head == null) {
            head = node;
            //Head and tail pointing to the same node
            tail = head;
            nodeCounter++;
            addFlag = true;
        } else {
            //Linked list is not empty, adding new element to the list
            tail.setNext(node);
            tail = node;
            nodeCounter++;
            addFlag = true;
        }
        return addFlag;
    }

    @Override
    public boolean remove(T data) {

        boolean removeFlag = false;
        //Checking whether list is empty or not
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        //Deleting head element
        if (head.getData().equals(data)) {
            Node temp = head;
            head = temp.getNext();
            temp = null;
            this.nodeCounter--;
            removeFlag = true;
        } else {
            Node temp = head.getNext();
            Node prev = head;
            // Iterating over the list
            while (temp != null) {
                // Checking the data for node deletion
                if (temp.getData().equals(data)) {
                    prev.setNext(temp.getNext());
                    temp = null;
                    this.nodeCounter--;
                    removeFlag = true;
                    break;
                }
                prev = temp;
                temp = temp.getNext();
            }
        }
        return removeFlag;
    }

    public boolean remove(int index) {
        Node temp = head;
        Node prev = head;
        int cntr = 0;

        if (head == null)
            throw new IndexOutOfBoundsException("List is empty");
        if (index >= this.nodeCounter)
            throw new IndexOutOfBoundsException("Index greater than list size");
        //If given index is of head node then return the data
        if (index == cntr) {
            this.head = head.getNext();
            nodeCounter--;
            return true;
        }
        else {
            //Checking the given index with the node index
            while (temp != null) {
                if (temp == tail) {
                    prev.setNext(null);
                    this.tail = prev;
                    nodeCounter--;
                    return true;
                }
                else if (cntr == index) {
                    prev.setNext(temp.getNext());
                    nodeCounter--;
                    return true;
                }
                prev = temp;
                temp = temp.getNext();
                cntr++;
            }
        }
        return false;
    }

    public void set(int index, T value) {
        Node temp = head;
        int cntr = 0;

        if (head == null)
            throw new IndexOutOfBoundsException("List is empty");
        if (index >= this.nodeCounter)
            throw new IndexOutOfBoundsException("Index greater than list size");
        //If given index is of head node then return the data
        if (index == cntr) {
            head.data = value;
            return;
        }
        else if (index == (nodeCounter - 1)) {
            tail.data = value;
            return;
        }
        else {
            //Checking the given index with the node index
            while (temp != null) {
                if (cntr == index) {
                    temp.data = value;
                    return;
                }
                temp = temp.getNext();
                cntr++;
            }
        }
    }

    @Override
    public void printNodes() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }

    @Override
    public int size() {
        return this.nodeCounter;
    }

    @Override
    public T get(int index) {
        T data = null;
        Node temp = head;
        int cntr = 0;

        if (head == null)
            throw new IndexOutOfBoundsException("List is empty");
        if (index >= this.nodeCounter)
            throw new IndexOutOfBoundsException("Index greater than list size");
        //If given index is of head node then return the data
        if (index == cntr)
            data = head.getData();
            //If given index is of tail node then return data
        else if (index == (nodeCounter - 1))
            data = tail.getData();
        else {
            //Checking the given index with the node index
            while (temp != null) {
                if (cntr == index) {
                    data = temp.getData();
                    break;
                }
                temp = temp.getNext();
                cntr++;
            }
        }
        return data;
    }

}