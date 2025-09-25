package net.theothervc.jgame.util;

import java.awt.*;

public class AspectRatio {

    public int width;
    public int height;

    public  int getWidth() {return width;}

    public  int getHeight() {return height;}


    public AspectRatio(int w, int h) {
        width = w;
        height = h;
    }

    public Dimension fitToDimension(Dimension d) {
        Dimension size;
        if( width*d.height > height*d.width ){
            size = new Dimension(d.width, height*d.width/width);
        } else{
            size = new Dimension(width*d.height/height, d.height);
        }
        return size;
    }

    public Dimension cropToDimension(Dimension d) {
        Dimension size;
        if( width*d.height < height*d.width ){
            size = new Dimension(d.width, height*d.width/width);
        } else{
            size = new Dimension(width*d.height/height, d.height);
        }
        return size;
    }

    public static AspectRatio fromDimension(Dimension size) {
        int gcf = Algorithms.euclid(size.width,size.height);
        return new AspectRatio(size.width/gcf, size.height/gcf);
    }

}
