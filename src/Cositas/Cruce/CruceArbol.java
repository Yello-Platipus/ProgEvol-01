package Cositas.Cruce;

import Cositas.Individuo.Individuo;
import Util.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


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

                Queue<Tree> cola1 = new LinkedList<Tree>();
                Queue<Tree> cola2 = new LinkedList<Tree>();
                cola1.add(hijo1.getArbol());
                cola2.add(hijo2.getArbol());
                //Posiblemente no funcione
                int cont1 = 0;
                int cont2 = 0;

                while(cont1 < random1){
                    Tree aux = cola1.peek();
                    cola1.remove();
                    if(!aux.esTerminal){
                        cola1.add(aux.left);
                        cola1.add(aux.right);
                    }
                    cont1++;
                } while(cont2 < random2){
                    Tree aux = cola2.peek();
                    cola2.remove();
                    if(!aux.esTerminal){
                        cola2.add(aux.left);
                        cola2.add(aux.right);
                    }
                    cont2++;
                }

                Tree uno = cola1.peek();
                Tree dos = cola2.peek();
                Tree aux = new Tree(uno);
                uno.updateDepth();
                uno.updateSize();
                dos.updateDepth();
                dos.updateSize();

                uno.setTree(dos);
                dos.setTree(aux);
            }
            cruzados.add(hijo1);
            cruzados.add(hijo2);
        }
        if(tamPob % 2 == 1)
            cruzados.add(poblacion.get(tamPob - 1).clonar());
        return cruzados;
    }
}
