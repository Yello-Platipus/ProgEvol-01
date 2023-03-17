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
            int pt1 = (int) (Math.random() * tamPoblacion), pt2 = (int) (Math.random() * tamPoblacion);
            while (pt1 == pt2) // Si los puntos son iguales, regenero el segundo punto
                pt2 = (int) (Math.random() * tamPoblacion);
            if (pt1 > pt2) { // Si el punto 1 es mayor que el punto 2, los intercambio
                int aux = pt1;
                pt1 = pt2;
                pt2 = aux;
            }
            boolean v1[] = new boolean[tamCromosoma], v2[] = new boolean[tamCromosoma];
            for (int j = 0; j < tamCromosoma; j++) { // Inicializo los "visitados" a false
                v1[i] = false;
                v2[i] = false;
            }
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();
            for (int j = pt1; j < pt2; j++) { // Copio los elementos entre pt1 y pt2 a los cromosomas de los hijos
                hijo1.getCromosoma()[j] = padre2.getCromosoma()[j];
                v1[(int) hijo1.getCromosoma()[j]] = true;
                hijo2.getCromosoma()[j] = padre1.getCromosoma()[j];
                v1[(int) hijo2.getCromosoma()[j]] = true;
            }
            // Copio los elementos del array que no esten repetidos CICLICAMENTE(desde pt2, hasta el final, y desde el principio hasta pt1)
            for (int j = 0; (j + pt2) % tamCromosoma != pt1; j++) {
                if (!v1[(int) padre1.getCromosoma()[(j + pt2) % tamCromosoma]]) {
                    hijo1.getCromosoma()[(j + pt2) % tamCromosoma] = padre1.getCromosoma()[(j + pt2) % tamCromosoma];
                    v1[(int) hijo1.getCromosoma()[(j + pt2) % tamCromosoma]] = true;
                }
                if (!v2[(int) padre2.getCromosoma()[(j + pt2) % tamCromosoma]]) {
                    hijo2.getCromosoma()[(j + pt2) % tamCromosoma] = padre2.getCromosoma()[(j + pt2) % tamCromosoma];
                    v2[(int) hijo2.getCromosoma()[(j + pt2) % tamCromosoma]] = true;
                }
            }
            hijos.add(hijo1);
            hijos.add(hijo2);
        }
        return hijos;
    }
}
