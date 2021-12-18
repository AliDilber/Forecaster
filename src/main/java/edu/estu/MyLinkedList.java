package edu.estu;

public class MyLinkedList {

    Node head;
    Node tail;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;

    }


    public boolean isEmpty() {

        return head == null;
    }


    public void insert(String name) {

        Node newNode = new Node(name);

        newNode.next = null;

        if (isEmpty()) {
            head = tail = newNode;
            head.previous = null;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;

        }
        tail.next = null;
    }


    public void insert(String name, int pos) {

        Node newNode = new Node(name);

        if (isEmpty()) {
            head = newNode;
        } else if (pos == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            if (pos >= 1) {
                Node temp = head;
                for (int i = 1; i < pos; i++) {
                    if (temp != null) {
                        temp = temp.next;
                    }
                }
                if (temp != null) {
                    newNode.next = temp.next;
                    newNode.previous = temp;
                    temp.next = newNode;
                    if (newNode.next != null) {
                        newNode.next.previous = newNode;
                    }
                }
            }
        }
    }


    public int size() {

        Node temp = head;
        int size = 0;
        while (temp != null) {
            size += 1;
            temp = temp.next;
        }
        return size;

    }


    public Node deleteByName(String name) {

        Node temp = tail;
        Node current;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            current = head;
            while (current.getName() != name) {
                current = current.next;
            }
            Node deletedNode = current;
            current.previous.next = deletedNode.next;
            return deletedNode;
        }

        return temp;
    }


    public Node deleteByPosition(int pos) {

        Node temp1 = new Node(null);
        Node temp2 = new Node(null);
        Node temp = new Node(null);
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            if (pos == 0) {
                temp = head;
                head = head.next;
                if (head != null)
                    head.previous = null;
                return temp;
            } else {
                temp1 = head;
                for (int i = 0; i < pos - 1; i++) {
                    if (temp1 != null) {
                        temp1 = temp1.next;
                    }
                }
                if (temp1 != null && temp1.next != null) {
                    temp2 = temp1.next;
                    temp1.next = temp1.next.next;
                    if (temp1.next.next != null)
                        temp1.next.next.previous = temp1.next;
                    temp2 = null;
                }
            }
        }
        return temp1.next.previous;
    }


    public Node getByPosition(int pos) {
        if (this.isEmpty())
            return new Node("empty");
        Node current = this.head;
        int index = 0;

        while (current != null) {
            if (index++ == pos)
                break;
            current = current.next;
        }

        if (current == null)
            return new Node("empty");

        return current;
    }


    public void print() {

        if (this.isEmpty())
            return;
        Node node = this.head;
        int index = 1;


        while (node != null) {
            System.out.println(index++ + ". " + node.getName());

            node = node.next;
        }
    }
}

