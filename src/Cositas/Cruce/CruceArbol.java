package Cositas.Cruce;

import Cositas.Individuo.Individuo;
import Util.Tree;

import java.util.ArrayList;
import java.util.Stack;

public class CruceArbol extends Cruce{
    @Override
    public String toString() {
        return "Operador de cruce del arbol";
    }

    @Override
    public ArrayList<Individuo> cruzar(ArrayList<Individuo> poblacion, double probCruce) {
        ArrayList<Individuo> cruzados = new ArrayList<Individuo>();
        tamPob = poblacion.size();
        for(int i = 0; i <  tamPob -1; i+=2){
            Individuo padre1 = poblacion.get(i);
            Individuo padre2 = poblacion.get(i + 1);
            Individuo hijo1 = padre1.clonar();
            Individuo hijo2 = padre2.clonar();
            if(Math.random() < probCruce){
                int random1 = (int)(hijo1.getArbol().getSize() * Math.random());
                int random2 = (int)(hijo2.getArbol().getSize() * Math.random());

                Stack<Tree> pila1 = new Stack<>();
                Stack<Tree> pila2 = new Stack<>();
                pila1.push(padre1.getArbol());
                pila2.push(padre2.getArbol());
                //Posiblemente no funcione
                int cont1 = 0;
                int cont2 = 0;

                while(cont1 < random1){
                    Tree aux = pila1.peek();
                    pila1.pop();
                    if(!aux.esTerminal){
                        pila1.push(aux.left);
                        pila1.push(aux.right);
                    }
                } while(cont2 < random2){
                    Tree aux = pila2.peek();
                    pila2.pop();
                    if(!aux.esTerminal){
                        pila2.push(aux.left);
                        pila2.push(aux.right);
                    }
                }
                Tree uno = pila1.peek();
                Tree dos = pila2.peek();
                Tree aux = new Tree(uno);
                uno = new Tree(dos);
                dos = new Tree(aux);
            }
            cruzados.add(hijo1);
            cruzados.add(hijo2);
        }
        return cruzados;
    }
}
