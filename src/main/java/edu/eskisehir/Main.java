package edu.eskisehir;

import edu.eskisehir.utils.Tree;
import edu.eskisehir.utils.Value;


public class Main {
    public static void main(String[] args) {
        Value value = new Value(50.5);
        Value value1 = new Value(40.5);
        Tree<Value> tree = new Tree<>(value);
        tree.find(value.getNumber()).addChild(value1);
        System.out.println(tree.find(value.getNumber()).firstChild);


    }

}