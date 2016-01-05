/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nnsc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Yorozuya
 */
public class NeuralNetwork implements Serializable{
    private static final long serialVersionUID = 1L;
    InputLayer inLayer;
    OutputLayer outLayer;
    HiddenLayer hidLayer;
    ArrayList<Weight> arrWeight=new ArrayList<>();
    ArrayList<Dataset> arrData=new ArrayList<>();
    ArrayList<Datatest> arrDataTest=new ArrayList<>();
    ArrayList<Dataset> arrDataValidate=new ArrayList<>();
    
    public NeuralNetwork() {
        
    }

    public void createInputLayer(int n){
        inLayer=new InputLayer(n);
        
    }
    
    public void createOutLayer(int n){
        outLayer=new OutputLayer(n);
    }
    
    public void createHiddenLayer(int n){
        hidLayer=new HiddenLayer(n);
    }
    
    public void generateWeightInputHidden(){
        for(int i=0; i<inLayer.arrInNode.size(); i++){
            for(int j=0; j<hidLayer.arrHidNode.size(); j++){
                Weight weight=new Weight(inLayer.arrInNode.get(i), hidLayer.arrHidNode.get(j));
                arrWeight.add(weight);
            }
        }
    }
    public void generateWeightHiddenOutput(){
        for(int i=0; i<hidLayer.arrHidNode.size(); i++){
            for(int j=0; j<outLayer.arrOutNode.size(); j++){
                Weight weight=new Weight(hidLayer.arrHidNode.get(i), outLayer.arrOutNode.get(j));
                arrWeight.add(weight);
            }
        }
    }
    
    //fungsi wajib urutan
    public void generateWeight(){
        generateWeightInputHidden();
        generateWeightHiddenOutput();
    }
    public void setWeightInputLayer(){
        for(int i=0; i<inLayer.arrInNode.size(); i++){
            for(int j=0;j<arrWeight.size(); j++){
                if(inLayer.arrInNode.get(i).equals(arrWeight.get(j).nPrev)){
                    inLayer.arrInNode.get(i).weightOut.add(arrWeight.get(j));
                }
            }
        }
    }
    public void setWeightHiddenLayer(){
        for(int i=0; i<hidLayer.arrHidNode.size(); i++){
            for(int j=0; j<arrWeight.size(); j++){
                if(hidLayer.arrHidNode.get(i).equals(arrWeight.get(j).nNext)){
                    hidLayer.arrHidNode.get(i).wIn.add(arrWeight.get(j));
                }
                else if(hidLayer.arrHidNode.get(i).equals(arrWeight.get(j).nPrev)){
                    hidLayer.arrHidNode.get(i).wOut.add(arrWeight.get(j));
                }
            }
        }
    }
    public void setWeightOutputLayer(){
        for(int i=0; i<outLayer.arrOutNode.size(); i++){
            for(int j=0; j<arrWeight.size(); j++){
                if(outLayer.arrOutNode.get(i).equals(arrWeight.get(j).nNext)){
                    outLayer.arrOutNode.get(i).weightIn.add(arrWeight.get(j));
                }
            }
        }
    }
    
    //fungsi isinya wajib urutan juga
    public void setWeight(){
        setWeightInputLayer();
        setWeightHiddenLayer();
        setWeightOutputLayer();
    }
    
    public void setDatasetToNode(int i){
        for(int j=0; j<inLayer.arrInNode.size(); j++){
            inLayer.arrInNode.get(j).setInputValue(arrData.get(i).pattern.get(j));
        }
        for(int j=0; j<outLayer.arrOutNode.size(); j++){
            outLayer.arrOutNode.get(j).setTarget(arrData.get(i).target.get(j));
        }        
    }
    
    public void setDataTestoNode(int i){
        for(int j=0; j<inLayer.arrInNode.size(); j++){
            inLayer.arrInNode.get(j).setInputValue(arrDataTest.get(i).pattern.get(j));
        }
    }
    
