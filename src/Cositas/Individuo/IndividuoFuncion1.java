package Cositas.Individuo;

import Util.Converter;

public class IndividuoFuncion1 extends Individuo<Boolean> {

	public IndividuoFuncion1(double precision,int d) {
		super(precision,d);
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = -3.000;
		this.min[1] = 4.100;
		this.max[0] = 12.100;
		this.max[1] = 5.800;
		this.tamGenes[0] = this.tamGen(min[0], max[0]);
		this.tamGenes[1] = this.tamGen(min[1], max[1]);
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Boolean[tamTotal];
		for (int i = 0; i < tamTotal; i++)
			this.cromosoma[i] = getRand().nextBoolean();
	}

	public IndividuoFuncion1(Individuo ind){
		super(ind);
		int l = ind.getTamGenes().length;
		this.tamGenes = new int[l];
		for(int i = 0; i < l; i++)
			this.tamGenes[i] = ind.getTamGenes()[i];
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Boolean[tamTotal];
		for (int i = 0; i < tamTotal; i++)
			this.cromosoma[i] = (Boolean) ind.getCromosoma()[i];
	}
	public double getValor() {
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
	}

	public double getFitness() {
		return this.getValor();
	}



	public int tamGen( double min, double max) {
		return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
	}

	public double getFenotipo(int x) {
		return this.min[x] + Converter.bin2dec(cromosoma, tamGenes, x)*(this.max[x] - this.min[x])/(Math.pow(2,tamGenes[x]) - 1);
	}

	@Override
	public void setCromosoma(int i, Boolean o) {
		cromosoma[i] = o;
	}

	@Override
	public int compareTo(Individuo o) { //Maximizar
		if(this.getFitness() < o.getFitness())
			return 1;
		else if(this.getFitness() > o.getFitness())
			return -1;
		return 0;
	}
	public Boolean nextRandom() {
		return getRand().nextBoolean();
	}
	@Override
	public Individuo clonar(){return new IndividuoFuncion1(this);}
}
