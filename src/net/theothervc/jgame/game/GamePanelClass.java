package net.theothervc.jgame.game;

import net.theothervc.jgame.util.Algorithms;
import net.theothervc.jgame.util.AspectRatio;
import net.theothervc.jgame.util.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class GamePanelClass {

    public JPanel frame;
    public JPanel canvas;
    public BufferedImage currentBackground = null;
    public boolean cropped = false;
    public Game parent = null;

    public JPanel getFrame() {
        return frame;
    }

    public JPanel getCanvas() {
        return canvas;
    }

    public void setBackgroundStyle(Color color) {
        canvas.setBackground(color);
        currentBackground = null;
    }

    public void setBackgroundStyle(String imagepath,boolean crop) {
     currentBackground = new Images().getImageFromPath(imagepath);
        cropped = crop;
    }


    protected void calculateBackground(Graphics g) {
        if (currentBackground != null) {
            if (cropped) {
                int w = 0;
                int h = 0;
                int x = 0;
                int y = 0;
                Dimension dim = AspectRatio.fromDimension(new Dimension(currentBackground.getWidth(),currentBackground.getHeight())).cropToDimension(new Dimension(canvas.getWidth(),canvas.getHeight()));
                w = dim.width;
                h = dim.height;
                x = -(w/2)+canvas.getWidth()/2;
                y = -(h/2)+canvas.getHeight()/2;
                g.drawImage(currentBackground,x,y,w,h,null);
            } else {
                g.drawImage(currentBackground,0,0,canvas.getWidth(),canvas.getHeight(),null);
            }
        }
    }

}
