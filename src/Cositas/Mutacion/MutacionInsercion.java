package Cositas.Mutacion;

import Cositas.Individuo.Individuo;

public class MutacionInsercion extends Mutacion{
    @Override
    public String toString() {
        return "Mutacion por insercion";
    }

    @Override
    public void mutar(Individuo ind, double probMutacion) {
        int tamCromosoma = ind.getCromosoma().length;
        if(Math.random() < probMutacion) {
            int pos1 = (int) (Math.random() * tamCromosoma);
            int pos2 = (int) (Math.random() * tamCromosoma);
            if(pos1 > pos2){
                int aux = pos1;
                pos1 = pos2;
                pos2 = aux;
            }

            Object aux = ind.getCromosoma()[pos1];

            for (int j = pos1; j < pos2; j++) {

                ind.setCromosoma(j,ind.getCromosoma()[j+1]);
            }
            ind.setCromosoma(pos2, aux);
        }
    }
}
