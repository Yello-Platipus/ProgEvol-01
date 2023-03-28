package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CruceCO extends Cruce {

    @Override
    public String toString() {
        return "Cruce CO";
    }
    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList<Individuo> cruzados = new ArrayList<Individuo>();
        tamPob = poblacion.size();
        tamCromosoma = poblacion.get(0).getCromosoma().length;
        for(int k = 0; k < tamPob - 1; k += 2){
            Individuo padre1 = poblacion.get(k);
            Individuo padre2 = poblacion.get(k + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();
            if(Math.random() < probCruce){
                ArrayList<Integer> a1 = new ArrayList<Integer>();
                ArrayList<Integer> a2 = new ArrayList<Integer>();
                for(int i = 0; i < tamCromosoma; i++){
                    a1.add(i);
                    a2.add(i);
                }
                int cod1[] = new int[tamCromosoma];
                int cod2[] = new int[tamCromosoma];
                Object[] c1 = hijo1.getCromosoma();
                Object[] c2 = hijo2.getCromosoma();
                //Obtengo la codificacion de los dos cromosomas
                for(int i = 0; i < tamCromosoma; i++){
                    int a,b;
                    a = a1.indexOf(c1[i]);
                    b = a2.indexOf(c2[i]);
                    cod1[i] = a;
                    cod2[i] = b;
                    a1.remove(a);
                    a2.remove(b);
                }
                //Cruce monopunto de los dos arrays
                monopunto(cod1, cod2);

                //Decodificacion
                Object[] crom1 = new Object[tamCromosoma];
                Object[] crom2 = new Object[tamCromosoma];
                for(int i = 0; i < tamCromosoma; i++){
                    a1.add(i);
                    a2.add(i);
                }

                for(int i = 0; i < tamCromosoma; i++){
                    crom1[i] = a1.get(cod1[i]);
                    crom2[i] = a2.get(cod2[i]);
                    a1.remove(cod1[i]);
                    a2.remove(cod2[i]);
                }
                for(int i = 0; i < tamCromosoma; i++){
                    hijo1.setCromosoma(i,crom1[i]);
                    hijo2.setCromosoma(i,crom2[i]);
                }
            }
            cruzados.add(hijo1);
            cruzados.add(hijo2);
        }
        return cruzados;
    }
    private void monopunto(int[] cod1, int[] cod2){
        int corte = (int)(Math.random()* tamCromosoma);
        int aux[] = cod1.clone();
        for(int i = corte; i < tamCromosoma; i++){
            cod1[i] = cod2[i];
        } for(int i = corte; i < tamCromosoma; i++){
            cod2[i] = aux[i];
        }
    }

}
