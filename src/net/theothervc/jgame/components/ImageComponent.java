package net.theothervc.jgame.components;

import java.awt.*;

public class ImageComponent extends Component {
    public ImageComponent(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    public ImageComponent(float x, float y, float w, float h, Color color) {
        super(x,y,w,h,color);
    }
    
    public ImageComponent(float x, float y, float w, float h, String path) {
        super(x,y,w,h,path);
    }
}
