package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;

public class CruceMonopunto extends Cruce{
    @Override
    public String toString() {
        return "Cruce monopunto";
    }

    @Override
    public ArrayList<Individuo>  cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList cruzados = new ArrayList<Individuo>();
        for(int i = 0; i < poblacion.size() -1; i+=2){
            Individuo padre1, padre2;
            padre1 = poblacion.get(i);
            padre2 = poblacion.get(i + 1);
            Individuo hijo1, hijo2;
            hijo1 = padre1.clonar();
            hijo2 = padre2.clonar();
            if(Math.random() < probCruce){

                Object[] c1 = padre1.getCromosoma();
                Object[] c2 = padre2.getCromosoma();

                int tam = padre1.getCromosoma().length;
                int corte = (int)Math.round(tam*Math.random());

                Object[] cAux1 = new Object[tam];
                Object[] cAux2 = new Object[tam];
                for(int c = 0; c < corte; c++){
                   cAux1[c] = c1[c];
                   cAux2[c] = c2[c];
                }
                for(int c = corte; c < tam; c++){
                    cAux1[c] = c2[c];
                    cAux2[c] = c1[c];
                }


                for(int c = 0; c < tam; c++){
                    hijo1.setCromosoma(c,cAux1[c]);
                    hijo2.setCromosoma(c,cAux2[c]);
                }

            }
            cruzados.add(hijo1);
            cruzados.add(hijo2);

        }
    return cruzados;
    }
}
