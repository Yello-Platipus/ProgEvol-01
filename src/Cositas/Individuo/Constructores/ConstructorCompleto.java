package Cositas.Individuo.Constructores;

import Util.Tree;

public class ConstructorCompleto extends Constructor{
    @Override
    public void construir(Tree n, int prof) {
        if(prof != maxProf) {
            n.value = funciones[(int) (Math.random() * funciones.length)];
            n.esTerminal = false;
            n.left = new Tree();
            n.right = new Tree();
            construir(n.left, prof + 1);
            construir(n.right, prof + 1);
        }
        else{
            n.value = terminales[(int) (Math.random() * terminales.length)];
            n.esTerminal = true;
        }
    }

    @Override
    public String toString() {
        return "Inicializacion Completa";
    }
}
