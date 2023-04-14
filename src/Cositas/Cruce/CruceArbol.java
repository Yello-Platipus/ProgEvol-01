package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;

public class CruceArbol extends Cruce{
    @Override
    public String toString() {
        return "Operador de cruce del arbol";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList<Individuo> cruzados = new ArrayList<Individuo>();
        tamPob = poblacion.size();
        for(int i = 0; i <  tamPob -1; i+=2){
            if(Math.random() < probCruce){

            }
        }
        return cruzados;
    }
}
