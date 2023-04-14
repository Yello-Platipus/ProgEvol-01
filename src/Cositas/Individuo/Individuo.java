package Cositas.Individuo;

import Util.Tree;

import java.util.Random;

public abstract class Individuo<T> implements Comparable<Individuo>{
	protected T[] cromosoma;
	protected int[] tamGenes;
	protected double min[];
	protected double max[];
	protected double precision;
	protected int d;
	//protected Comparator comp;

	public Individuo(double precision,int d) {
		this.precision = precision;
		this.d = d;
	}
	public Individuo(Individuo ind){
		this.d = ind.getD();
		this.max = new double[d];
		this.min = new double[d];
		this.precision = ind.getPrecision();
		for(int i = 0; i < d; i++){
			this.max[i] = ind.getMax()[i];
			this.min[i] = ind.getMin()[i];
		}
	}

	protected int[] getTamGenes() { return tamGenes;
	}

	protected double getPrecision() { return precision;
	}

	protected int getD() {return d;
	}

	public abstract double getFitness();
	
	public abstract int tamGen( double min, double max);

	public abstract double getFenotipo(int x);

	public double[] getMin(){return min;}

	public double[] getMax(){return max;}

	public T[] getCromosoma() {
		return cromosoma;
	}

	public Random getRand(){
		return new Random();
	}

	public String toString(){
		int n = tamGenes.length;
		String r = "			Mejor solucion = ";
		for(int i = 1; i <= n; i++){
			r += "X" + i + "(" + getFenotipo(i-1) + ")";
			if(i < n-1)
				r+= ", ";
			else if(i == n-1)
				r +=" y ";
		}
		r+= " con fitness " + getFitness();
		return r;
	}

	public abstract void setCromosoma(int i, T o);

	public abstract T nextRandom();

	public abstract Individuo clonar();

	public abstract Tree getArbol();
}
