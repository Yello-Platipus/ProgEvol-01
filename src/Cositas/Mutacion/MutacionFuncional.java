package Cositas.Mutacion;

import Cositas.Individuo.Constructores.Constructor;
import Cositas.Individuo.Individuo;
import Util.Tree;

public class MutacionFuncional extends Mutacion{
    @Override
    public String toString() {
        return "Mutacion Funcional";
    }

    @Override
    public void mutar(Individuo ind, double probMutacion) {
        Tree t = ind.getArbol();
        while(!t.esTerminal){
            if(Math.random() < probMutacion){
                t = new Tree(Constructor.funciones[(int) (Math.random() * Constructor.funciones.length)]);

                break;
            }
            if(Math.random() < 0.5)
                t = t.left;
            else
                t = t.right;
        }
    }
}

