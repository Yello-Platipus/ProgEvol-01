package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CruceParticion extends Cruce{
    @Override
    public String toString() {
        return "Cruce de particion";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList<Individuo> cruzados = new ArrayList<Individuo>();
        tamPob = poblacion.size();
        tamCromosoma = poblacion.get(0).getCromosoma().length;
        for(int k = 0; k < tamPob - 1; k+=2){
            Individuo padre1 = poblacion.get(k);
            Individuo padre2 = poblacion.get(k + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();

            if(Math.random() < probCruce){
                int particion = (int) (Math.random() * tamCromosoma);
                Set i1 = new HashSet<Integer>(tamCromosoma);
                Set i2 = new HashSet<Integer>(tamCromosoma);

                for(int i = 0; i < tamCromosoma; i++){
                    i1.add(i);
                    i2.add(i);
                }
                Object[] c1 = padre1.getCromosoma();
                Object[] c2 = padre2.getCromosoma();
                Object[] crom1 = new Object[tamCromosoma];
                Object[] crom2 = new Object[tamCromosoma];
                for(int i = 0; i < particion; i++){
                    crom1[i] = c2[i];
                    i1.remove(crom1[i]);
                    crom2[i] = c1[i];
                    i2.remove(crom2[i]);
                }
                int i = particion,j = i, h = i;
                while(i < tamCromosoma){
                    if(i1.contains(c1[i])){
                        crom1[j] = c1[i];
                        i1.remove(crom1[j]);
                        j++;
                    }
                    if(i2.contains(c2[i])){
                        crom2[h] = c1[i];
                        i2.remove(crom2[h]);
                        h++;
                    }
                    i++;
                }
                for(Object crom: i1){
                    crom1[j] = crom;
                    j++;
                }for(Object crom: i2){
                    crom2[h] = crom;
                    h++;
                }
                for(int l = 0; l < tamCromosoma; l++){
                    hijo1.setCromosoma(l,crom1[l]);
                    hijo2.setCromosoma(l,crom2[l]);
                }
            }
            cruzados.add(hijo1);
            cruzados.add(hijo2);
        }
        return cruzados;
    }
}
