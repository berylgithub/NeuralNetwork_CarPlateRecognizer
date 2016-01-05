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
public class OutputLayer implements Serializable{
    ArrayList<OutputNode> arrOutNode=new ArrayList<>();

    public OutputLayer() {
        
    }

    public OutputLayer(int n) {
        for(int i=0;i<n;i++){
            OutputNode oNode=new OutputNode();
            arrOutNode.add(oNode);
        }
    }
    
    
    
}
