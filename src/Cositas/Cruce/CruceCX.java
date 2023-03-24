package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CruceCX extends Cruce{
    @Override
    public String toString() {
        return "Cruce CX";
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

            HashMap<Object, Integer> m = new HashMap<Object, Integer>();
            HashSet<Object> v1 = new HashSet<Object>();
            HashSet<Object> v2 = new HashSet<Object>();

            for(int j = 0; j < tamCromosoma; j++)
                m.put(padre1.getCromosoma()[j], j);

            int pos = 0;
            while(!v1.contains(padre1.getCromosoma()[pos])){
                v1.add(padre1.getCromosoma()[pos]);
                v2.add(padre2.getCromosoma()[pos]);
                pos = m.get(padre2.getCromosoma()[pos]);
            }

            for(int j = 0; j < tamCromosoma; j++){
                if(!v1.contains(padre1.getCromosoma()[j]))
                    hijo1.setCromosoma(j, padre2.getCromosoma()[j]);
                if(!v2.contains(padre2.getCromosoma()[j]))
                    hijo2.setCromosoma(j, padre1.getCromosoma()[j]);
            }

            hijos.add(hijo1);
            hijos.add(hijo2);
        }

        return hijos;
    }
}
