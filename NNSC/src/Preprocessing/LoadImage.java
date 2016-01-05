/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Yorozuya
 */
public class LoadImage {
    BufferedImage img;

    public LoadImage(BufferedImage img) {
        this.img = img;
    }

    public LoadImage(String dir) throws IOException {
        img = ImageIO.read(new File(dir));
    }
    
    public void setImg(BufferedImage img){
        this.img=img;
    }
    public BufferedImage getImg(){
        return img;
    }
    public void saveImage(BufferedImage img, String name) throws FileNotFoundException, IOException
    {
        File outputfile = new File(name);
        ImageIO.write(img, "png", outputfile);
    }
    
}
