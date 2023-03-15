package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
//Cruce de emparejamiento parcial
public class CrucePMX extends Cruce{
    private Boolean[] i1,i2;
    @Override
    public String toString() {
        return "Cruce PMX";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        int tamPob = poblacion.size();
        ArrayList cruzados = new ArrayList<Individuo>();
        for(int i = 0; i < poblacion.size() -1; i+=2) {

            Individuo padre1, padre2;
            padre1 = poblacion.get(i);
            padre2 = poblacion.get(i + 1);
            Individuo hijo1, hijo2;
            hijo1 = padre1.clonar();
            hijo2 = padre2.clonar();
            if(Math.random() < probCruce){
                i1 = new Boolean[tamPob];
                i2 = new Boolean[tamPob];

                Object[] c1 = padre1.getCromosoma();
                Object[] c2 = padre2.getCromosoma();

                int tam = padre1.getCromosoma().length;
                int pos1 = 0, pos2 = 0;
                while (pos1 == pos2) {
                    pos1 = (int) Math.round(Math.random() * tamPob);
                    pos2 = (int) Math.round(Math.random() * tamPob);
                }
                if (pos2 < pos1) {
                    int aux = pos2;
                    pos2 = pos1;
                    pos1 = aux;
                }

                Object[] cAux1 = new Object[tam];
                Object[] cAux2 = new Object[tam];
                for(int c = pos1; c < pos2; c++){
                    cAux1[c] = c2[c];
                    cAux2[c] = c1[c];
                    i1[c] = true;
                    i2[c] = true;
                }

              for(int k = pos2; k < tamPob; k++){
                  if(!i1[k])
                      cAux1[k] = c1[k];
                  else
                      cAux1[k] = c2[buscarPos(c2,c1[k])];
                  i1[(int)cAux1[k]] = true;
                  if(!i2[k])
                      cAux2[k] = c2[k];
                  else
                      cAux2[k] = c1[buscarPos(c1,c2[k])];
                      i2[(int)cAux2[k]] = true;
              }
            }
            cruzados.add(hijo1);
            cruzados.add(hijo2);
        }
        return cruzados;
    }

    private int buscarPos(Object[] array, Object o){
        int i = 0;
        while(array[i] != o)
            i++;
        return i;
    }
}
