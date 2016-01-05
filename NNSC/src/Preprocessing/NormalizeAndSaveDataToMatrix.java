/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Yorozuya
 */
public class NormalizeAndSaveDataToMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        //create Dataset Training
//        PrintWriter writer = new PrintWriter("NN Normalized Input.data", "UTF-8");
//        for(int i=0; i<36; i++){
//            LoadImage load=new LoadImage("D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\Dataset_new\\"+i+"_16.PNG");
//            ProjectionHistogram proj=new ProjectionHistogram(load.getImg());
//            NormPrinter printer=new NormPrinter();
//            Normalization norm=new Normalization(16, proj.getPH());
//            norm.normArrayV2(0, 16, 0.1, 0.9);
//            printer.setpH(norm.getArrNorm());
//            norm=new Normalization(16, proj.getPV());
//            norm.normArrayV2(0, 16, 0.1, 0.9);
//            printer.setpV(norm.getArrNorm());
//            printer.printNorm(writer);
//        }
//        writer.close();
        
        
        //bikin semua dataset jadi matrix P
        PrintWriter writer = new PrintWriter("NN Normalized Input V2.data", "UTF-8");
        for(int i=1; i<12; i++){
            for(int j=0; j<36; j++){
                LoadImage load=new LoadImage("D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\Dataset_new_resize_16\\"+j+"_"+i+"_resize16.PNG");
                ProjectionHistogram proj=new ProjectionHistogram(load.getImg());
                NormPrinter printer=new NormPrinter();
                Normalization norm=new Normalization(16, proj.getPH());
                norm.normArrayV2(0, 16, 0.1, 0.9);
                printer.setpH(norm.getArrNorm());
                norm=new Normalization(16, proj.getPV());
                norm.normArrayV2(0, 16, 0.1, 0.9);
                printer.setpV(norm.getArrNorm());
                printer.printNorm(writer);
            }
        }
        writer.close();
        
    }
    
}
