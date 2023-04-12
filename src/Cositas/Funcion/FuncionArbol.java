package Cositas.Funcion;

import Cositas.Individuo.Constructores.Constructor;
import Cositas.Individuo.Individuo;
import Cositas.Individuo.IndividuoArbol;

public class FuncionArbol extends Funcion{

    @Override
    public Individuo crearIndividuo(Constructor cons) {
        return new IndividuoArbol(cons);
    }

    @Override
    public String toString() {
        return "Funcion Arbol";
    }
}
