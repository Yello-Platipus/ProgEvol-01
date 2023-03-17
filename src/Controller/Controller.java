package Controller;

import Cositas.AlgoritmoGenetico;
import Cositas.Individuo.Individuo;

public class Controller {
    private AlgoritmoGenetico ag;
    private double numGen[];
    private double mejorGen[];
    private double mejorAbs[];
    private double mediaGen[];
    private int maxGen;
    String mejorInd;

    public Controller(){
        //ag = new AlgoritmoGenetico(100, 100, 0.6, 0.05, 0.01);
    }

    public void run(AlgoritmoGenetico ag) {
        this.ag = ag;

        maxGen = ag.getMaxGeneraciones() ;
        numGen = new double[maxGen+1];
        mejorGen = new double[maxGen+1];
        mejorAbs = new double[maxGen+1];
        mediaGen = new double[maxGen+1];

        int i = 1;
        ag.initPob();
        ag.evalPob();
        numGen[0] = 0;
        mediaGen[0] = ag.getMediaGen();
        mejorGen[0] = ag.getMejorFitness();
        mejorAbs[0] = mejorGen[0];
        mejorInd = ag.getMejorIndividuo().toString();

        while(i <= maxGen) {
            ag.generarElite();
            ag.selPob();
            ag.cruzPob();
            ag.mutPob();
            ag.introducirElite();
            ag.evalPob();
            setGrafica(i);
            i++;
        }
    }
    private void setGrafica(int i){
        numGen[i] = i;
        mediaGen[i] = ag.getMediaGen();
        mejorGen[i] = ag.getMejorFitness();

        if(ag.esMejor(mejorAbs[i-1], mejorGen[i])){
            mejorAbs[i] = mejorGen[i];
            mejorInd = ag.getMejorIndividuo().toString();
        }
        else{
            mejorAbs[i] = mejorAbs[i-1];
        }

    }
    public double[] getNumGen() { return numGen;}

    public double[] getMejorGen() { return mejorGen;}

    public double[] getMejorAbs(){return mejorAbs;}

    public double[] getMediaGen(){return mediaGen;}

    public String getMejorIndAbs() { return mejorInd; }
}
