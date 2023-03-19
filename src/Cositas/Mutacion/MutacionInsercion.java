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
        int nMutaciones = (int) (tamCromosoma * probMutacion);
        for(int i = 0; i < nMutaciones; i++){
            int pos1 = (int) (Math.random() * tamCromosoma);
            int pos2 = (int) (Math.random() * tamCromosoma);
            Object aux = ind.getCromosoma()[pos1];
            for(int j = pos1; j < pos2; j++){
                ind.getCromosoma()[j] = ind.getCromosoma()[j+1];
            }
            ind.getCromosoma()[pos2] = aux;
        }
    }
}
