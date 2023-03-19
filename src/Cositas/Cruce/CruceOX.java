package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;

public class CruceOX extends Cruce{
    @Override
    public String toString() {
        return "Cruce OX";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList<Individuo> hijos = new ArrayList<>();
        int tamPoblacion = poblacion.size();
        int tamCromosoma = poblacion.get(0).getCromosoma().length;
        for(int i = 0; i < tamPoblacion-1; i += 2) {
            int pt1 = (int) (Math.random() * tamCromosoma), pt2 = (int) (Math.random() * tamCromosoma);
            while (pt1 == pt2) // Si los puntos son iguales, regenero el segundo punto
                pt2 = (int) (Math.random() * tamCromosoma);
            if (pt1 > pt2) { // Si el punto 1 es mayor que el punto 2, los intercambio
                int aux = pt1;
                pt1 = pt2;
                pt2 = aux;
            }
            boolean v1[] = new boolean[tamCromosoma], v2[] = new boolean[tamCromosoma];
            // He pensado en hacerlo con un mapa, pero no lo tengo claro
            for (int j = 0; j < tamCromosoma; j++) { // Inicializo los "visitados" a false
                v1[j] = false;
                v2[j] = false;
            }
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();
            for (int j = pt1; j < pt2; j++) { // Copio los elementos entre pt1 y pt2 a los cromosomas de los hijos
                hijo1.getCromosoma()[j] = padre2.getCromosoma()[j];
                v1[(int) hijo1.getCromosoma()[j]] = true;
                hijo2.getCromosoma()[j] = padre1.getCromosoma()[j];
                v2[(int) hijo2.getCromosoma()[j]] = true;
            }
            // Copio los elementos del array que no esten repetidos CICLICAMENTE(desde pt2, hasta el final, y desde el principio hasta pt1)
            int i1 = pt2, i2 = pt2;
            for(int j = pt2; j != pt1 && (i1 != pt1 || i2 != pt1); j++){
                j %= tamCromosoma;
                if(!v1[(int) padre1.getCromosoma()[j]]){ // TODO Si se usa con otro que no sea el TSP petara
                    hijo1.getCromosoma()[i1] = padre1.getCromosoma()[j];
                    v1[(int) hijo1.getCromosoma()[i1]] = true;
                    i1++;
                    i1 %= tamCromosoma;
                }
                if(!v2[(int) padre2.getCromosoma()[j]]){ // TODO Si se usa con otro que no sea el TSP petara
                    hijo2.getCromosoma()[i2] = padre2.getCromosoma()[j];
                    v2[(int) hijo2.getCromosoma()[i2]] = true;
                    i2++;
                    i2 %= tamCromosoma;
                }
            }
            hijos.add(hijo1);
            hijos.add(hijo2);
        }
        return hijos;
    }
}
