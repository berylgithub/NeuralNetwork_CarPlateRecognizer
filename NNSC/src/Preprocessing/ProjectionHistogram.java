/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Yorozuya
 */
public class ProjectionHistogram {
    BufferedImage img;
    int[] pH, pV;
    int[][] arrImg;
    public ProjectionHistogram() {
    }

    public ProjectionHistogram(BufferedImage img) {
        this.img = img;
        setArraySize();
        convertRGBtoArray();
        searchPH();
        searchPV();
    }
    public void setArraySize(){
        arrImg=new int[img.getHeight()][img.getWidth()];
    }
    public void convertRGBtoArray(){
        for(int i=0;i<img.getHeight();i++){
            for(int j=0;j<img.getWidth();j++){
                Color c = new Color(img.getRGB(j, i));
                arrImg[i][j]=c.getRed();
                if(arrImg[i][j]<200){
                    arrImg[i][j]=0;
                }
                else{
                    arrImg[i][j]=255;
                }
                System.out.print(arrImg[i][j]+"\t");    
            }
            System.out.println();
        }
    }
    public void searchPH(){
        int y=arrImg.length;
        int x=arrImg[0].length;
        pH=new int[y];
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                if(arrImg[i][j]==0){
                    pH[i]++;
                }
            }
        }
    }
    public void searchPV(){
        int y=arrImg.length;
        int x=arrImg[0].length;
        pV=new int[x];
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                if(arrImg[i][j]==0){
                    pV[j]++;
                }
            }
        }
    }
    
    public int[] getPH(){
        return pH;
        
    }
    public int[] getPV(){
        return pV;
        
    }
    public void cekProj(){
//        for(int i=0; i<pH.length; i++){
//            System.out.println(i+" "+pH[i]);
//        }
//        for(int i=0; i<pV.length; i++){
//            System.out.println(i+" "+pV[i]);
//        }
        for(int i=0; i<pH.length; i++){
            System.out.print(pH[i]+"\t");
        }
        for(int i=0; i<pV.length; i++){
            System.out.print(pV[i]+"\t");
        }
    }
    
    public void saveToFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        for(int i=0; i<pH.length; i++){
            writer.print(pH[i]+"\t");
        }
        for(int i=0; i<pV.length; i++){
            writer.print(pV[i]+"\t");
        }
        writer.println();
        writer.close();
    }
    public void saveAllToFile(PrintWriter writer) throws FileNotFoundException, UnsupportedEncodingException{
        for(int i=0; i<pH.length; i++){
            writer.print(pH[i]+"\t");
        }
        for(int i=0; i<pV.length; i++){
            writer.print(pV[i]+"\t");
        }
        writer.println();
    }
}
