package Util;

public class Node {

    String value;
    public Node left;
    public Node right;

    public Node(String value) {
        this.value = value;
        right = null;
        left = null;
    }
}