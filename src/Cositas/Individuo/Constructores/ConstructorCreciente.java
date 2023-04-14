package Cositas.Individuo.Constructores;

import Util.Node;

public class ConstructorCreciente extends Constructor {
    @Override
    public void construir(Node n, int prof) {
        if(prof != maxProf) {
            if(Math.random() < 0.5)
                n = new Node(funciones[(int) (Math.random() * funciones.length)]);
            else
                n = new Node(terminales[(int) (Math.random() * terminales.length)]);
            construir(n.left, prof + 1);
            construir(n.right, prof + 1);
        }
        else{
            n = new Node(terminales[(int) (Math.random() * terminales.length)]);
        }
    }

    @Override
    public String toString() {
        return "Inicializacion Creciente";
    }
}
