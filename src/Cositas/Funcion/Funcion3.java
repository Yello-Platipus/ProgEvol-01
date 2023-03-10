package Cositas.Funcion;

import Cositas.Individuo.Individuo;
import Cositas.Individuo.IndividuoFuncion3;

public class Funcion3 extends Funcion{
    @Override
    public Individuo crearIndividuo(double precision, int d){return new IndividuoFuncion3(precision,d);
    }

    @Override
    public String toString() {
        return "Funcion 3";
    }
}
