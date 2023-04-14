package Cositas.Individuo.Constructores;

import Util.Tree;

public class ConstructorCompleto extends Constructor{
    @Override
    public void construir(Tree n, int prof) {
        if(prof != maxProf) {
            n = new Tree(funciones[(int) (Math.random() * funciones.length)]);
            n.esTerminal = false;
            construir(n.left, prof + 1);
            construir(n.right, prof + 1);
        }
        else{
            n = new Tree(terminales[(int) (Math.random() * terminales.length)]);
            n.esTerminal = true;
        }
    }

    @Override
    public String toString() {
        return "Inicializacion Completa";
    }
}
