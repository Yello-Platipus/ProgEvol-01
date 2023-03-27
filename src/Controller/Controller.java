package Controller;

import Cositas.AlgoritmoGenetico;
import Cositas.Individuo.Individuo;

import java.util.ArrayList;

public class Controller {
    private AlgoritmoGenetico ag;
    private double numGen[];
    private double mejorGen[];
    private double mejorAbs[];
    private double mediaGen[];

    private double fitnessEjec[];
    private int maxGen;
    String mejorInd;
    private int min, max;
    private int cont;

    private double mejorEjec;
    private ArrayList<Individuo> original;
    public Controller(){
    }

    public void run(AlgoritmoGenetico ag, int min, int max, String tipo) {
        this.ag = ag;
        this.min = min;
        this.max = max;
        cont = (max - min)/10;
        maxGen = ag.getMaxGeneraciones() ;

        ag.initPob();
        ag.evalPob();
        mejorEjec = ag.getMejorFitness();
        original = (ArrayList<Individuo>) ag.getPoblacion().clone();

        switch(tipo){
            case "Mutacion":
                runIntervaloMut();
                break;
            case "Cruce":
                break;
            case "Tamano":
                break;
            default:
                runIntervaloless();
        }

    }

    private void runIntervaloMut(){
        iniArraysIntervalos();
        for(int i = min; i < max; i+=cont){
            ag.setProbMutacion(i);

            for(int g = 0; g < maxGen; g++){
                iteracion();
            }
        }
    }
    private void runIntervaloless(){
        iniArrays();
        int i = 1;
        numGen[0] = 0;
        mediaGen[0] = ag.getMediaGen();
        mejorGen[0] = ag.getMejorFitness();
        mejorAbs[0] = mejorGen[0];
        mejorInd = ag.getMejorIndividuo().toString();

        while(i <= maxGen) {
            iteracion();
            setGrafica(i);
            i++;
        }
    }

    private void iniArrays(){
        numGen = new double[maxGen+1];
        mejorGen = new double[maxGen+1];
        mejorAbs = new double[maxGen+1];
        mediaGen = new double[maxGen+1];
    }

    public double[] getFitnessEjec() {
        return fitnessEjec;
    }

    private void iniArraysIntervalos(){
        fitnessEjec = new double[max/cont];
    }
    private void iteracion(){
        ag.generarElite();
        ag.selPob();
        ag.cruzPob();
        ag.mutPob();
        ag.introducirElite();
        ag.evalPob();

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
