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
public class HiddenLayer implements Serializable{
    ArrayList<HiddenNode> arrHidNode=new ArrayList();
    
    public HiddenLayer() {
    }

    public HiddenLayer(int n) {
        for(int i=0; i<n; i++){
            HiddenNode hNode=new HiddenNode();
            arrHidNode.add(hNode);
        }
    }
    
}
