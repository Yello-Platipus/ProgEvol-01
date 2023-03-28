package Cositas.Seleccion;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.Collections;

public class SeleccionRanking extends Seleccion{
    private final static double beta = 1.5;
    @Override
    public String toString() {
        return "Seleccion por ranking";
    }

    @Override
    public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion, int tamTorneo) {
        ArrayList<Individuo> seleccionados = new ArrayList<>();
        tamPoblacion = poblacion.size();
        Collections.sort(poblacion);
        double probabilidad[] = new double[tamPoblacion];
        probabilidad[0] = calculoRanking(1, tamPoblacion);
        for(int i = 1; i < tamPoblacion; i++){
            probabilidad[i] = calculoRanking(i + 1, tamPoblacion) + probabilidad[i - 1];
        }
        double r = Math.random();
        while(seleccionados.size() < tamPoblacion){
            for(int i = 0; i < tamPoblacion; i++){
                if(r < probabilidad[i]){
                    seleccionados.add(poblacion.get(i).clonar());
                    r = Math.random();
                    break;
                }
            }
        }
        return seleccionados;
    }

    @Override
    public void corregirMinimizar(double max) {

    }

    @Override
    public void corregirMaximizar(double min) {

    }

    private double calculoRanking(int ind, int tamPoblacion){
        return ((1 / (double) tamPoblacion) * (beta - 2 * (beta - 1) * (((double) ind - 1) / ((double) tamPoblacion - 1))));
    }
}
