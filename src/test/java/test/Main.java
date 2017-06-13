/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import forms.CalculatorForm;
import javax.swing.JFrame;

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
              
                JFrame main = new CalculatorForm();
                main.pack();
                main.setLocationRelativeTo(null);
                main.setVisible(true);

    }
    
}