    public void setDataValidatetoNode(int i){
        for(int j=0; j<inLayer.arrInNode.size(); j++){
            inLayer.arrInNode.get(j).setInputValue(arrDataValidate.get(i).pattern.get(j));
        }
        for(int j=0; j<outLayer.arrOutNode.size(); j++){
            outLayer.arrOutNode.get(j).setTarget(arrDataValidate.get(i).target.get(j));
        }
    }
    
    public void loadDataSetTrainingFromFile(String url) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new java.io.FileReader(url));
        String line;
        String[] splitLine;
        Dataset tempData;
        ArrayList<Dataset> arrTempData=new ArrayList();
        while ((line = br.readLine()) != null) {
            splitLine=line.split("\t");
            tempData = new Dataset();
            //tempData.setDatasetFromDouble(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]), Double.parseDouble(splitLine[7]), Double.parseDouble(splitLine[8]), Double.parseDouble(splitLine[9]));
            for(int i=0; i<32; i++){
                tempData.pattern.add(Double.parseDouble(splitLine[i]));
                //System.out.print(splitLine[i]+" ");
            }
            for(int i=32; i<68; i++){
                tempData.target.add(Double.parseDouble(splitLine[i]));
                //System.out.print(splitLine[i]+" ");
            }
            //System.out.println("");
            arrTempData.add(tempData);
         }
        arrData=arrTempData;
    }
    
    public void loadDataTestFromFile(String url) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new java.io.FileReader(url));
        String line;
        String[] splitLine;
        Datatest tempData;
        ArrayList<Datatest> arrTempData=new ArrayList();
        while ((line = br.readLine()) != null) {
            splitLine=line.split("\t");
            tempData = new Datatest();
            //tempData.setDatasetFromDouble(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]), Double.parseDouble(splitLine[7]), Double.parseDouble(splitLine[8]), Double.parseDouble(splitLine[9]));
            for(int i=0; i<32; i++){
                tempData.pattern.add(Double.parseDouble(splitLine[i]));
                //System.out.print(splitLine[i]+" ");
            }
            //System.out.println("");
            arrTempData.add(tempData);
         }
        arrDataTest=arrTempData;
    }
    
    public void loadDataValidateFromFile(String url) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new java.io.FileReader(url));
        String line;
        String[] splitLine;
        Dataset tempData;
        ArrayList<Dataset> arrTempData=new ArrayList();
        while ((line = br.readLine()) != null) {
            splitLine=line.split("\t");
            tempData = new Dataset();
            //tempData.setDatasetFromDouble(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]), Double.parseDouble(splitLine[7]), Double.parseDouble(splitLine[8]), Double.parseDouble(splitLine[9]));
            for(int i=0; i<32; i++){
                tempData.pattern.add(Double.parseDouble(splitLine[i]));
                //System.out.print(splitLine[i]+" ");
            }
            for(int i=32; i<68; i++){
                tempData.target.add(Double.parseDouble(splitLine[i]));
                //System.out.print(splitLine[i]+" ");
            }
            //System.out.println("");
            arrTempData.add(tempData);
         }
        arrDataValidate=arrTempData;
    }
    
    public void saveModel(NeuralNetwork nN, String fileName) throws FileNotFoundException, IOException{
        FileOutputStream f_out = new FileOutputStream(fileName);
        ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
        obj_out.writeObject(nN);
    }
    
    public NeuralNetwork loadModel(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
        NeuralNetwork nN=null;
        FileInputStream f_in = new FileInputStream(fileName);
        ObjectInputStream obj_in = new ObjectInputStream (f_in);
        Object obj = obj_in.readObject();
        if(obj instanceof NeuralNetwork){
            nN=(NeuralNetwork)obj;
        }
        return nN;
    }
    
    public ArrayList<Weight> getArrweight(){
        return arrWeight;
    }
    
}
