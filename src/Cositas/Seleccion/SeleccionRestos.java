package Cositas.Seleccion;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.Collections;

public class SeleccionRestos extends Seleccion{
    @Override
    public String toString() {
        return "Seleccion por restos";
    }

    @Override
    public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion, int tamTorneo) {
        tamPoblacion = poblacion.size();
        double fitnessTotal = 0;
        ArrayList<Individuo> seleccionados = new ArrayList<>();
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

        for (int i = 0; i < tamPoblacion; i++) {
            if(tamPoblacion * (fitness[i] / fitnessTotal) > 1)
                seleccionados.add(poblacion.get(i).clonar());
        }
        while(seleccionados.size() < tamPoblacion) // El resto se selecciona de manera aleatoria
            seleccionados.add(poblacion.get((int) (Math.random() * tamPoblacion)).clonar());

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
