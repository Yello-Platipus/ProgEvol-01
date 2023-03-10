package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;

public class CruceUniforme extends Cruce{
    @Override
    public String toString() {
        return "Cruce uniforme";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        int tamPoblacion = poblacion.size();
        ArrayList<Individuo> seleccionados = new ArrayList<Individuo>(tamPoblacion);
        for(int i = 0; i < tamPoblacion-1; i+=2){
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i+1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();
            if(Math.random() < probCruce){
                for(int k = 0; k < padre1.getCromosoma().length; k++){
                    if(Math.random() < 0.5){
                        hijo1.getCromosoma()[k] = padre2.getCromosoma()[k];
                        hijo2.getCromosoma()[k] = padre1.getCromosoma()[k];
                    }
                }

            }

            seleccionados.add(hijo1);
            seleccionados.add(hijo2);

        }
        return seleccionados;
    }
}
