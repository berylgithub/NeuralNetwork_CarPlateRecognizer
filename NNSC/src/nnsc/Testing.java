/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nnsc;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Yorozuya
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        NeuralNetwork nTest=new NeuralNetwork();
        nTest=nTest.loadModel("Neural Model2.data");
        nTest.loadDataTestFromFile("NN_testing2.data");
        BackPropagation bPTest=new BackPropagation(nTest);
        System.out.println("result : ");
        bPTest.testModel();
        
    }
    
}
