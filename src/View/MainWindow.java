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

    private double[] xValues = {-1.0, -0.98, -0.96, -0.94, -0.9199999999999999, -0.8999999999999999, -0.8799999999999999,
                                -0.8599999999999999, -0.8399999999999999, -0.8199999999999998, -0.7999999999999998, -0.7799999999999998,
                                -0.7599999999999998, -0.7399999999999998, -0.7199999999999998, -0.6999999999999997, -0.6799999999999997,
                                -0.6599999999999997, -0.6399999999999997, -0.6199999999999997, -0.5999999999999996, -0.5799999999999996,
                                -0.5599999999999996, -0.5399999999999996, -0.5199999999999996, -0.49999999999999956, -0.47999999999999954,
                                -0.4599999999999995, -0.4399999999999995, -0.4199999999999995, -0.39999999999999947, -0.37999999999999945,
                                -0.35999999999999943, -0.3399999999999994, -0.3199999999999994, -0.2999999999999994, -0.27999999999999936,
                                -0.25999999999999934, -0.23999999999999935, -0.21999999999999936, -0.19999999999999937, -0.17999999999999938,
                                -0.1599999999999994, -0.1399999999999994, -0.1199999999999994, -0.0999999999999994, -0.07999999999999939,
                                -0.05999999999999939, -0.03999999999999938, -0.019999999999999383, 6.175615574477433E-16, 0.020000000000000618,
                                0.04000000000000062, 0.06000000000000062, 0.08000000000000063, 0.10000000000000063, 0.12000000000000063, 0.14000000000000062,
                                0.16000000000000061, 0.1800000000000006, 0.2000000000000006, 0.22000000000000058, 0.24000000000000057, 0.26000000000000056,
                                0.2800000000000006, 0.3000000000000006, 0.3200000000000006, 0.34000000000000064, 0.36000000000000065, 0.38000000000000067,
                                0.4000000000000007, 0.4200000000000007, 0.4400000000000007, 0.46000000000000074, 0.48000000000000076, 0.5000000000000008,
                                0.5200000000000008, 0.5400000000000008, 0.5600000000000008, 0.5800000000000008, 0.6000000000000009, 0.6200000000000009,
                                0.6400000000000009, 0.6600000000000009, 0.6800000000000009, 0.700000000000001, 0.720000000000001, 0.740000000000001,
                                0.760000000000001, 0.780000000000001, 0.800000000000001, 0.8200000000000011, 0.8400000000000011, 0.8600000000000011,
                                0.8800000000000011, 0.9000000000000011, 0.9200000000000012, 0.9400000000000012, 0.9600000000000012, 0.9800000000000012, 1.000000000000001};

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
                plot.removeAll();
                plot = new Plot2DPanel();
                if(aux.equalsIgnoreCase("Ninguno")){
                    iniGrafica();
                    //iniGrafComparativa();
                    mSol = cont.getMejorIndAbs().toString();
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
