package Cositas.Individuo;

import Util.Tree;

import java.util.Random;

public abstract class Individuo<T> implements Comparable<Individuo>{
	protected T[] cromosoma;
	protected double precision;
	protected int d;

	public Individuo() {
	}

	public abstract String toString();

	public abstract double getFitness();

	public abstract double getFenotipo(int x);

	public T[] getCromosoma() {
		return cromosoma;
	}

	public Random getRand(){
		return new Random();
	}


	public abstract void setCromosoma(int i, T o);

	public abstract T nextRandom();

	public abstract Individuo clonar();

	public abstract Tree getArbol();
}
