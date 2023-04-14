package Util;

public class Node {

    public String value;

    public boolean esTerminal;
    public Node left;
    public Node right;

    public Node(String value) {
        this.value = value;
        right = null;
        left = null;
    }

    public Node(Node n){
        this.value = value;
        this.right = new Node(n.right);
        this.left = new Node(n.left);
    }
}