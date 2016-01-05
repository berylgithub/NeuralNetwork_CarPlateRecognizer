/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nnsc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Yorozuya
 */
public class BackPropagation {

    NeuralNetwork nN;
    double lr, sumError, sumErrorVal, MSE, MSEval;
    int epoch;
    List<String> nodeVals = new ArrayList<String>();
    ArrayList<Double> arrMSE = new ArrayList();
    ArrayList<Double> arrMSEVal = new ArrayList<>();
    ArrayList<NeuralNetwork> arrNNWeightContainer = new ArrayList<>();

    public BackPropagation() {
        lr = 0;
        sumError = 0;
        sumErrorVal = 0;
        MSE = 0;
        epoch = 0;
        MSEval = 0;
    }

    public BackPropagation(NeuralNetwork nN) {
        this.nN = nN;
    }

    public BackPropagation(NeuralNetwork nN, double lr, double sumError, double MSE, int epoch, double MSEval, double sumErrorVal) {
        this.nN = nN;
        this.lr = lr;
        this.sumError = sumError;
        this.MSE = MSE;
        this.epoch = epoch;
        this.MSEval = MSEval;
        this.sumErrorVal = sumErrorVal;
    }

    public void setnN(NeuralNetwork nN) {
        this.nN = nN;
    }

    public void setLr(double lr) {
        this.lr = lr;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    public void calForward() {
//        System.out.println("WeightInPrev");
        for (int i = 0; i < nN.arrWeight.size(); i++) {
            if (nN.arrWeight.get(i).nPrev.getClass().equals(InputNode.class)) {
                nN.arrWeight.get(i).setVIn();
                nN.arrWeight.get(i).calVOut();
//                System.out.println(nN.arrWeight.get(i).vWeight+" ");
            }
        }
//        System.out.println("SumHid");
        for (int i = 0; i < nN.hidLayer.arrHidNode.size(); i++) {
            nN.hidLayer.arrHidNode.get(i).sumFunction();
            nN.hidLayer.arrHidNode.get(i).sigmoidFunction(1);
//            nN.hidLayer.arrHidNode.get(i).hardLimit();
//            System.out.print(nN.hidLayer.arrHidNode.get(i).resultValue+" ");
        }
//        System.out.println("WeightHidPrev");
//        System.out.println("");
        for (int i = 0; i < nN.arrWeight.size(); i++) {
            if (nN.arrWeight.get(i).nPrev.getClass().equals(HiddenNode.class)) {
                nN.arrWeight.get(i).setVIn();
                nN.arrWeight.get(i).calVOut();
//                System.out.println(nN.arrWeight.get(i).vWeight+" ");
            }
        }
//        System.out.println("SumOut");
        for (int i = 0; i < nN.outLayer.arrOutNode.size(); i++) {
            nN.outLayer.arrOutNode.get(i).sumFunction();
            nN.outLayer.arrOutNode.get(i).sigmoidFunction(1);
//            nN.outLayer.arrOutNode.get(i).hardLimit();
//            System.out.print(nN.outLayer.arrOutNode.get(i).resultValue+" ");
        }
//        System.out.println("");
    }

    public void calMSE(int n) {
        MSE = sumError / n;
        arrMSE.add(MSE);
    }

    public void calMSEVal(int n) {
        MSEval = sumErrorVal / n;
        arrMSEVal.add(MSEval);

    }

    public void calBack() {
        double tempRes = 0;
        for (int i = 0; i < nN.outLayer.arrOutNode.size(); i++) {
            nN.outLayer.arrOutNode.get(i).calError();
        }
        for (int i = 0; i < nN.arrWeight.size(); i++) {
            if (nN.arrWeight.get(i).nPrev.getClass().equals(HiddenNode.class)) {
                tempRes = nN.arrWeight.get(i).vWeight + (lr * nN.arrWeight.get(i).vInput * ((OutputNode) nN.arrWeight.get(i).nNext).error);
                nN.arrWeight.get(i).vWeight = tempRes;
            }
        }
        for (int i = 0; i < nN.hidLayer.arrHidNode.size(); i++) {
            nN.hidLayer.arrHidNode.get(i).calError();
        }
        for (int i = 0; i < nN.arrWeight.size(); i++) {
            if (nN.arrWeight.get(i).nPrev.getClass().equals(InputNode.class)) {
                tempRes = nN.arrWeight.get(i).vWeight + lr * nN.arrWeight.get(i).vInput * ((HiddenNode) nN.arrWeight.get(i).nNext).error;
                nN.arrWeight.get(i).vWeight = tempRes;
            }
        }
    }

    public void trainModel() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        //fungsi load dataset ke node
        PrintWriter writer = new PrintWriter("Hasil_training.txt");
//        writer.println("Target||ResultValue||Error");
        for (int i = 0; i < epoch; i++) {
            writer.println("Training di Epoch : " + i);
            for (int j = 0; j < nN.arrData.size(); j++) {
                nN.setDatasetToNode(j);
                calForward();
                calBack();
                for (int k = 0; k < nN.outLayer.arrOutNode.size(); k++) {
                    sumError = sumError + Math.pow(nN.outLayer.arrOutNode.get(k).target - nN.outLayer.arrOutNode.get(k).resultValue, 2);
//                    System.out.print(nN.outLayer.arrOutNode.get(k).target+"||"+nN.outLayer.arrOutNode.get(k).resultValue+"||"+(nN.outLayer.arrOutNode.get(k).target-nN.outLayer.arrOutNode.get(k).resultValue)+"\t");
//                    writer.print(nN.outLayer.arrOutNode.get(k).target+"||"+nN.outLayer.arrOutNode.get(k).resultValue+"||"+(nN.outLayer.arrOutNode.get(k).target-nN.outLayer.arrOutNode.get(k).resultValue)+"\t");
                }
//                System.out.println("");
//                writer.println("");
            }
            calMSE(nN.arrData.size());
            writer.println("MSE Training : " + MSE);
            sumError = 0;
        }
    }

