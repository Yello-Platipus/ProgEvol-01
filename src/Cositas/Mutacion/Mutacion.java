package Cositas.Mutacion;

import Cositas.Individuo.Individuo;

public abstract class Mutacion {
    public abstract String toString();
    public abstract void  mutar(Individuo ind, double probMutacion);

}
