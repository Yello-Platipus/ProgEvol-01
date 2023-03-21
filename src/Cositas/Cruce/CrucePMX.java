package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.*;

//Cruce de emparejamiento parcial
public class CrucePMX extends Cruce{
    private Set<Integer> i1,i2;
    private Map<Integer,Integer> p1,p2;
    @Override
    public String toString() {
        return "Cruce PMX";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        int tamPob = poblacion.size();
        ArrayList cruzados = new ArrayList<Individuo>();
        for(int i = 0; i < tamPob -1; i+=2) {

            Individuo padre1, padre2;
            padre1 = poblacion.get(i);
            padre2 = poblacion.get(i + 1);
            Individuo hijo1, hijo2;
            hijo1 = padre1.clonar();
            hijo2 = padre2.clonar();
            if(Math.random() < probCruce){
                int tam = padre1.getCromosoma().length;
                i1 = new HashSet<Integer>();
                i2 = new HashSet<Integer>();
                p1 = new HashMap<Integer,Integer>();
                p2 = new HashMap<Integer,Integer>();

                Object[] c1 = padre1.getCromosoma();
                Object[] c2 = padre2.getCromosoma();

                //Se elige 2 posiciones aleatorias
                int pos1 = 0, pos2 = 0;
                while (pos1 == pos2) {
                    pos1 = (int) Math.round(Math.random() * tam);
                    pos2 = (int) Math.round(Math.random() * tam);
                }
                //La mas pequenya sera pos1
                if (pos2 < pos1) {
                    int aux = pos2;
                    pos2 = pos1;
                    pos1 = aux;
                }

                Object[] cAux1 = new Object[tam];
                Object[] cAux2 = new Object[tam];
               for(int k = 0; k < tam; k++){
                   p1.put((int)c1[k], k);
                   p2.put((int)c2[k], k);
               }
                //Intercambiamos los valores entre pos1 y pos 2
                for(int c = pos1; c < pos2; c++){
                    cAux1[c] = c2[c];
                    cAux2[c] = c1[c];
                    i1.add((int)cAux1[c]);
                    i2.add((int)cAux2[c]);
                }

                //Si no está en el cromosoma, bajamos la posicion k con el valor del padre en k, si está, le asignamos -1
                for(int k = pos2; k < tam; k++){
                  if(!i1.contains((int)c1[k])){
                      cAux1[k] = c1[k];
                      i1.add((int)cAux1[k]);
                  }
                  else
                      cAux1[k] = -1;
                  if(!i2.contains((int)c2[k])){
                      cAux2[k] = c2[k];
                      i2.add((int)cAux2[k]);
                  }
                  else
                      cAux2[k] = -1;
                }
                for(int k = 0; k < pos1; k++){
                    if(!i1.contains((int)c1[k])){
                        cAux1[k] = c1[k];
                        i1.add((int)cAux1[k]);
                    }
                    else
                        cAux1[k] = -1;
                    if(!i2.contains((int)c2[k])){
                        cAux2[k] = c2[k];
                        i2.add((int)cAux2[k]);
                    }
                    else
                        cAux2[k] = -1;
                }

                for(int k =0; k < tam; k++){
                      if((int)cAux1[k] == -1){
                          int pos = p2.get((int)c1[k]);
                          while(i1.contains(c1[pos]))
                              pos = p2.get((int)c1[pos]);
                          cAux1[k] = c1[pos];
                          i1.add((int)cAux1[k]);
                      }
                      if((int)cAux2[k] == -1){
                          int pos = p1.get((int)c2[k]);
                          while(i2.contains(c2[pos]))
                              pos = p1.get((int)c2[pos]);
                          cAux2[k] = c2[pos];
                          i2.add((int)cAux2[k]);
                      }
                  }
                for(int c = 0; c < tam; c++){
                    hijo1.setCromosoma(c,cAux1[c]);
                    hijo2.setCromosoma(c,cAux2[c]);
                }
            }
            cruzados.add(hijo1);
            cruzados.add(hijo2);

        }
        if(tamPob%2 != 0)
            cruzados.add(poblacion.get(tamPob-1).clonar());
        return cruzados;
    }
}