    public void trainAndValidateModel() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        //fungsi load dataset ke node
        PrintWriter writer = new PrintWriter("Hasil_training.txt");
        writer.println("Target||ResultValue||Error");
        for (int i = 0; i < epoch; i++) {
            writer.println("Training di Epoch : " + i);
            for (int j = 0; j < nN.arrData.size(); j++) {
                nN.setDatasetToNode(j);
                calForward();
                calBack();
                for (int k = 0; k < nN.outLayer.arrOutNode.size(); k++) {
                    sumError = sumError + Math.pow(nN.outLayer.arrOutNode.get(k).target - nN.outLayer.arrOutNode.get(k).resultValue, 2);
                    writer.print(nN.outLayer.arrOutNode.get(k).target + "||" + nN.outLayer.arrOutNode.get(k).resultValue + "||" + (nN.outLayer.arrOutNode.get(k).target - nN.outLayer.arrOutNode.get(k).resultValue) + "\t");
                }
            }

            //V A L I D A S I
            for (int j = 0; j < nN.arrDataValidate.size(); j++) {
                //fungsi load dataset validasi ke node
                nN.setDataValidatetoNode(j);
                calForward();
                for (int l = 0; l < nN.outLayer.arrOutNode.size(); l++) {
                    nN.outLayer.arrOutNode.get(l).calError();
                }
                for (int k = 0; k < nN.outLayer.arrOutNode.size(); k++) {
                    sumErrorVal = sumErrorVal + Math.pow(nN.outLayer.arrOutNode.get(k).target - nN.outLayer.arrOutNode.get(k).resultValue, 2);
                    writer.print(nN.outLayer.arrOutNode.get(k).target + "||" + nN.outLayer.arrOutNode.get(k).resultValue + "||" + (nN.outLayer.arrOutNode.get(k).target - nN.outLayer.arrOutNode.get(k).resultValue) + "\t");
                }
            }
            //V A L I D A S I

            calMSE(nN.arrData.size());
            sumError = 0;

            calMSEVal(nN.arrDataValidate.size());
            sumErrorVal = 0;

            //save weight conf setiap epoch
            arrNNWeightContainer.add(nN);
        }
    }

    public int searchBestMSE() {
        ArrayList<Double> arrTempRes = new ArrayList<>();
        double tempRes = 0, tempMax = 0;
        int maxIndex = 0;
        for (int i = 0; i < arrMSE.size(); i++) {
            tempRes = (arrMSE.get(i) + arrMSEVal.get(i)) / 2;
            arrTempRes.add(tempRes);
        }
        tempMax = Collections.max(arrTempRes);
        for (int i = 0; i < arrMSE.size(); i++) {
            if (arrTempRes.get(i) == tempMax) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public NeuralNetwork loadBestMSENN(int maxIndex) {
        return this.arrNNWeightContainer.get(maxIndex);
    }

    public void testModel() {
        for (int h = 0; h < nN.arrDataTest.size(); h++) {
            nN.setDataTestoNode(h);
            calForward();
            ArrayList<Double> arrTempRes = new ArrayList<>();
            for (int i = 0; i < nN.outLayer.arrOutNode.size(); i++) {
                arrTempRes.add(nN.outLayer.arrOutNode.get(i).resultValue);
            }
            for (int i = 0; i < nN.outLayer.arrOutNode.size(); i++) {
                if (Collections.max(arrTempRes) == nN.outLayer.arrOutNode.get(i).resultValue) {
                    if (i < 10) {
                        nN.arrDataTest.get(h).result = "" + i;
                    } else if (i == 10) {
                        nN.arrDataTest.get(h).result = "A";
                    } else if (i == 11) {
                        nN.arrDataTest.get(h).result = "B";
                    } else if (i == 12) {
                        nN.arrDataTest.get(h).result = "C";
                    } else if (i == 13) {
                        nN.arrDataTest.get(h).result = "D";
                    } else if (i == 14) {
                        nN.arrDataTest.get(h).result = "E";
                    } else if (i == 15) {
                        nN.arrDataTest.get(h).result = "F";
                    } else if (i == 16) {
                        nN.arrDataTest.get(h).result = "G";
                    } else if (i == 17) {
                        nN.arrDataTest.get(h).result = "H";
                    } else if (i == 18) {
                        nN.arrDataTest.get(h).result = "I";
                    } else if (i == 19) {
                        nN.arrDataTest.get(h).result = "J";
                    } else if (i == 20) {
                        nN.arrDataTest.get(h).result = "K";
                    } else if (i == 21) {
                        nN.arrDataTest.get(h).result = "L";
                    } else if (i == 22) {
                        nN.arrDataTest.get(h).result = "M";
                    } else if (i == 23) {
                        nN.arrDataTest.get(h).result = "N";
                    } else if (i == 24) {
                        nN.arrDataTest.get(h).result = "O";
                    } else if (i == 25) {
                        nN.arrDataTest.get(h).result = "P";
                    } else if (i == 26) {
                        nN.arrDataTest.get(h).result = "Q";
                    } else if (i == 27) {
                        nN.arrDataTest.get(h).result = "R";
                    } else if (i == 28) {
                        nN.arrDataTest.get(h).result = "S";
                    } else if (i == 29) {
                        nN.arrDataTest.get(h).result = "T";
                    } else if (i == 30) {
                        nN.arrDataTest.get(h).result = "U";
                    } else if (i == 31) {
                        nN.arrDataTest.get(h).result = "V";
                    } else if (i == 32) {
                        nN.arrDataTest.get(h).result = "W";
                    } else if (i == 33) {
                        nN.arrDataTest.get(h).result = "X";
                    } else if (i == 34) {
                        nN.arrDataTest.get(h).result = "Y";
                    } else if (i == 35) {
                        nN.arrDataTest.get(h).result = "Z";
                    }
                }
            }
            String val = "";
            for (int i = 0; i < nN.outLayer.arrOutNode.size(); i++) {
                val = val + "" + nN.outLayer.arrOutNode.get(i).resultValue + "||" + i + " ";
            }
            nodeVals.add(val + " || " +nN.arrDataTest.get(h).result);
        }

    }

    public List<String> getResults() {
        List<String> res = new ArrayList<String>();
        for (Datatest data : nN.arrDataTest) {
            res.add(data.result);
        }
        return res;
    }

    public List<String> getNodesRslt() {
        return nodeVals;
    }
}
