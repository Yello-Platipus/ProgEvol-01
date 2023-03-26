package Cositas.Mutacion;

import Cositas.Individuo.Individuo;

import java.util.ArrayList;
import java.util.Collections;

public class MutacionHeuristica extends Mutacion{
    @Override
    public String toString() {
        return "Mutacion heuristica";
    }

    @Override
    public void mutar(Individuo ind, double probMutacion) {
        if(Math.random() < probMutacion){
            ArrayList<Individuo> nuevos = new ArrayList<Individuo>();
            //3 Posiciones aleatorias
            int a = (int) (Math.random() * ind.getCromosoma().length);
            int b = (int) (Math.random() * ind.getCromosoma().length);
            while(b == a)
                b = (int) (Math.random() * ind.getCromosoma().length);
            int c = (int) (Math.random() * ind.getCromosoma().length);
            while(c == b || c == a)
                c = (int) (Math.random() * ind.getCromosoma().length);

            int[] seleccionados = {(int)ind.getCromosoma()[a],(int)ind.getCromosoma()[b],(int)ind.getCromosoma()[c]};
            ArrayList<int[]> permutaciones = permutar(seleccionados);

            for(int i = 0; i < 6; i++){
                Individuo aux = ind.clonar();
                aux.setCromosoma(a, permutaciones.get(i)[0]);
                aux.setCromosoma(b, permutaciones.get(i)[1]);
                aux.setCromosoma(c, permutaciones.get(i)[2]);
                nuevos.add(aux);
            }
            Collections.sort(nuevos);
            ind.setCromosoma(a, nuevos.get(0).getCromosoma()[a]);
            ind.setCromosoma(b, nuevos.get(0).getCromosoma()[b]);
            ind.setCromosoma(c, nuevos.get(0).getCromosoma()[c]);
        }
    }
    private ArrayList<int[]> permutar(int[] nums) {
        ArrayList<int[]> permutations = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (i != j && i != k && j != k) { // asegurarse de que los numeros sean diferentes
                        int[] perm = {nums[i], nums[j], nums[k]}; // crear una permutaciun
                        permutations.add(perm); // agregarla a la lista
                    }
                }
            }
        }
        return permutations;
    }
}
