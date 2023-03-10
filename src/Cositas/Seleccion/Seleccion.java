package Cositas.Seleccion;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;


public abstract class Seleccion {

    protected int tamPoblacion;
    protected double[] fitness;
    public Seleccion() {}

    public abstract String toString();
    public abstract ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion, int tamTorneo);

    public abstract void corregirMinimizar(double max);
    public abstract void corregirMaximizar(double min);
}
