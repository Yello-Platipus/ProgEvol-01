package Launcher;

import Controller.Controller;
import View.MainWindow;

import javax.swing.*;
import java.sql.SQLOutput;



import static Cositas.Individuo.IndividuoRS.REAL;


public class Main {
    public static void main(String[] args) {

        Controller c = new Controller();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mw = new MainWindow(c);
            }
        });/*
        System.out.print("{");
        for(int i = 0; i < 101; i++){
            double x = MainWindow.xValues[i];
            System.out.print(x*x*x*x+x*x*x+x*x+x+1 + ", ");
        }
        System.out.print("};");*/
    }

}
