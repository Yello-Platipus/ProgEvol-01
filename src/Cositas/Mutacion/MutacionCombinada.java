package Cositas.Mutacion;

import Cositas.Individuo.Individuo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MutacionCombinada extends Mutacion{
    @Override
    public String toString() {
        return "Mutacion combinada";
    }

    @Override
    public void mutar(Individuo ind, double probMutacion) {
        if(Math.random() < probMutacion){
            ArrayList<Individuo> lista = new ArrayList<Individuo>();
            for(int i = 0; i < 3; i++){
                Individuo aux = ind.clonar();
                lista.add(aux);
            }
            new MutacionInsercion().mutar(lista.get(0), 1);
            new MutacionIntercambio().mutar(lista.get(1), 1);
            new MutacionInversion().mutar(lista.get(2), 1);
            Collections.sort(lista);
            for(int i = 0; i < ind.getCromosoma().length; i++){
                ind.setCromosoma(i, lista.get(0).getCromosoma()[i]);
            }
        }
    }
}
