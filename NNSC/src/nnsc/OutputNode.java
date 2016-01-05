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
public class OutputNode extends Node implements Serializable{
    double resultValue, target, error, bias;
    ArrayList<Weight> weightIn=new ArrayList<>();

    public OutputNode() {
        error=0;
        resultValue=0;
        target=0;
        bias=0;
    }

    public OutputNode(double target, ArrayList<Weight> weightIn, double error, double bias) {
        resultValue=0;
        this.target=0;
        this.target = target;
        this.weightIn = weightIn;
        this.error=error;
        this.bias=bias;
        
    }

    public void setTarget(double target) {
        this.target = target;
    }
    
    public void sumFunction(){
        for(int i=0; i<weightIn.size(); i++){
            resultValue=resultValue+weightIn.get(i).vOut;
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
    public void calError2(){
        this.error=target-resultValue;
    }
    public void calError(){
        this.error=resultValue*(1-resultValue)*(target-resultValue);
    }
    
}
