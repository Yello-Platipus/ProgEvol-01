package Cositas.Mutacion;

import Cositas.Individuo.Constructores.Constructor;
import Cositas.Individuo.Individuo;
import Util.Tree;

public class MutacionTerminal extends Mutacion{
    @Override
    public String toString() {
        return "Mutacion Terminal";
    }

    @Override
    public void mutar(Individuo ind, double probMutacion) {
        Tree t = ind.getArbol();
        while(!t.esTerminal){
            if(Math.random() < 0.5)
                t = t.left;
            else
                t = t.right;
        }
        if(Math.random() < probMutacion){
            t = new Tree(Constructor.terminales[(int) (Math.random() * Constructor.terminales.length)]);
        }
        ind.updateFitness();
    }
}
