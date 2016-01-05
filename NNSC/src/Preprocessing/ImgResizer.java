/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.awt.image.BufferedImage;
import org.imgscalr.*;
/**
 *
 * @author Yorozuya
 */
public class ImgResizer {
    BufferedImage img;

    public ImgResizer(BufferedImage img) {
        this.img = img;
    }
    public void resizeImage(int sizeSquare){
        img=Scalr.resize(img, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT,sizeSquare, sizeSquare);
    }

    public BufferedImage getImg() {
        return img;
    }
    
}
