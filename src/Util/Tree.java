package Util;

public class Tree{

    public String value;

    public boolean esTerminal;
    public Tree left;
    public Tree right;

    public Tree(String value) {
        this.value = value;
        right = null;
        left = null;
    }

    public Tree(Tree n){
        this.value = value;
        this.right = new Tree(n.right);
        this.left = new Tree(n.left);
    }

    public Tree() {

    }
}
