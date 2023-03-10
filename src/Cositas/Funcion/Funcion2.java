package Cositas.Funcion;

import Cositas.Individuo.Individuo;
import Cositas.Individuo.IndividuoFuncion2;

public class Funcion2 extends Funcion{
    @Override
    public Individuo crearIndividuo(double precision, int d){return new IndividuoFuncion2(precision,d);
    }

    @Override
    public String toString() {
        return "Funcion 2";
    }
}
