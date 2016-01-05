/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

/**
 *
 * @author Yorozuya
 */
public class Normalization {
    double normal;
    double[] arrNorm;
    int max;
    int input;
    int[] arrInput;

    public Normalization(int max, int input) {
        this.max = max;
        this.input = input;
    }
    public Normalization(int max, int[] arrInput){
        this.max=max;
        this.arrInput=arrInput;
    }
    public void normData(){
        normal=(double)input/max;
    }
    
    public void normArray(){
        arrNorm = new double[arrInput.length];
        for(int i=0; i<arrInput.length; i++){
            arrNorm[i]=(double)arrInput[i]/max;
        }
    }
    
    public void normArrayV2(double min, double max, double normMin, double normMax){
        arrNorm = new double[arrInput.length];
        for(int i=0; i<arrInput.length; i++){
            arrNorm[i]=(double)(((arrInput[i]-min)/(max-min))*(normMax-normMin))+normMin;
        }
    }

    public double getNormal() {
        return normal;
    }

    public double[] getArrNorm() {
        return arrNorm;
    }
    
}
