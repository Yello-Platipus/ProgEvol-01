package Cositas.Mutacion;

import Cositas.Individuo.Individuo;

public class MutacionIntercambio extends Mutacion{
    @Override
    public String toString() {
        return "Mutacion por intercambio";
    }

    @Override
    public void mutar(Individuo ind, double probMutacion) {
        if(Math.random() < probMutacion){
            int pos1 = (int)(Math.random() * ind.getCromosoma().length);
            int pos2 = (int)(Math.random() * ind.getCromosoma().length);
            Object aux1 = ind.getCromosoma()[pos1];
            Object aux2 = ind.getCromosoma()[pos2];
            ind.setCromosoma(pos1, aux1);
            ind.setCromosoma(pos2, aux2);
        }
    }
}
