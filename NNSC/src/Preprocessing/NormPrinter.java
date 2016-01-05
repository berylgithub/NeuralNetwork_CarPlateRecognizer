/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.io.PrintWriter;

/**
 *
 * @author Yorozuya
 */
public class NormPrinter {
    double[] pH, pV;

    public NormPrinter(double[] pH, double[] pV) {
        this.pH = pH;
        this.pV = pV;
    }

    public NormPrinter() {
    }

    public void setpH(double[] pH) {
        this.pH = pH;
    }

    public void setpV(double[] pV) {
        this.pV = pV;
    }
    
    public void printNorm(PrintWriter writer){
        for(int i=0; i<pH.length; i++){
            writer.print(pH[i]+"\t");
        }
        for(int i=0; i<pV.length; i++){
            writer.print(pV[i]+"\t");
        }
        writer.println();
    }
}
