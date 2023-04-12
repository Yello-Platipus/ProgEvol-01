package Cositas.Funcion;

import Cositas.Individuo.Constructores.Constructor;
import Cositas.Individuo.Individuo;

public abstract class Funcion {
    public abstract Individuo crearIndividuo(Constructor cons);

    public abstract String toString();
}
