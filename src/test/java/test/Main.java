/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import forms.CalculatorForm;
import javax.swing.JFrame;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Milica
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                BasicConfigurator.configure();
                JFrame main = new CalculatorForm();
                main.pack();
                main.setLocationRelativeTo(null);
                main.setVisible(true);

    }
    
}
