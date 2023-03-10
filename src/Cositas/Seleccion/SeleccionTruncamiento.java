package Cositas.Seleccion;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.Collections;

public class SeleccionTruncamiento extends Seleccion{
    private double trunc = 0.5;
    @Override
    public String toString() {
        return "Seleccion por Truncamiento";
    }

    @Override
    public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion, int tamTorneo) {
        tamPoblacion = poblacion.size();
        int tamTrunc = (int) (tamPoblacion * trunc);
        ArrayList<Individuo> seleccionados = new ArrayList<>();
        Collections.sort(poblacion);
        for (int i = 0; i < tamPoblacion; i++) {
            seleccionados.add(poblacion.get(i % tamTrunc).clonar());
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
