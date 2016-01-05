/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nnsc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Yorozuya
 */
public class Weight implements Serializable{
    double vInput, vWeight, vOut;
    Node nPrev, nNext;

    public Weight() {
        vInput=0;
        vWeight=0;
        vOut=0;
        setVIn();
        calVOut();
    }
    public Weight(Node nPrev, Node nNext){
        setWeightRandom();
        this.nPrev=nPrev;
        this.nNext=nNext;
        setVIn();
        calVOut();
    }

    public void setvInput(double vInput) {
        this.vInput = vInput;
    }

    public void setnPrev(Node nPrev) {
        this.nPrev = nPrev;
    }

    public void setnNext(Node nNext) {
        this.nNext = nNext;
    }

    public double getvWeight() {
        return vWeight;
    }

    public double getvInput() {
        return vInput;
    }
    public void setWeight(){
        vWeight=1;
    }
    public void setWeightRandom(){
        Random r = new Random();
        double randomValue = -0.5 + (0.5 - -0.5) * r.nextDouble();
        vWeight=randomValue;
    }
    public void updateWeight(double vWeight){
        this.vWeight=vWeight;
    }
    
    //fungsi wajib
    public void setVIn(){
        if(nPrev.getClass().equals(InputNode.class)){
            vInput=((InputNode)nPrev).inputValue;
        }
        else if(nPrev.getClass().equals(HiddenNode.class)){
            vInput=((HiddenNode)nPrev).resultValue;
        }
        
    }
    public void calVOut(){
        this.vOut=vInput*vWeight;
    }
    
    
}
