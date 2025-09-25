package net.theothervc.jgame.components;

import java.awt.*;

public class AnchorPoint {

    public int x;
    public int y;

    public AnchorPoint(Dimension point) {
        x = point.width;
        y = point.height;
    }
}
