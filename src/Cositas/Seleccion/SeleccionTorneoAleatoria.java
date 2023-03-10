package Cositas.Seleccion;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.Collections;


public class SeleccionTorneoAleatoria extends Seleccion{
    public SeleccionTorneoAleatoria() {
        super();
    }

    @Override
    public String toString() {
        return "Seleccion por torneo aleatorio";
    }

    @Override
    public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion, int tamTorneo) {
        int tamPoblacion = poblacion.size();
        ArrayList<Individuo> seleccionados = new ArrayList<Individuo>(tamPoblacion);
        ArrayList<Individuo> torneo = new ArrayList<Individuo>(tamTorneo);
        double p = (Math.random() % 0.5) + 0.5;
        for(int i = 0; i < tamPoblacion; i++){
            for(int j = 0; j < tamTorneo; j++)
                torneo.add(poblacion.get((int) (Math.random() * tamPoblacion)));
            Collections.sort(torneo);

            if(Math.random() > p)
                seleccionados.add(torneo.get(0).compareTo(torneo.get(tamTorneo - 1)) == 1 ? torneo.get(0) : torneo.get(tamTorneo - 1).clonar());
            else
                seleccionados.add( torneo.get(0).compareTo(torneo.get(tamTorneo - 1)) == -1 ? torneo.get(0) : torneo.get(tamTorneo - 1).clonar());
        }
        return seleccionados;
    }

    @Override
    public void corregirMinimizar(double max) {

    }

    @Override
    public void corregirMaximizar(double min) {

    }
}
