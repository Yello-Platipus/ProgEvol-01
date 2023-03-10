package Cositas.Seleccion;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.Collections;

public class SeleccionTorneoDeterminista  extends Seleccion{

    private static final int k = 3;

    public SeleccionTorneoDeterminista() {
        super();
    }

    @Override
    public String toString() {
        return "Seleccion por torneo determinista";
    }

    @Override
    public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion, int tamTorneo) {
        int tamPoblacion = poblacion.size();
        ArrayList<Individuo> ret = new ArrayList<Individuo>(tamPoblacion);

        for(int i = 0; i < tamPoblacion; i++){
            ArrayList<Individuo> aux = new ArrayList<Individuo>(k);
            for(int j = 0; j < k; j++){
                aux.add(poblacion.get((int) (Math.random() * tamPoblacion)));
            }
            Collections.sort(aux);
            ret.add(aux.get(0).clonar());
        }
        return ret;
    }

    @Override
    public void corregirMinimizar(double max) {

    }

    @Override
    public void corregirMaximizar(double min) {

    }
}
