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
    private int mejorEjecX;
    private ArrayList<Individuo> original;
    private double[] numInterval;

    public Controller(){
    }

    public void run(AlgoritmoGenetico ag, int min, int max, String tipo) {
        this.ag = ag;
        this.min = min;
        this.max = max;
        cont = (max - min)/10;
        maxGen = ag.getMaxGeneraciones();
        
        ag.initPob();
        ag.evalPob();

        mejorAbs = new double[1];
        mejorAbs[0] = ag.getMejorFitness();
        mejorEjecX = 0;
        original = (ArrayList<Individuo>) ag.getPoblacion().clone();

        switch(tipo){
            case "Porcentaje de mutacion":
                runIntervaloMut();
                break;
            case "Porcentaje de cruce":
                runIntervaloCruce();
                break;
            case "Tamano de poblacion":
                runIntervaloPob();
                break;
            default:
                runIntervaloless();
        }

    }

    private void runIntervaloMut(){
        iniArraysIntervalos();
        int fCont =0;
        for(int i = min; i <= max; i+=cont){
            ag.setProbMutacion((double)i/100);
            ag.setPoblacion((ArrayList<Individuo>) original.clone());
            mejorEjec = ag.getMejorFitness();
            for(int g = 0; g < maxGen; g++){
                iteracion();
                double actual = ag.getMejorFitness();
                if(ag.esMejor(mejorEjec, actual))
                    mejorEjec = actual;
            }
            if(ag.esMejor(mejorAbs[0], mejorEjec)) {
                mejorAbs[0] = mejorEjec;
                mejorEjecX = i;
            }
            numInterval[fCont] = i;
            fitnessEjec[fCont++] = mejorEjec;
        }
    }
    private void runIntervaloCruce(){
        iniArraysIntervalos();
        int fCont = 0;
        for(int i = min; i <= max; i+=cont){
            ag.setProbCruce((double)i/100);
            ag.setPoblacion((ArrayList<Individuo>) original.clone());
            mejorEjec = ag.getMejorFitness();
            for(int g = 0; g < maxGen; g++){

                iteracion();
                double actual = ag.getMejorFitness();
                if(ag.esMejor(mejorEjec, actual))
                    mejorEjec = actual;
            }
            if(ag.esMejor(mejorAbs[0], mejorEjec)) {
                mejorAbs[0] = mejorEjec;
                mejorEjecX = i;
            }
            numInterval[fCont] = i;
            fitnessEjec[fCont++] = mejorEjec;

        }
    }
    private void runIntervaloPob(){
        iniArraysIntervalos();
        int fCont = 0;
        for(int i = min; i <= max; i+=cont){
            ag.setTamPoblacion(i);
            ag.initPob();
            ag.evalPob();
            mejorEjec = ag.getMejorFitness();

            for(int g = 0; g < maxGen; g++){
                iteracion();
                double actual = ag.getMejorFitness();
                if(ag.esMejor(mejorEjec, actual))
                    mejorEjec = actual;
            }
            if(ag.esMejor(mejorAbs[0], mejorEjec)) {
                mejorAbs[0] = mejorEjec;
                mejorEjecX = i;
            }
            numInterval[fCont] = i;
            fitnessEjec[fCont++] = mejorEjec;
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
        
        fitnessEjec = new double[1+(max-min)/cont];
        numInterval = new double[1+(max-min)/cont];
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

    public double[] getNumInterval(){return numInterval;}

    public int getMejorEjecX(){return mejorEjecX;}

    public double getMejorEjec(){return mejorEjec;}
}
