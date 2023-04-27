package Cositas.Individuo;

import Cositas.Individuo.Constructores.Constructor;
import Util.Tree;
import View.MainWindow;

public class IndividuoRS extends Individuo<Object>{

    public static final double[] REAL=
            {1.0, 0.96157616, 0.92621056, 0.89376496, 0.86410496, 0.8371, 0.81262336,0.79055216, 0.77076736, 0.75315376,
            0.7376, 0.724, 0.71224576, 0.70224176, 0.69389056, 0.6871, 0.68178176, 0.67785136, 0.67522816, 0.67383536,
            0.6736, 0.67445296, 0.67632896, 0.67916656, 0.68290816, 0.6875, 0.69289216, 0.69903856, 0.70589696, 0.71342896,
            0.7216, 0.73037936, 0.73974016, 0.74965936, 0.76011776, 0.7711, 0.78259456, 0.79459376, 0.80709376, 0.82009456,
            0.8336, 0.84761776, 0.86215936, 0.87724016, 0.89287936, 0.9091, 0.92592896, 0.94339696, 0.96153856, 0.98039216,
            1.0, 1.02040816, 1.04166656, 1.06382896, 1.08695296, 1.1111, 1.13633536, 1.16272816, 1.19035136, 1.21928176,
            1.2496, 1.28139056, 1.31474176, 1.34974576, 1.38649856, 1.4251, 1.46565376, 1.50826736, 1.55305216, 1.60012336,
            1.6496, 1.70160496, 1.75626496, 1.81371056, 1.87407616, 1.9375, 2.00412416, 2.07409456, 2.14756096, 2.22467696,
            2.3056, 2.39049136, 2.47951616, 2.57284336, 2.67064576, 2.7731, 2.88038656, 2.99268976, 3.11019776, 3.23310256,
            3.3616, 3.49588976, 3.63617536, 3.78266416, 3.93556736, 4.0951, 4.26148096, 4.43493296, 4.61568256, 4.80396016, 5.0};



    private Constructor constructor;
    private Tree arbol;
    private int minProf;
    private int maxProf;
    private double[] calculado;


    public IndividuoRS(Constructor cons, int minProf, int maxProf){
        this.constructor = cons;
        this.arbol = new Tree();
        constructor.construir(arbol, 1);
        arbol.updateValues();
        this.minProf = minProf;
        this.maxProf = maxProf;
    }
    public IndividuoRS(IndividuoRS ind){
        this.arbol = new Tree(ind.getArbol());
    }

    @Override
    public String toString() {
        return arbol.toString() + " " + getFitness();
    }

    @Override
    public double getFitness() {
        calculado = new double[101];
        for(int i = 0; i < 101; i++){
            calculado[i] = calcularValor(arbol, MainWindow.xValues[i]);
        }
        double difAcum = 0;
        for(int i = 0; i < 101; i++){
            difAcum += Math.pow(REAL[i] - calculado[i],2);
        }

        return Math.sqrt(difAcum);
    }

    public double getFenotipo(int x) {
        return 0;
    }

    public void setArbol(Tree antiguo, Tree nuevo) {

    }

    @Override
    public Object nextRandom() {
        return null;
    }

    @Override
    public Individuo clonar() {
        return new IndividuoRS(this);
    }

    private double calcularValor(Tree n, double x){
        if(n.esTerminal){
            if(n.value.equals("x"))
                return x;
            else
                return Integer.parseInt(n.value);
        }
        else{
            double izq = 0, der = 0;
            izq = calcularValor(n.left, x);
            der = calcularValor(n.right, x);

            double res = 0;
            switch (n.value){
                case "add":
                    res = izq + der;
                    break;
                case "sub":
                    res = izq - der;
                    break;
                case "mul":
                    res = izq * der;
                    break;
                default:
                    res = 0;
            }
            return res;
        }

    }

    @Override
    public Tree getArbol(){return arbol;}

    @Override
    public double[] getGraf() {
        return calculado;
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
