package Cositas.Mutacion;

import Cositas.Individuo.Constructores.ConstructorCreciente;
import Cositas.Individuo.Individuo;
import Util.Tree;

public class MutacionArbolSubarbol extends Mutacion{

    @Override
    public String toString() {
        return "Mutacion de Arbol-Subarbol";
    }

    @Override
    public void mutar(Individuo ind, double probMutacion) {
        Tree t = ind.getArbol();
        Double random = Math.random();
        if(random < 0.5)
            t = t.left;
        else
            t = t.right;
        int prof = 2;
        while(!t.esTerminal){
            random = Math.random();
            if(random < 0.3333333333)
                break;
            else if(random < 0.6666666666)
                t = t.left;
            else
                t = t.right;
            prof++;
        }
        if(Math.random() < probMutacion)
            new ConstructorCreciente().construir(t, prof);
    }
}
