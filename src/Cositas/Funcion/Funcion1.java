package Cositas.Funcion;

import Cositas.Individuo.Individuo;
import Cositas.Individuo.IndividuoFuncion1;

public class Funcion1 extends Funcion{
    @Override
    public Individuo crearIndividuo(double precision,int d){return new IndividuoFuncion1(precision,d);
    }

    @Override
    public String toString() {
        return "Funcion 1";
    }
}
