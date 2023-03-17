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
                int tam = padre1.getCromosoma().length;
                i1 = new Boolean[tam];
                i2 = new Boolean[tam];

                Object[] c1 = padre1.getCromosoma();
                Object[] c2 = padre2.getCromosoma();


                int pos1 = 0, pos2 = 0;
                while (pos1 == pos2) {
                    pos1 = (int) Math.round(Math.random() * tam);
                    pos2 = (int) Math.round(Math.random() * tam);
                }
                if (pos2 < pos1) {
                    int aux = pos2;
                    pos2 = pos1;
                    pos1 = aux;
                }

                Object[] cAux1 = new Object[tam];
                Object[] cAux2 = new Object[tam];
                for(int k = 0; k < tam; k++) {
                    i1[k] = false;
                    i2[k] = false;
                }
                for(int c = pos1; c < pos2; c++){
                    cAux1[c] = c2[c];
                    cAux2[c] = c1[c];
                    i1[(int)cAux1[c]] = true;
                    i2[(int)cAux1[c]] = true;
                }
                for(int k = pos2; k < tam; k++){
                  if(!i1[(int)c1[k]])
                      cAux1[k] = c1[k];
                  else{
                      int aux = (int)c2[(int)c1[k]];
                      while(i1[aux])                //TODO
                          aux = (int)c2[aux];
                      cAux1[k] = aux;
                  }
                  i1[(int)cAux1[k]] = true;
                  if(!i2[(int)c2[k]])
                      cAux2[k] = c2[k];
                  else{
                      int aux = (int)c1[(int)c2[k]];
                      while(i2[aux])
                          aux = (int)c1[aux];
                      cAux2[k] = aux;
                  }
                      i2[(int)cAux2[k]] = true;
                }
                for(int k = 0; k < pos1; k++){
                  if(!i1[(int)c1[k]])
                      cAux1[k] = c1[k];
                  else{
                      int aux = (int)c2[(int)c1[k]];
                      while(i1[aux])
                          aux = (int)c2[aux];
                      cAux1[k] = aux;
                  }
                  i1[(int)cAux1[k]] = true;
                  if(!i2[(int)cAux1[k]])
                      cAux2[k] = c2[k];
                  else{
                      int aux = (int)c1[(int)c2[k]];
                      while(i2[aux])
                          aux = (int)c1[aux];
                      cAux2[k] = aux;
                  }
                  i2[(int)cAux2[k]] = true;
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
