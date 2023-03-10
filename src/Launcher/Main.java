package Launcher;

import Controller.Controller;
import View.MainWindow;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mw = new MainWindow(c);
            }
        });
    }
}
