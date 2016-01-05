/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Image.processor;

import Preprocessing.ImgResizer;
import Preprocessing.LoadImage;
import Preprocessing.NormPrinter;
import Preprocessing.Normalization;
import Preprocessing.ProjectionHistogram;
import com.sun.xml.internal.ws.util.StringUtils;
import image.processor.DetectorV2;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nnsc.BackPropagation;
import nnsc.NeuralNetwork;

/**
 *
 * @author Yorozuya Samurai
 */
public class Driver {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        for (int x = 1; x < 12; x++) {
            
        try {
            PrintWriter writer = null;
            //deteksi plat nomor
            DetectorV2 d = new DetectorV2();
            String url = "D:\\Users\\Yorozuya\\Documents\\Matkul smt 7\\Soft Computing\\Tubes\\plat_test\\plat_"+x;
            String urlPH = url + "\\PH";
            File dir = new File(urlPH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            d.detect(url, "jpg");
            File filePH = new File(dir.getAbsolutePath() + "\\NN Normalized Input.data");
            writer = new PrintWriter(filePH, "UTF-8");
            List<BufferedImage> detecteds = d.output;
            //preprocess -> dataset
            for (BufferedImage img : detecteds) {
                ImgResizer ir = new ImgResizer(img);
                ir.resizeImage(16);
                ProjectionHistogram proj = new ProjectionHistogram(ir.getImg());
                NormPrinter printer = new NormPrinter();
                Normalization norm = new Normalization(16, proj.getPH());
                norm.normArrayV2(0, 16, 0.1, 0.9);
                printer.setpH(norm.getArrNorm());
                norm = new Normalization(16, proj.getPV());
                norm.normArrayV2(0, 16, 0.1, 0.9);
                printer.setpV(norm.getArrNorm());
                printer.printNorm(writer);
            }
            writer.close();
            File fileRes = new File(url + "\\hasil_klasifikasi\\hasil_klasifikasi.txt");
            if(!fileRes.exists()) fileRes.getParentFile().mkdirs();
            writer = new PrintWriter(fileRes, "UTF-8");
            //klasifikasi
            writer.println("=============hasil klasifikasi===============");
            NeuralNetwork nTest = new NeuralNetwork();
            nTest = nTest.loadModel("Neural Model2.data");
            nTest.loadDataTestFromFile(urlPH + "\\NN Normalized Input.data");
            BackPropagation bPTest = new BackPropagation(nTest);
            System.out.print("result : ");
            bPTest.testModel();
            System.out.println("");
            for (String i : bPTest.getNodesRslt()) {
                System.out.println(i + " ");
                writer.println(i + " ");
            }
            System.out.println();
            writer.println("result : ");
            for (String i : bPTest.getResults()) {
                System.out.print(i + " ");
                writer.print(i + " ");
            }
            System.out.println();
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }
    }

}
