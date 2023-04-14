package Cositas.Individuo;

import Cositas.Individuo.Constructores.Constructor;
import Util.Tree;

public class IndividuoRS extends Individuo<Object>{

    public static final double[] REAL= {1.0, 0.9615761599999999, 0.92621056, 0.8937649599999999, 0.8641049599999998, 0.8371, 0.8126233599999999, 0.7905521599999998,
            0.7707673599999999, 0.75315376, 0.7375999999999998, 0.7239985599999998, 0.7122457599999998, 0.7022417599999999, 0.6938905599999998, 0.6870999999999999,
            0.6817817599999999, 0.67785136, 0.67522816, 0.67383536, 0.6736, 0.67445296, 0.67632896, 0.6791665600000001, 0.6829081600000001, 0.6875000000000001,
            0.6928921600000002, 0.6990385600000002, 0.7058969600000002, 0.7134289600000002, 0.7216000000000002, 0.7303793600000003, 0.7397401600000003, 0.7496593600000003,
            0.7601177600000003, 0.7711000000000003, 0.7825945600000004, 0.7945937600000004, 0.8070937600000004, 0.8200945600000005, 0.8336000000000005, 0.8476177600000004,
            0.8621593600000005, 0.8772401600000005, 0.8928793600000005, 0.9091000000000005, 0.9259289600000005, 0.9433969600000005, 0.9615385600000006, 0.9803921600000006,
            1.0000000000000007, 1.0204081600000006, 1.0416665600000006, 1.0638289600000006, 1.0869529600000007, 1.1111000000000009, 1.1363353600000008, 1.1627281600000008,
            1.1903513600000009, 1.2192817600000008, 1.249600000000001, 1.281390560000001, 1.3147417600000009, 1.3497457600000011, 1.386498560000001, 1.4251000000000011,
            1.4656537600000012, 1.5082673600000014, 1.5530521600000013, 1.6001233600000015, 1.6496000000000017, 1.7016049600000018, 1.756264960000002, 1.8137105600000023,
            1.8740761600000022, 1.9375000000000027, 2.0041241600000026, 2.074094560000003, 2.147560960000003, 2.2246769600000036, 2.3056000000000036, 2.390491360000004,
            2.479516160000004, 2.5728433600000042, 2.6706457600000046, 2.7731000000000052, 2.8803865600000056, 2.9926897600000055, 3.1101977600000064, 3.2331025600000065,
            3.361600000000007, 3.4958897600000074, 3.6361753600000077, 3.7826641600000084, 3.9355673600000087, 4.095100000000009, 4.26148096000001, 4.43493296000001,
            4.615682560000011, 4.8039601600000115, 5.0};


    private Constructor constructor;
    private Tree arbol;
    private int minProf;
    private int maxProf;



    public IndividuoRS(double precision, int d) {
        super(precision, d);
    }

    public IndividuoRS(Constructor cons, int minProf, int maxProf){
        super(0.001, 2);
        this.constructor = cons;
        this.arbol = new Tree();
        constructor.construir(arbol, 1);
        this.minProf = minProf;
        this.maxProf = maxProf;
    }
    public IndividuoRS(IndividuoRS ind){
        super(ind.precision, ind.d);
        this.arbol = new Tree(ind.getArbol());
    }
    @Override
    public double getFitness() {
        double[] acum = new double[101];
        int cont = 0;
        for(double i = -1; i <= 1.01; i+=0.02){
            acum[cont++] = calcularValor(arbol, i);
        }
        double difAcum = 0;
        for(int i = 0; i < cont; i++){
            difAcum = Math.pow(REAL[i] - acum[i],2);
        }

        return Math.sqrt(difAcum);
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
                default:
                    res = 0;
            }
            return res;
        }

    }

    public Tree getArbol(){return arbol;}
    @Override
    public int compareTo(Individuo o) {
        if(this.getFitness() > o.getFitness())
            return 1;
        else if(this.getFitness() < o.getFitness())
            return -1;
        return 0;
    }
}
