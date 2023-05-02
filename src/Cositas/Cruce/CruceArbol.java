package Cositas.Cruce;

import Cositas.AlgoritmoGenetico;
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
                //Escogemos una posicion aleatoria del arbol
                int random1 = 1+(int)((hijo1.getArbol().getSize()-1) * Math.random());
                int random2 = 1+(int)((hijo2.getArbol().getSize()-1) * Math.random());

                Queue<Tree> cola1 = new LinkedList<Tree>();
                Queue<Tree> cola2 = new LinkedList<Tree>();

                //Hacemos push de la raíz
                cola1.add(hijo1.getArbol());
                cola2.add(hijo2.getArbol());


                int cont1 = 0;
                int cont2 = 0;

                //Avanzamos el bucle hasta llegar a la posición aleatoria o hasta el último elemento del árbol
                while(cont1 < random1 && !cola1.isEmpty()){
                    Tree aux = cola1.peek();
                    cola1.remove();
                    if(!aux.esTerminal){
                        cola1.add(aux.left);
                        cola1.add(aux.right);
                    }
                    cont1++;
                } while(cont2 < random2 && !cola2.isEmpty()){
                    Tree aux = cola2.peek();
                    cola2.remove();
                    if(!aux.esTerminal){
                        cola2.add(aux.left);
                        cola2.add(aux.right);
                    }
                    cont2++;
                }

                //Intercambiamos los nodos
                Tree uno = cola1.peek();
                Tree dos = cola2.peek();
                Tree aux = new Tree(uno);

                uno.setTree(dos);
                dos.setTree(aux);

                //Actualizamos los valores del árbol
                hijo1.getArbol().updateValues();
                hijo1.updateFitness();
                hijo2.getArbol().updateValues();
                hijo2.updateFitness();
            }
            /*Control de bloating, cruce no destructivo
            No lo usamos porque hemos visto que reducía drásticamente la media del fitness de la población
            y muchas veces provocaba convergencias prematuras

            if(AlgoritmoGenetico.getBloating()){
                if(hijo1.getFitness() < padre1.getFitness())
                    cruzados.add(hijo1);
                else
                    cruzados.add(padre1);
                if(hijo2.getFitness() < padre2.getFitness())
                    cruzados.add(hijo2);
                else
                    cruzados.add(padre2);
            }
            else{
                cruzados.add(hijo1);
                cruzados.add(hijo2);
            }*/
            cruzados.add(hijo1);
            cruzados.add(hijo2);
        }
        if(tamPob % 2 == 1)
            cruzados.add(poblacion.get(tamPob - 1).clonar());
        return cruzados;
    }
}
