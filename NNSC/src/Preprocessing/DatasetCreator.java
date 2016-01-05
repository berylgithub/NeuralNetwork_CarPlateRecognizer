/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yorozuya
 */
public class DatasetCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        try {
//            // TODO code application logic here
////            LoadImage load=new LoadImage("D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\Dataset_new\\35.PNG");
//////            ProjectionHistogram proj=new ProjectionHistogram(load.img);
//////            proj.cekProj();
//////            proj.saveToFile("Projection Histogram.data");
////            ImgResizer imResize=new ImgResizer(load.getImg());
////            imResize.resizeImage(16);
////            load.saveImage(imResize.getImg(), "D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\Dataset_new\\35_resize.PNG");
////            ProjectionHistogram proj=new ProjectionHistogram(imResize.getImg());
////            proj.cekProj();
////            proj.saveToFile("Projection Histogram.data");
//            
//            
//            
//            //bikin dataset
//            for(int i=0; i<36; i++){
//                LoadImage load=new LoadImage("D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\Dataset_new\\"+i+".PNG");
//                ImgResizer imgRes=new ImgResizer(load.getImg());
//                imgRes.resizeImage(16);
//                load.saveImage(imgRes.getImg(), "D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\Dataset_new\\"+i+"_16.PNG");
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(DatasetCreator.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
       for(int i=0; i<36; i++){
           for(int j=1; j<12; j++){
               LoadImage load=new LoadImage("D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\dataset (sorted)\\"+i+"_"+j+".PNG");
               ImgResizer imgRes=new ImgResizer(load.getImg());
               imgRes.resizeImage(16);
               load.saveImage(imgRes.getImg(), "D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\Dataset_new_resize_16\\"+i+"_"+j+"_resize16.PNG");
           }
       }
        
    }
    
}
