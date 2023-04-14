package Cositas.Individuo;

import Cositas.Individuo.Constructores.Constructor;
import Util.Node;
import Util.Tree;

import java.util.ArrayList;
import java.util.Arrays;

public class IndividuoArbol extends Individuo<Object>{

    private Constructor constructor;
    private Tree arbol;
    private int minProf;
    private int maxProf;

    public IndividuoArbol(double precision, int d) {
        super(precision, d);
    }

    public IndividuoArbol(Constructor cons, int profMin, int profMax){
        super(0.001, 2);
        this.constructor = cons;
        this.arbol = new Tree();
        constructor.construir(arbol.root, 1);
        minProf = profMin;
        maxProf = profMax;
    }

    @Override
    public double getFitness() { // TODO
        return 0;
    }

    @Override
    public int tamGen(double min, double max) {
        return 0;
    }

    @Override
    public double getFenotipo(int x) {
        return 0;
    }

    @Override
    public void setCromosoma(int i, Object o) {

    }

    @Override
    public Object nextRandom() {
        return null;
    }

    @Override
    public Individuo clonar() { // TODO
        return null;
    }

    @Override
    public int compareTo(Individuo o) {
        if(this.getFitness() > o.getFitness())
            return 1;
        else if(this.getFitness() < o.getFitness())
            return -1;
        return 0;
    }
}
