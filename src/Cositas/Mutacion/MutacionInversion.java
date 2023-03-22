package Cositas.Mutacion;

import Cositas.Individuo.Individuo;

public class MutacionInversion extends Mutacion{
    @Override
    public String toString() {
        return "Mutacion por inversion";
    }

    @Override
    public void mutar(Individuo ind, double probMutacion) {
        if(Math.random() < probMutacion){
            int pos1 = (int)(Math.random() * ind.getCromosoma().length);
            int pos2 = (int)(Math.random() * ind.getCromosoma().length);
            if(pos1 > pos2){
                int aux = pos1;
                pos1 = pos2;
                pos2 = aux;
            }

            Object aux[] = ind.getCromosoma().clone();

            int j = pos2-1;
            for(int i = pos1; i < pos2; i++){
                aux[i] = ind.getCromosoma()[j];
                j--;
            }
            for(int i = pos1; i < pos2; i++){
                ind.setCromosoma(i, aux[i]);
            }
        }
    }
}
