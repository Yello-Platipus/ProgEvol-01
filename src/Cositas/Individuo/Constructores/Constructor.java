package Cositas.Individuo.Constructores;

import Util.Tree;

public abstract class Constructor {
    protected final int minProf = 2;
    protected final int maxProf = 5;
    public static final String[] funciones = {"add", "sub", "mul"};
    public static final String[] terminales = {"x", "-2", "-1", "0", "1", "2"};

    public abstract void construir(Tree n, int prof);
    public abstract String toString();
}
