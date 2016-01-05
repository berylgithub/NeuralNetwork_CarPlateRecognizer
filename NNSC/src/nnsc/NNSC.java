/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nnsc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Yorozuya
 */
public class NNSC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        // TODO code application logic here
        
        
        //tes struktur
//        NeuralNetwork n1= new NeuralNetwork();
//        n1.createInputLayer(3);
//        n1.createHiddenLayer(4);
//        n1.createOutLayer(5);
//        n1.generateWeight();
//        n1.setWeight();
//        
//        //print node+weight
//        System.out.println("Jumlah Input Node : "+n1.inLayer.arrInNode.size());
//        System.out.println("Jumlah Hidden Node : "+n1.hidLayer.arrHidNode.size());
//        System.out.println("Jumlah Output Node : "+n1.outLayer.arrOutNode.size());
//        System.out.println("Jumlah Weight : "+n1.arrWeight.size());
//        
//        
//        n1.arrWeight.get(0).vWeight=2;
//        n1.inLayer.arrInNode.get(0).inputValue=3;
//        
//        //penting
//        n1.inLayer.arrInNode.get(0).weightOut.get(0).setVIn();
//        n1.inLayer.arrInNode.get(0).weightOut.get(0).calVOut();
//        //
//        
//        System.out.println(n1.hidLayer.arrHidNode.get(0).wIn.get(0).vInput);
//        System.out.println(n1.hidLayer.arrHidNode.get(0).wIn.get(0).vWeight);
//        System.out.println(n1.hidLayer.arrHidNode.get(0).wIn.get(0).vOut);
//        System.out.println();
//        
//        //tes kesambungan data antar node dan weight
//        n1.outLayer.arrOutNode.get(0).error=10.0;
//        for(int i=0; i<n1.arrWeight.size(); i++){
//            if(n1.arrWeight.get(i).nNext.getClass().equals(OutputNode.class)){
//                System.out.println(((OutputNode)n1.arrWeight.get(i).nNext).error);
//                //System.out.println(n1.arrWeight.get(i).vWeight);
//            }
//            
//        }
//        System.out.print("1"+"\t");
//        for(int i=0; i<35; i++){
//            System.out.print("0"+"\t");
//        }
        
        //tes save&load data serializable NN
//        NeuralNetwork n2=new NeuralNetwork();
//        try {
//            n1.saveModel(n1,"Neural Model.data");
//        } catch (IOException ex) {
//            Logger.getLogger(NNSC.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            n2=n2.loadModel("Neural Model.data");
//        } catch (IOException ex) {
//            Logger.getLogger(NNSC.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(NNSC.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(n2.outLayer.arrOutNode.get(0).error);
//        System.out.println(n2.arrWeight.get(0).vWeight);
        
        //tes load datatraining from file
//        try {
//            n1.loadDataSetTrainingFromFile("NN_pattern_target.data");
//        } catch (IOException ex) {
//            Logger.getLogger(NNSC.class.getName()).log(Level.SEVERE, null, ex);
//        }
        NeuralNetwork n1=new NeuralNetwork();
        System.out.println("inisiasi....");
        try {
            n1.loadDataSetTrainingFromFile("NN_pattern_target2.data");
        } catch (IOException ex) {
            Logger.getLogger(NNSC.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("training NN model");
        n1.createInputLayer(32);
        n1.createHiddenLayer(34);
        n1.createOutLayer(36);
        n1.generateWeight();
        n1.setWeight();
        BackPropagation bp=new BackPropagation(n1);
        bp.setEpoch(5000);
        bp.setLr(0.5);
        bp.trainModel();
        n1.saveModel(n1, "Neural Model2.data");
        System.out.println("finish....");
    }
    
}
