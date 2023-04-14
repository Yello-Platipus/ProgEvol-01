package Cositas.Funcion;

import Cositas.Individuo.Constructores.Constructor;
import Cositas.Individuo.Individuo;

public abstract class Funcion {
    public abstract Individuo crearIndividuo(Constructor cons, int minProf, int maxProf);

    public abstract String toString();
}
