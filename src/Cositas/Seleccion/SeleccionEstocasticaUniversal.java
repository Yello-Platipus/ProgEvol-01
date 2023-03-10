package Cositas.Seleccion;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.Collections;

public class SeleccionEstocasticaUniversal extends Seleccion{
    @Override
    public String toString() {
        return "Seleccion Estocastica Universal";
    }

    @Override
    public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion, int tamTorneo) {
        tamPoblacion = poblacion.size();
        ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
        double fitnessTotal = 0;
        fitness = new double[tamPoblacion];
        Collections.sort(poblacion);
        for(int i = 0; i < tamPoblacion; i++){
            fitness[i] = poblacion.get(i).getFitness();
        }

        int q = 1;
        do{

            if(fitness[0] < fitness[q])
                if(fitness[tamPoblacion - 1] > 0)
                    corregirMinimizar(fitness[tamPoblacion - 1]);
                else if(fitness[0] > fitness[q])
                    if(fitness[tamPoblacion - 1] < 0)
                        corregirMaximizar(fitness[tamPoblacion - 1]);
            q++;
        }while(fitness[0] == fitness[q]);

        for(int i = 0 ; i < tamPoblacion; i++){
            fitnessTotal += fitness[i];
        }

        double r = Math.random() * (1 / (double) tamPoblacion);
        double sum = 0;
        for(int i = 0; i < tamPoblacion && seleccionados.size() < tamPoblacion; i++){
            sum += fitness[i] / fitnessTotal;
            while(sum > r && seleccionados.size() < tamPoblacion){
                seleccionados.add(poblacion.get(i).clonar());
                r += 1 / (double) tamPoblacion;
            }
        }
        return seleccionados;
    }

    @Override
    public void corregirMinimizar(double max) {
        for(int i = 0; i < this.tamPoblacion; i++)
            this.fitness[i] = (1.05 * max) - this.fitness[i];
    }

    @Override
    public void corregirMaximizar(double min) {
        for(int i = 0; i < this.tamPoblacion; i++)
            this.fitness[i] = this.fitness[i] + Math.abs(min);
    }
}
