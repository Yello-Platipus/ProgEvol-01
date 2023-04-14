package Cositas.Individuo.Constructores;

public class ConstructorCompleto extends Constructor{
    @Override
    public void construir(Node n, int prof) {
        if(prof != maxProf) {
            n = new Node(funciones[(int) (Math.random() * funciones.length)]);
            n.esTerminal = false;
            construir(n.left, prof + 1);
            construir(n.right, prof + 1);
        }
        else{
            n = new Node(terminales[(int) (Math.random() * terminales.length)]);
            n.esTerminal = true;
        }
    }

    @Override
    public String toString() {
        return "Constructor Completo";
    }
}
