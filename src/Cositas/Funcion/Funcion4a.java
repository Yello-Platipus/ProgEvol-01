package Cositas.Funcion;

import Cositas.Individuo.Individuo;
import Cositas.Individuo.IndividuoFuncion4a;

public class Funcion4a extends Funcion{
    @Override
    public Individuo crearIndividuo(double precision, int d){return new IndividuoFuncion4a(precision,d);
    }

    @Override
    public String toString() {
        return "Funcion 4a";
    }
}
