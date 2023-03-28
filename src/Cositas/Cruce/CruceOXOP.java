package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;

public class CruceOXOP extends Cruce{
    @Override
    public String toString() {
        return "Cruce OX orden prioritario";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList<Individuo> hijos = new ArrayList<>();
        int tamPoblacion = poblacion.size();
        int tamCromosoma = poblacion.get(0).getCromosoma().length;
        for(int i = 0; i < tamPoblacion - 1; i += 2){
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i + 1);
            Individuo hijo1 = padre2.clonar();
            Individuo hijo2 = padre1.clonar();

            if(Math.random() < probCruce) {
                ArrayList<Integer> pos1 = new ArrayList<Integer>();
                for (int j = 0; j < 4; j++) {
                    Integer pos2 = -1;
                    int random = (int) (Math.random() * tamCromosoma); // Genero una posicion aleatoria del cromosoma
                    while (pos1.contains(random))
                        random = (int) (Math.random() * tamCromosoma); // La regenero mientras ya este en la lista
                    pos1.add(random);
                    for (int k = 0; k < tamCromosoma; k++) {
                        if (padre2.getCromosoma()[k] == padre1.getCromosoma()[random]) {
                            pos2 = k;
                            break;
                        }
                    }
                    hijo1.setCromosoma(pos2, padre1.getCromosoma()[random]);
                }

                pos1 = new ArrayList<Integer>();
                for (int j = 0; j < 4; j++) {
                    Integer pos2 = -1;
                    int random = (int) (Math.random() * tamCromosoma); // Genero una posicion aleatoria del cromosoma
                    while (pos1.contains(random))
                        random = (int) (Math.random() * tamCromosoma); // La regenero mientras ya este en la lista
                    pos1.add(random);
                    for (int k = 0; k < tamCromosoma; k++) {
                        if (padre1.getCromosoma()[k] == padre2.getCromosoma()[random]) {
                            pos2 = k;
                            break;
                        }
                    }
                    hijo2.setCromosoma(pos2, padre2.getCromosoma()[random]);
                }
            }
            hijos.add(hijo1);
            hijos.add(hijo2);
        }
        return hijos;
    }
}
