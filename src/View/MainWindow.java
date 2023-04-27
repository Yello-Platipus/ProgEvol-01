package View;

import Controller.Controller;
import Cositas.AlgoritmoGenetico;
import Cositas.Individuo.Constructores.Constructor;
import Cositas.Individuo.Constructores.ConstructorCompleto;
import Cositas.Individuo.Constructores.ConstructorCreciente;
import Cositas.Individuo.Constructores.ConstructorRampedHalf;
import Cositas.Individuo.IndividuoRS;
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
    private ConfigPanel panelCruce;
    private ConfigPanel panelMutacion;
    private ConfigPanel panelTamPoblacion;
    private ConfigPanel panelNumGeneraciones;
    private ConfigPanel panelPrecision;
    private ConfigPanel panelTipoSeleccion;
    private ConfigPanel panelTamTorneo;
    private ConfigPanel panelD;
    private ConfigPanel panelTipoConstructor;
    private ConfigPanel panelTipoFuncion;
    private ConfigPanel panelTipoCruce;
    private ConfigPanel panelTipoMutacion;
    private ConfigPanel panelElite;
    private String[] tipoIntervalo = {"Ninguno", "Porcentaje de mutacion", "Porcentaje de cruce", "Tamano de poblacion"};
    private Plot2DPanel plot;

    private boolean evolution;
    private AlgoritmoGenetico ag;
    private JLabel mejorSol;
    private JComboBox<String> tipo;
    private JTextField min, max;
    private String mSol;
    private JCheckBox bloating;
    private double[] numGen;
    private double[] mejorGen;
    private double[] mejorAbs;
    private double[] mediaGen;
    private double[] fitnessEjec;
    private double[] numIntervalo;
    private double[] mejorGraf;

    public static final double[] xValues =
            {-1.00, -0.98, -0.96, -0.94, -0.92, -0.90, -0.88, -0.86, -0.84, -0.82, -0.80, -0.78, -0.76, -0.74, -0.72,
            -0.70, -0.68, -0.66, -0.64, -0.62, -0.60, -0.58, -0.56, -0.54,-0.52, -0.50, -0.48, -0.46, -0.44, -0.42,
            -0.40, -0.38, -0.36, -0.34, -0.32, -0.30, -0.28, -0.26, -0.24, -0.22, -0.20, -0.18, -0.16, -0.14, -0.12,
            -0.10, -0.08, -0.06, -0.04, -0.02, 0.00, 0.02, 0.04, 0.06, 0.08, 0.10, 0.12, 0.14, 0.16, 0.18, 0.20, 0.22,
            0.24, 0.26, 0.28, 0.30, 0.32, 0.34, 0.36, 0.38, 0.40, 0.42, 0.44, 0.46, 0.48,  0.50, 0.52, 0.54, 0.56, 0.58,
            0.60, 0.62, 0.64, 0.66, 0.68, 0.70, 0.72, 0.74, 0.76, 0.78, 0.80, 0.82, 0.84, 0.86, 0.88, 0.90, 0.92, 0.94,
            0.96, 0.98, 1.00};
    public MainWindow(Controller cont){
        super("Panel de configuracion");
        this.cont = cont;
        cPanel = new ConfigPanel<AlgoritmoGenetico>();
        panelCruce = new ConfigPanel<AlgoritmoGenetico>();
        panelMutacion = new ConfigPanel<AlgoritmoGenetico>();
        panelTamPoblacion = new ConfigPanel<AlgoritmoGenetico>();
        panelNumGeneraciones = new ConfigPanel<AlgoritmoGenetico>();
        panelPrecision = new ConfigPanel<AlgoritmoGenetico>();
        panelTipoSeleccion = new ConfigPanel<AlgoritmoGenetico>();
        panelTamTorneo = new ConfigPanel<AlgoritmoGenetico>();
        panelD = new ConfigPanel<AlgoritmoGenetico>();
        panelTipoConstructor = new ConfigPanel<AlgoritmoGenetico>();
        panelTipoFuncion = new ConfigPanel<AlgoritmoGenetico>();
        panelTipoCruce = new ConfigPanel<AlgoritmoGenetico>();
        panelTipoMutacion = new ConfigPanel<AlgoritmoGenetico>();
        panelElite = new ConfigPanel<AlgoritmoGenetico>();
        plot = new Plot2DPanel();
        tipo = new JComboBox<>(tipoIntervalo);
        min = new JTextField("");
        max = new JTextField("");
        ag = new AlgoritmoGenetico();
        cPanel.setTarget(ag);
        panelCruce.setTarget(ag);
        panelMutacion.setTarget(ag);
        panelTamPoblacion.setTarget(ag);
        panelNumGeneraciones.setTarget(ag);
        panelPrecision.setTarget(ag);
        panelTipoSeleccion.setTarget(ag);
        panelTamTorneo.setTarget(ag);
        panelD.setTarget(ag);
        panelTipoConstructor.setTarget(ag);
        panelTipoFuncion.setTarget(ag);
        panelTipoCruce.setTarget(ag);
        panelTipoMutacion.setTarget(ag);
        panelElite.setTarget(ag);
        mSol = "Mejor solucion: ";
        init();
    }
    public void init(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        iniPanel();
        cPanel.initialize();
        panelCruce.initialize();
        panelMutacion.initialize();
        panelTamPoblacion.initialize();
        panelNumGeneraciones.initialize();
        panelPrecision.initialize();
        panelTipoSeleccion.initialize();
        panelTamTorneo.initialize();
        panelD.initialize();
        panelTipoConstructor.initialize();
        panelTipoFuncion.initialize();
        panelTipoCruce.initialize();
        panelTipoMutacion.initialize();
        panelElite.initialize();
        JButton ejecBoton = new JButton("Ejecutar");
        ejecBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double minimo = 0;
                double maximo = 0;
                String aux = tipo.getSelectedItem().toString();
                if(!aux.equalsIgnoreCase("Ninguno")) {
                    try {
                        minimo = Double.parseDouble(min.getText());
                        maximo = Double.parseDouble(max.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Introduce valores numericos en los campos de intervalo");
                        return;
                    }
                }
                cPanel.initialize();
                panelCruce.initialize();
                panelMutacion.initialize();
                panelTamPoblacion.initialize();
                panelNumGeneraciones.initialize();
                panelPrecision.initialize();
                panelTipoSeleccion.initialize();
                panelTamTorneo.initialize();
                panelD.initialize();
                panelTipoConstructor.initialize();
                panelTipoFuncion.initialize();
                panelTipoCruce.initialize();
                panelTipoMutacion.initialize();
                panelElite.initialize();
                ag.setBloating(bloating.isSelected());
                cont.run(ag, minimo, maximo, aux);
                plot = new Plot2DPanel();
                if(aux.equalsIgnoreCase("Ninguno")){
                    iniGrafica();
                    mSol =  "Mejor sol: "  + cont.getMejorIndAbs().toString();
                }
                else{
                    iniGraficaInterval();
                    mSol = "La mejor ejecucion ha sido con " + cont.getMejorEjecX() +
                            " de " + aux.toLowerCase() + ", con fitness: " + cont.getMejorAbs()[0];
                }
                mejorSol.setText(mSol);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true);
            }
        });

        JButton switchGraphButton = new JButton("Cambiar grafica");
        switchGraphButton.setFont(new Font("Arial", Font.PLAIN, 20));
        switchGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(evolution){
                    plot = new Plot2DPanel();
                    iniGrafComparativa();
                }
                else{
                    plot = new Plot2DPanel();
                    iniGrafica();
                }
                setVisible(true);
            }
        });
        mejorSol = new JLabel(mSol);
        JScrollPane sPane = new JScrollPane();
        sPane.setViewportView(mejorSol);
        JPanel panelSur = new JPanel(new BorderLayout());
        ejecBoton.setFont(new Font("Arial", Font.PLAIN, 20));

        panelSur.add(ejecBoton, BorderLayout.WEST);
        panelSur.add(switchGraphButton, BorderLayout.EAST);
        panelSur.add(sPane, BorderLayout.CENTER);

        this.add(panelSur, BorderLayout.SOUTH);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setMinimumSize(new Dimension(1000, 600));

        this.setVisible(true);
    }


    public void iniPanel(){
        panelTamPoblacion.addOption(new IntegerOption<AlgoritmoGenetico>(
                "Poblacion", "Numero de individuos en la poblacion",
                "tamPoblacion", 0, Integer.MAX_VALUE));
        cPanel.add(panelTamPoblacion);
        panelNumGeneraciones.addOption(new IntegerOption<AlgoritmoGenetico>(
                "Numero de generaciones", "Numero de generaciones a ejecutar",
                "maxGeneraciones", 0, Integer.MAX_VALUE));
        cPanel.add(panelNumGeneraciones);
        panelCruce.addOption(new DoubleOption<AlgoritmoGenetico>(
                "Probabilidad de cruce", "Probabilidad de que se produzca un cruce entre dos individuos",
                "probCruce", 0, 1));
        cPanel.add(panelCruce);        panelMutacion.addOption(new DoubleOption<AlgoritmoGenetico>(
                "Probabilidad de mutacion", "Probabilidad de que se produzca una mutacion en un individuo",
                "probMutacion", 0, 1));
        cPanel.add(panelMutacion);
        panelTipoSeleccion.addOption(new ChoiceOption<AlgoritmoGenetico>(
                "Tipo de seleccion", "Tipo de seleccion a utilizar",
                "sel", new Seleccion[]{new SeleccionRanking(), new SeleccionRuleta(), new SeleccionTorneoAleatoria(), new SeleccionTorneoDeterminista(),
                                                new SeleccionEstocasticaUniversal(), new SeleccionTruncamiento(), new SeleccionRestos()}));
        cPanel.add(panelTipoSeleccion);
        panelTamTorneo.addOption(new IntegerOption<AlgoritmoGenetico>(
                "Tamano del torneo", "Tamano de torneo de la seleccion por torneo",
                "tamTorneo", 1, Integer.MAX_VALUE));
        cPanel.add(panelTamTorneo);
        panelTipoConstructor.addOption(new ChoiceOption<AlgoritmoGenetico>(
                "Tipo de constructor", "Tipo de constructor a utilizar",
                "cons", new Constructor[]{new ConstructorCompleto(), new ConstructorRampedHalf(),
                                                new ConstructorCreciente()}));
        cPanel.add(panelTipoConstructor);
        panelTipoCruce.addOption(new ChoiceOption<AlgoritmoGenetico>(
                "Tipo de cruce", "Tipo de cruce",
                "cruce", new Cruce[]{new CruceArbol()}));
        cPanel.add(panelTipoCruce);
        panelTipoMutacion.addOption(new ChoiceOption<AlgoritmoGenetico>(
                "Tipo de mutacion", "Tipo de mutacion",
                "mut", new Mutacion[]{new MutacionFuncional(), new MutacionArbolSubarbol(), new MutacionTerminal()}));
        cPanel.add(panelTipoMutacion);
        panelElite.addOption(new DoubleOption<AlgoritmoGenetico>(
                "Proporcion de elite", "Proporcion de la poblacion que se guarda como elite",
                "elitismo", 0, 1));
        cPanel.add(panelElite);

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
                if(tipo.getSelectedItem().toString().equals("Ninguno")){
                    panelMutacion.setVisible(true);
                    panelCruce.setVisible(true);
                    panelTamPoblacion.setVisible(true);
                    intervalo.setVisible(false);
                }
                else{
                    if(tipo.getSelectedItem().toString().equals("Porcentaje de mutacion")){
                        panelMutacion.setVisible(false);
                        panelCruce.setVisible(true);
                        panelTamPoblacion.setVisible(true);
                    }
                    else if(tipo.getSelectedItem().toString().equals("Porcentaje de cruce")){
                        panelMutacion.setVisible(true);
                        panelCruce.setVisible(false);
                        panelTamPoblacion.setVisible(true);
                    }
                    else if(tipo.getSelectedItem().toString().equals("Tamano de poblacion")){
                        panelMutacion.setVisible(true);
                        panelCruce.setVisible(true);
                        panelTamPoblacion.setVisible(false);
                    }
                    intervalo.setVisible(true);
                }
            }
        });

        bloating = new JCheckBox("Bloating", false);
        cPanel.add(bloating);

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
        evolution = true;
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

    private void iniGrafComparativa(){
        mejorGraf = cont.getMejorGraf();
        evolution = false;
        plot.addLegend("SOUTH");
        plot.addLinePlot("Mejor individuo", xValues, mejorGraf);
        plot.addLinePlot("Funcion original", xValues, IndividuoRS.REAL);

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
