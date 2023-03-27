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
            boolean roto = false;
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();

            // Creo el mapa de conexiones y lo inicializo
            HashMap<Object, HashSet<Object>> conexiones = new HashMap<>();
            for(int j = 0; j <= tamCromosoma; j++){
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
                ArrayList<Object> aceptables1 = new ArrayList<>();
                ArrayList<Object> aceptables2 = new ArrayList<>();

                int minConexiones = Integer.MAX_VALUE;
                for(Object o : conexiones.get(hijo1.getCromosoma()[pos - 1])){
                    if(!v1.contains(o)) {
                        if (conexiones.get(o).size() < minConexiones) {
                            aceptables1 = new ArrayList<>();
                            aceptables1.add(o);
                            minConexiones = conexiones.get(o).size();
                        } else if (conexiones.get(o).size() == minConexiones)
                            aceptables1.add(o);
                    }
                }
                //TODO EL PROBLEMA ES QUE PUEDE LLEGAR A UN PUNTO EN EL QUE ACEPTABLES NO TENGA ELEMENTOS
                if(aceptables1.size() < 1){
                    for(Object o : padre1.getCromosoma())
                        if (!v1.contains(o)) {
                            aceptables1.add(o);
                        }
                }

                minConexiones = Integer.MAX_VALUE;

                for(Object o : conexiones.get(hijo2.getCromosoma()[pos - 1])){
                    if(!v2.contains(o)) {
                        if (conexiones.get(o).size() < minConexiones) {
                            aceptables2 = new ArrayList<>();
                            aceptables2.add(o);
                            minConexiones = conexiones.get(o).size();
                        } else if (conexiones.get(o).size() == minConexiones)
                            aceptables2.add(o);
                    }
                }
                if(aceptables2.size() < 1){
                    for(Object o : padre2.getCromosoma())
                        if (!v2.contains(o)) {
                            aceptables2.add(o);
                        }
                }

                Object obj = aceptables1.get((int) Math.random() * aceptables1.size());
                v1.add(obj);
                hijo1.setCromosoma(pos, obj);

                obj = aceptables2.get((int) Math.random() * aceptables2.size());
                v2.add(obj);
                hijo2.setCromosoma(pos, obj);
            }

            hijos.add(hijo1);
            hijos.add(hijo2);
        }

        return hijos;
    }

    // Ignorar, probablemente sobra
    private int conexionesValidas(HashMap<Object, HashSet<Object>> conexiones, Object index, HashSet<Object> visitados){
        int i = 0;
        for(Object o : conexiones.get(index)){
            if(!visitados.contains(o))
                i++;
        }
        return i;
    }
}
