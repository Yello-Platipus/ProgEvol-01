package Cositas;

import Cositas.Cruce.*;
import Cositas.Funcion.Funcion;
import Cositas.Funcion.FuncionTSP;
import Cositas.Individuo.Individuo;
import Cositas.Mutacion.*;
import Cositas.Seleccion.Seleccion;
import Cositas.Seleccion.SeleccionRuleta;

import java.util.ArrayList;
import java.util.Collections;


public class AlgoritmoGenetico {
	private int tamPoblacion;
	private ArrayList<Individuo> poblacion;
	//private double[] fitness;
	private int maxGeneraciones;
	private double probCruce;
	private double probMutacion;
	private double precision;
	private int d;
	private int tamTorneo;
	private Individuo elMejor;

	private double elitismo;
	private int numElite = 0;
	private Individuo elite[];

	private double mediaGen;

	private Seleccion sel;
	private Cruce cruce;
	private Mutacion mut;
	private Funcion func;

	public AlgoritmoGenetico(){
		this.tamPoblacion = 100;
		this.maxGeneraciones = 100;
		this.probCruce = 0.6;
		this.probMutacion = 0.05;
		this.precision = 0.001;
		this.elitismo = 0.0;
		this.d = 2;
		this.tamTorneo = 3;
		this.sel = new SeleccionRuleta();
		this.cruce = new CrucePMX();
		this.mut = new MutacionIntercambio();
		this.func = new FuncionTSP();

	}
	public AlgoritmoGenetico(int tamPoblacion, int maxGeneraciones, double probCruce, double probMutacion, double precision){
		this.tamPoblacion = tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.probCruce = probCruce;
		this.probMutacion = probMutacion;
		this.precision = precision;

	}

	public void evalPob(){
		Collections.sort(poblacion);
		elMejor = poblacion.get(0);
		mediaGen = calcularMediaGen();
	}

	public void initPob(){
		poblacion = new ArrayList<Individuo>();
		for(int i = 0; i < tamPoblacion; i++) {
			poblacion.add(func.crearIndividuo(precision,d));
		}
	}
	public void selPob(){
		poblacion = sel.seleccionar(poblacion, tamTorneo);
	}
	public void cruzPob(){
		poblacion = cruce.cruzar(poblacion, probCruce);

	}
	public void mutPob(){
		for(int i = 0; i < tamPoblacion; i++){
			mut.mutar(poblacion.get(i), probMutacion);
		}
	}

	public double getMejorFitness() { return elMejor.getFitness();}

	public void generarElite(){
		this.numElite = (int)Math.round(elitismo * tamPoblacion);
		elite = new Individuo[numElite];
		Collections.sort(poblacion);
		for(int i = 0; i < numElite; i++){
			elite[i] = poblacion.get(i).clonar();
		}
	}

	public void introducirElite() {
		Collections.sort(poblacion);
		for(int i = 0; i < numElite; i++){
			poblacion.set(tamPoblacion-1-i,elite[i]);
		}
		Collections.sort(poblacion);
	}

	public double calcularMediaGen(){
		double suma = 0;
		for(int i = 0; i < tamPoblacion; i++){
			suma += poblacion.get(i).getFitness();
		}
		return suma/tamPoblacion;
	}

	public double getMediaGen(){return mediaGen;}

	public Individuo getMejorIndividuo(){
		return elMejor;
	}
	//Getters y setters

	public int getTamPoblacion() {
		return tamPoblacion;
	}

	public void setTamPoblacion(int tamPoblacion) {
		this.tamPoblacion = tamPoblacion;
	}

	public ArrayList<Individuo> getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(ArrayList<Individuo> poblacion) {
		this.poblacion = poblacion;
	}

	public int getMaxGeneraciones() {
		return maxGeneraciones;
	}

	public void setMaxGeneraciones(int maxGeneraciones) {
		this.maxGeneraciones = maxGeneraciones;
	}

	public double getProbCruce() {
		return probCruce;
	}

	public void setProbCruce(double probCruce) {
		this.probCruce = probCruce;
	}

	public double getProbMutacion() {
		return probMutacion;
	}

	public void setProbMutacion(double probMutacion) {
		this.probMutacion = probMutacion;
	}

	public int getTamTorneo() {
		return tamTorneo;
	}

	public void setTamTorneo(int tamTorneo) {
		this.tamTorneo = tamTorneo;
	}

	public Seleccion getSel() {
		return sel;
	}

	public void setSel(Seleccion sel) {
		this.sel = sel;
	}

	public Cruce getCruce() {
		return cruce;
	}

	public void setCruce(Cruce cruce) {
		this.cruce = cruce;
	}

	public Mutacion getMut() {
		return mut;
	}

	public void setMut(Mutacion mut) {
		this.mut = mut;
	}

	public Funcion getFunc() {
		return func;
	}

	public void setFunc(Funcion func) {
		this.func = func;
	}

	public void setElitismo(double elitismo) {
		this.elitismo = elitismo;
	}

	public double getElitismo(){return elitismo;}

	public double getPrecision() {return precision;}

	public void setPrecision(double precision) {this.precision = precision;}

	public int getD() {return d;}
	public void setD(int d){this.d = d;}

	public Boolean esMejor(double a, double b){ // b mejor que a
		if(func.toString().equalsIgnoreCase("Funcion 1"))
			return a < b;
		else return a > b;
	}
}
