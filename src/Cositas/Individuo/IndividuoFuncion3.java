package Cositas.Individuo;

import Util.Converter;

public class IndividuoFuncion3 extends Individuo<Boolean>{
    private int d;
    public IndividuoFuncion3(double precision,int d) {
        super(precision, d);
        this.d = 2;
        tamGenes = new int[d];
        min = new double[d];
        max = new double[d];
        int tamTotal = 0;
        for(int i = 0; i< d; i++){
            min[i] = -5;
            max[i] = 5;
            tamGenes[i] = tamGen(min[i], max[i]);
            tamTotal += tamGenes[i];
        }

        this.cromosoma = new Boolean[tamTotal];
        for (int i = 0; i < tamTotal; i++) this.cromosoma[i] = getRand().nextBoolean();
    }
    public IndividuoFuncion3(Individuo ind){
        super(ind);
        int tamTotal = 0;
        this.d = 2;
        int l = ind.getTamGenes().length;
        this.d = 2;
        this.tamGenes = new int[l];
        for(int i = 0; i < l; i++) {
            this.tamGenes[i] = ind.getTamGenes()[i];
            tamTotal += tamGenes[i];
        }
        this.cromosoma = new Boolean[tamTotal];
        for (int i = 0; i < tamTotal; i++)
            this.cromosoma[i] = (Boolean) ind.getCromosoma()[i];
    }

    public double getValor() {
        double xi[] = new double[d];

        double sumatorio = 0;
        for(int i = 0; i < d; i++){
            xi[i] = getFenotipo(i);
            sumatorio += Math.pow(xi[i],4) - 16*Math.pow(xi[i],2) + 5*xi[i];
        }
        return sumatorio/2 ;
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
    public int compareTo(Individuo o) { //Minimizar
        if(this.getFitness() > o.getFitness())
            return 1;
        else if(this.getFitness() < o.getFitness())
            return -1;
        return 0;
    }
    public Boolean nextRandom(){
        return getRand().nextBoolean();
    }
    public Individuo clonar(){return new IndividuoFuncion3(this);}

}


