package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.*;

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
            Set m1 = new HashSet<Integer>(), m2 = new HashSet<Integer>();
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();
            if(Math.random() < probCruce) {
                for (int j = pt1; j < pt2; j++) { // Copio los elementos entre pt1 y pt2 a los cromosomas de los hijos
                    hijo1.setCromosoma(j, padre2.getCromosoma()[j]);
                    m1.add(hijo1.getCromosoma()[j]);
                    hijo2.setCromosoma(j, padre1.getCromosoma()[j]);
                    m2.add(hijo2.getCromosoma()[j]);
                }
                // Copio los elementos del array que no esten repetidos CICLICAMENTE(desde pt2, hasta el final, y desde el principio hasta pt1)
                int i1 = pt2, i2 = pt2;
                if (!m1.contains(padre1.getCromosoma()[pt2])) {
                    hijo1.setCromosoma(i1, padre1.getCromosoma()[pt2]);
                    m1.add(padre1.getCromosoma()[pt2]);
                    i1++;
                    i1 %= tamCromosoma;
                }
                if (!m2.contains(padre2.getCromosoma()[pt2])) {
                    hijo2.setCromosoma(i2, padre2.getCromosoma()[pt2]);
                    m2.add(padre2.getCromosoma()[pt2]);
                    i2++;
                    i2 %= tamCromosoma;
                }
                for (int j = pt2 + 1; j != pt2 && (i1 != pt1 || i2 != pt1); j++) {
                    j %= tamCromosoma;
                    if (!m1.contains(padre1.getCromosoma()[j])) {
                        hijo1.setCromosoma(i1, padre1.getCromosoma()[j]);
                        m1.add(padre1.getCromosoma()[j]);
                        i1++;
                        i1 %= tamCromosoma;
                    }
                    if (!m2.contains(padre2.getCromosoma()[j])) {
                        hijo2.setCromosoma(i2, padre2.getCromosoma()[j]);
                        m2.add(padre2.getCromosoma()[j]);
                        i2++;
                        i2 %= tamCromosoma;
                    }
                }
            }
            hijos.add(hijo1);
            hijos.add(hijo2);
        }
        return hijos;
    }
}
