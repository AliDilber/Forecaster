package edu.eskisehir.utils;

public class Tree<T> { // our generic m-ary Tree design (LinkedList implementation)
    private final Node<T> root; // root of the tree
    private Node<T> findedNode; // if we're looking for the same node that would be make things easier

    public Tree(Node<T> root) {
        this.root = root;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void findNode(double number) { // to find node
        if (root.getNumber() == number) {
            findedNode = root;
        } else {
            findNode(root, number);
        }
    }

    public void findNode(Node<T> node, double number) { // to find node
        if (node.getNumber() == number) {
            findedNode = node;
            return;
        }

        Node<T> child = node.firstChild;
        while (child != null) {
            findNode(child, number);
            child = child.nextSibling;
        }
    }

    public Node<T> find(double number) { // to find node
        findNode(number);
        return findedNode;
    }

    public void getPreOrder() {
        getPreOrder(root);
    } // basically iterates preordered in tree

    public void getPreOrder(Node baseNode) { // to save all of the nodes once and forget about traversing between them just using the ArrayList
        Node child = baseNode.firstChild;
        while (child != null) {
            getPreOrder(child);
            child = child.nextSibling;
        }
    }
}
