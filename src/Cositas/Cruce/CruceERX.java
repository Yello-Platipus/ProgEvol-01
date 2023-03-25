package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CruceERX extends Cruce{
    @Override
    public String toString() {
        return "Cruce ERX";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList<Individuo> hijos = new ArrayList<>();
        int tamPoblacion = poblacion.size();
        int tamCromosoma = poblacion.get(0).getCromosoma().length;

        for(int i = 0; i < tamPoblacion - 1; i += 2){
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();



            hijos.add(hijo1);
            hijos.add(hijo2);
        }

        return hijos;
    }
}
