package Cositas.Cruce;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;

public abstract class Cruce {
    protected int tamPob;
    protected int tamCromosoma;
    public abstract String toString();
    public abstract ArrayList<Individuo> cruzar(ArrayList<Individuo>  poblacion, double probCruce);
}
