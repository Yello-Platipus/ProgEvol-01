package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

            // Creo el mapa de conexiones y lo inicializo
            HashMap<Object, HashSet<Object>> conexiones = new HashMap<>();
            for(int j = 0; j < tamCromosoma; j++){
                conexiones.put(j, new HashSet<>());
            }
            // Relleno el mapa de conexiones
            conexiones.get(padre1.getCromosoma()[0]).add(padre1.getCromosoma()[tamCromosoma - 1]);
            conexiones.get(padre1.getCromosoma()[0]).add(padre1.getCromosoma()[1]);
            conexiones.get(padre2.getCromosoma()[0]).add(padre2.getCromosoma()[tamCromosoma - 1]);
            conexiones.get(padre2.getCromosoma()[0]).add(padre2.getCromosoma()[1]);
            for(int j = 1; j < tamCromosoma; j++){
                conexiones.get(padre1.getCromosoma()[j]).add(padre1.getCromosoma()[j - 1]);
                conexiones.get(padre2.getCromosoma()[j]).add(padre2.getCromosoma()[j - 1]);
                conexiones.get(padre1.getCromosoma()[j]).add(padre1.getCromosoma()[(j + 1) % tamCromosoma]);
                conexiones.get(padre2.getCromosoma()[j]).add(padre2.getCromosoma()[(j + 1) % tamCromosoma]);
            }

            // Tomo el primer elemento del padre 1 como primer elemento del hijo 1 y del padre 2 como primer elemento del hijo 2
            HashSet<Object> v1 = new HashSet<>();
            HashSet<Object> v2 = new HashSet<>();
            v1.add(padre1.getCromosoma()[0]);
            v2.add(padre2.getCromosoma()[0]);
            for(int pos = 1; pos < tamCromosoma; pos++){
                ArrayList<Object> aceptables = new ArrayList<>();
                int minConexiones = Integer.MAX_VALUE;
                for(Object o : conexiones.get(hijo1.getCromosoma()[pos - 1])){
                    if(!v1.contains(o)) {
                        if (conexiones.get(o).size() < minConexiones) {
                            aceptables = new ArrayList<>();
                            aceptables.add(o);
                            minConexiones = conexiones.get(o).size();
                        } else if (conexiones.get(o).size() == minConexiones)
                            aceptables.add(o);
                    }
                }
                Object o = aceptables.get((int) Math.random() * aceptables.size());
            }

            hijos.add(hijo1);
            hijos.add(hijo2);
        }

        return hijos;
    }
}
