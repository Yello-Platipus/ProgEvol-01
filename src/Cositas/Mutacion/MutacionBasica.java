package Cositas.Mutacion;

import Cositas.Individuo.*;
public class MutacionBasica extends Mutacion{

    @Override
    public String toString() {
        return "Mutacion basica";
    }

    public void mutar(Individuo ind, double probMutacion){
        boolean cambios = false;
        Object cromosoma[] = ind.getCromosoma();
        for(int i = 0; i < cromosoma.length; i++){
            if(Math.random() < probMutacion){
                cromosoma[i] = ind.nextRandom();
                cambios = true;
            }
        }
        if(cambios){
            //TODO refrescar fenotipo
        }
    }
}
