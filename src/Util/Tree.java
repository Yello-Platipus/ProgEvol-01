package Util;

public class Tree{

    public String value;
    public boolean esTerminal;
    public Tree left;
    public Tree right;

    public Tree(){
        value = "";
        right = null;
        left = null;
    }
    public Tree(String value) {
        this.value = value;
        right = new Tree();
        left = new Tree();
    }

    public Tree(Tree n){
        this.value = n.value;
        this.esTerminal = n.esTerminal;
        if(!n.esTerminal) {
            this.right = new Tree(n.right);
            this.left = new Tree(n.left);
        }
    }
    public void setTree(Tree nuevo){        //Cambiar un subarbol por otro
        this.value = nuevo.value;
        this.esTerminal = nuevo.esTerminal;

        if(this.right != null && nuevo.right != null)
            this.right.setTree(nuevo.right);
        else if(nuevo.right != null)
            this.right = new Tree(nuevo.right);
        else
            this.right = null;

        if(this.left != null && nuevo.left != null)
            this.left.setTree(nuevo.left);
        else if(nuevo.left != null)
            this.left = new Tree(nuevo.left);
        else
            this.left = null;

    }

    public int getSize(){
        if (esTerminal)
            return 1;
        return 1 + left.getSize() + right.getSize();
    }
    public int getProf(){
        if(esTerminal)
            return 1;
        return 1 + Math.max(left.getProf(), right.getProf());
    }

    public String toString(){
        if(esTerminal)
            return value;
        switch (value){
            case "add":
                return "(" + left.toString() + " + " + right.toString() + ")";
            case "sub":
                return "(" + left.toString() + " - " + right.toString() + ")";
            case "mul":
                return "(" + left.toString() + " * " + right.toString() + ")";
            default:
                return "Error";
        }
    }
}