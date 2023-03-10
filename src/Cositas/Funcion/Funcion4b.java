package Cositas.Funcion;

import Cositas.Individuo.Individuo;
import Cositas.Individuo.IndividuoFuncion4b;

public class Funcion4b extends Funcion{
    @Override
    public Individuo crearIndividuo(double precision, int d){return new IndividuoFuncion4b(precision,d);
    }

    @Override
    public String toString() {
        return "Funcion 4b";
    }
}
