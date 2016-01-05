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
public class InputLayer implements Serializable{
    ArrayList<InputNode> arrInNode=new ArrayList();

    public InputLayer() {
    }

    public InputLayer(int n) {
        for(int i=0;i<n;i++){
            InputNode iNode=new InputNode();
            arrInNode.add(iNode);
        }
    }
    
    
}
