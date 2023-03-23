package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CruceOXPP extends Cruce{
    @Override
    public String toString() {
        return "Cruce OX posiciones prioritarias";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList<Individuo> hijos = new ArrayList<>();
        int tamPoblacion = poblacion.size();
        int tamCromosoma = poblacion.get(0).getCromosoma().length;
        for(int i = 0; i < tamPoblacion - 1; i += 2) {
            int ultimo = 0;
            Set nAleatorios = new HashSet<Integer>();
            Set m1 = new HashSet<Integer>(), m2 = new HashSet<Integer>();
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();
            for(int j = 0; j < 4; j++){
                int random = (int) (Math.random() * tamCromosoma);
                while(nAleatorios.contains(random))
                    random = (int) (Math.random() * tamCromosoma);
                nAleatorios.add(random);
                if(random > ultimo)
                    ultimo = random;
                hijo1.setCromosoma(random, padre2.getCromosoma()[random]);
                m1.add(hijo1.getCromosoma()[random]);
                hijo2.setCromosoma(random, padre1.getCromosoma()[random]);
                m2.add(hijo2.getCromosoma()[random]);
            }
            // TODO Hay que arreglar esto todavia, y preguntar si es obligatorio empezar desde la derecha del todo
            // Probablemente sea buena idea borrarlo y hacerlo de cero
            int i1 = ultimo, i2 = ultimo;
            for(int j = ultimo + 1; j != ultimo && (i1 != ultimo - 1 || i2 != ultimo - 1); j++){
                j %= tamCromosoma;
                    if(!m1.contains(padre1.getCromosoma()[j])){
                        hijo1.setCromosoma(i1, padre1.getCromosoma()[j]);
                        m1.add(padre1.getCromosoma()[j]);
                        do {
                            i1++;
                            i1 %= tamCromosoma;
                        } while(m1.contains(padre1.getCromosoma()[i1]) && i1 != ultimo - 1);
                    }
                    if(!m2.contains(padre2.getCromosoma()[j])){
                        hijo2.setCromosoma(i2, padre2.getCromosoma()[j]);
                        m2.add(padre2.getCromosoma()[j]);
                        do {
                            i2++;
                            i2 %= tamCromosoma;
                        } while(m2.contains(padre2.getCromosoma()[i2]) && i2 != ultimo - 1);
                    }
            }
            hijos.add(hijo1);
            hijos.add(hijo2);
        }
        return hijos;
    }
}
