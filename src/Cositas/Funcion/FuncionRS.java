package Cositas.Funcion;

import Cositas.Individuo.Constructores.Constructor;
import Cositas.Individuo.Individuo;
import Cositas.Individuo.IndividuoRS;

public class FuncionRS extends Funcion{

    @Override
    public Individuo crearIndividuo(Constructor cons, int minProf, int maxProf) {
        return new IndividuoArbol(cons, minProf, maxProf);
    }

    @Override
    public String toString() {
        return "Funcion Regresion Simbolica";
    }
}
