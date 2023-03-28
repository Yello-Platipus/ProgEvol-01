package View;

import Controller.Controller;
import Cositas.AlgoritmoGenetico;
import Cositas.Mutacion.*;
import Cositas.Seleccion.*;
import Cositas.Funcion.*;
import Cositas.Cruce.*;
import View.ConfigPanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.math.plot.*;

public class MainWindow extends JFrame {
    private Controller cont;
    private ConfigPanel cPanel;
    private String[] tipoIntervalo = {"Ninguno", "Porcentaje de mutacion", "Porcentaje de cruce", "Tamano de poblacion"};
    private Plot2DPanel plot;
    private AlgoritmoGenetico ag;
    private JLabel mejorSol;
    private JComboBox<String> tipo;
    private JTextField min, max;
    String mSol;
    private double[] numGen;
    private double[] mejorGen;
    private double[] mejorAbs;
    private double[] mediaGen;

    private double[] fitnessEjec;

    private double[] numIntervalo;

    public MainWindow(Controller cont){
        super("Panel de configuracion");
        this.cont = cont;
        cPanel = new ConfigPanel<AlgoritmoGenetico>();
        plot = new Plot2DPanel();
        tipo = new JComboBox<>(tipoIntervalo);
        min = new JTextField("");
        max = new JTextField("");
        ag = new AlgoritmoGenetico();
        cPanel.setTarget(ag);
        mSol = "Mejor solucion: ";
        init();
    }
    public void init(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        iniPanel();
        cPanel.initialize();
        JButton ejecBoton = new JButton("Ejecutar");
        ejecBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int minimo = Integer.parseInt(min.getText());
                    int maximo = Integer.parseInt(max.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce valores numericos en los campos de intervalo");
                    return;
                }
                cPanel.initialize();
                String aux = tipo.getSelectedItem().toString();
                cont.run(ag, Integer.parseInt(min.getText()), Integer.parseInt(max.getText()),aux );
                plot.removeAll();
                plot = new Plot2DPanel();
                if(aux.equalsIgnoreCase("Ninguno")){
                    iniGrafica();
                    mSol = cont.getMejorIndAbs().toString();
                }
                else{
                    iniGraficaInterval();
                    mSol = "La mejor ejecucion ha sido con " + cont.getMejorEjecX() +
                            " de " + aux.toLowerCase() + " con fitness: " + cont.getMejorAbs()[0];
                }
                mejorSol.setText(mSol);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true);
            }
        });

        mejorSol = new JLabel(mSol);
        JScrollPane sPane = new JScrollPane();
        sPane.setViewportView(mejorSol);
        JPanel panelSur = new JPanel(new BorderLayout());
        ejecBoton.setFont(new Font("Arial", Font.PLAIN, 20));
        panelSur.add(ejecBoton, BorderLayout.EAST);
        panelSur.add(sPane, BorderLayout.CENTER);

        this.add(panelSur, BorderLayout.SOUTH);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setMinimumSize(new Dimension(1000, 600));

        this.setVisible(true);
    }


    public void iniPanel(){
        cPanel.addOption(new IntegerOption<AlgoritmoGenetico>(
                "Poblacion", "Numero de individuos en la poblacion",
                "tamPoblacion", 0, Integer.MAX_VALUE));
        cPanel.addOption(new IntegerOption<AlgoritmoGenetico>(
                "Numero de generaciones", "Numero de generaciones a ejecutar",
                "maxGeneraciones", 0, Integer.MAX_VALUE));
        cPanel.addOption(new DoubleOption<AlgoritmoGenetico>(
                "Probabilidad de cruce", "Probabilidad de que se produzca un cruce entre dos individuos",
                "probCruce", 0, 1));
        cPanel.addOption(new DoubleOption<AlgoritmoGenetico>(
                "Probabilidad de mutacion", "Probabilidad de que se produzca una mutacion en un individuo",
                "probMutacion", 0, 1));
        cPanel.addOption(new DoubleOption<AlgoritmoGenetico>(
               "Precision", "Precision para la discretización del intervalo",
               "precision", 0, 1));
        cPanel.addOption(new ChoiceOption<AlgoritmoGenetico>(
                "Tipo de seleccion", "Tipo de seleccion a utilizar",
                "sel", new Seleccion[]{new SeleccionRuleta(), new SeleccionTorneoAleatoria(), new SeleccionTorneoDeterminista(),
                                                new SeleccionEstocasticaUniversal(), new SeleccionTruncamiento(), new SeleccionRestos(),
                                                new SeleccionRanking()}));
        cPanel.addOption(new IntegerOption<AlgoritmoGenetico>(
                "Tamanyo del torneo", "Tamanyo de torneo de la seleccion por torneo",
                "tamTorneo", 1, Integer.MAX_VALUE));
        cPanel.addOption(new IntegerOption<AlgoritmoGenetico>(
                "d", "Dimensiones de la funcion 4",
                "d", 1, Integer.MAX_VALUE));
        cPanel.addOption(new ChoiceOption<AlgoritmoGenetico>(
                "Tipo de funcion", "Tipo de funcion",
                "func", new Funcion[]{/*new Funcion1(), new Funcion2(), new Funcion3(),new Funcion4a(), new Funcion4b(),*/ new FuncionTSP()}));
        cPanel.addOption(new ChoiceOption<AlgoritmoGenetico>(
                "Tipo de cruce", "Tipo de cruce",
                "cruce", new Cruce[]{/*new CruceMonopunto(), new CruceUniforme(),*/ new CrucePMX(), new CruceOX(), new CruceOXPP(),
                                                new CruceOXOP(), new CruceCX(), new CruceCO(), new CruceParticion(), new CruceERX()}));
        cPanel.addOption(new ChoiceOption<AlgoritmoGenetico>(
                "Tipo de mutacion", "Tipo de mutacion",
                "mut", new Mutacion[]{/*new MutacionBasica(),*/ new MutacionInsercion(), new MutacionIntercambio(), new MutacionInversion(),
                                                new MutacionHeuristica(), new MutacionCombinada()}));
        cPanel.addOption(new DoubleOption<AlgoritmoGenetico>(
                "Proporcion de elite", "Proporcion de la poblacion que se guarda como elite",
                "elitismo", 0, 1));

        cPanel.add(tipo);
        JPanel intervalo = new JPanel(new FlowLayout());
        min.setPreferredSize(new Dimension(150, 30));
        max.setPreferredSize(new Dimension(150, 30));
        intervalo.add(min);
        intervalo.add(max);
        intervalo.setVisible(false);
        cPanel.add(intervalo);
        tipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tipo.getSelectedItem().toString().equals("Ninguno")){
                    intervalo.setVisible(true);
                }
                else{
                    intervalo.setVisible(false);
                }
            }
        });
        cPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.PAGE_AXIS));
        this.add(cPanel, BorderLayout.WEST);
    }

    public void iniGrafica(){
        // define your data

        numGen = cont.getNumGen();
        mejorGen = cont.getMejorGen();
        mejorAbs = cont.getMejorAbs();
        mediaGen = cont.getMediaGen();

        // create your PlotPanel (you can use it as a JPanel)


        // define the legend position
        plot.addLegend("SOUTH");

        // add a line plot to the PlotPanel
        //plot.addLinePlot("my plot", x, y/*numGen, mejorGen*/);

        plot.addLinePlot("Mejor Absoluto", numGen, mejorAbs);
        plot.addLinePlot("Mejor Generacion", numGen, mejorGen);
        plot.addLinePlot("Media Generacion", numGen, mediaGen);

        // put the PlotPanel in a JFrame like a JPanel

        this.add(plot, BorderLayout.CENTER);
    }


    private void iniGraficaInterval() {

        fitnessEjec = cont.getFitnessEjec();
        numIntervalo = cont.getNumInterval();

        plot.addLegend("SOUTH");
        plot.addLinePlot("Fitness", numIntervalo, fitnessEjec);
        this.add(plot, BorderLayout.CENTER);
    }
}
