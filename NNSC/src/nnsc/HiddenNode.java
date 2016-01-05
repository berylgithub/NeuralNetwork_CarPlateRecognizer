/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nnsc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Yorozuya
 */
public class HiddenNode extends Node implements Serializable{
    double bias, resultValue, error;
    ArrayList<Weight> wIn=new ArrayList<>(), wOut=new ArrayList<>();

    public HiddenNode() {
        bias=0;
        resultValue=0;
        error=0;
    }

    public HiddenNode(double bias, double resultValue, ArrayList<Weight> wIn, ArrayList<Weight> wOut) {
        this.bias = bias;
        this.resultValue = resultValue;
        this.wIn = wIn;
        this.wOut = wOut;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getResultValue() {
        return resultValue;
    }
    
    public void sumFunction(){
        for(int i=0; i<wIn.size(); i++){
            resultValue=resultValue+wIn.get(i).vOut;
        }
        resultValue=resultValue+bias;
    }
    public void sigmoidFunction(int a){
        resultValue=1/(1+Math.exp(0-(a*resultValue)));
    }
    public void hardLimit(){
        if(resultValue>0){
            resultValue=1;
        }
        else if(resultValue<=0){
            resultValue=0;
        }
    }
    
    public void calError(){
        double tempError=0;
        for(int i=0; i<wOut.size(); i++){
            tempError=tempError+wOut.get(i).vWeight*((OutputNode)wOut.get(i).nNext).error;
        }
        error=resultValue*(1-resultValue)*tempError;
    }
    
    public void calError2(){
        double tempError=0;
        for(int i=0; i<wOut.size(); i++){
            tempError=tempError+wOut.get(i).vWeight*((OutputNode)wOut.get(i).nNext).error;
        }
        error=resultValue*(1-resultValue)*tempError;
    }
}
