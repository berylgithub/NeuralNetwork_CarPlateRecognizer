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
public class InputNode extends Node implements Serializable{
    double inputValue;
    ArrayList<Weight> weightOut=new ArrayList<>();
    
    public InputNode() {

    }
    

    public InputNode(double inputValue) {
        this.inputValue = inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public double getInputValue() {
        return inputValue;
    }
    
    
}
