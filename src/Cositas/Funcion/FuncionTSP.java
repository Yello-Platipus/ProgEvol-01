package Cositas.Funcion;

import Cositas.Individuo.Individuo;
import Cositas.Individuo.IndividuoTSP;

public class FuncionTSP extends Funcion {

    @Override
    public Individuo crearIndividuo(double precision, int d) {
        return new IndividuoTSP(precision, d);
    }

    @Override
    public String toString() {
        return "Funcion TSP";
    }
}
