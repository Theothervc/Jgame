package net.theothervc.jgame.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Images {

    public BufferedImage getImageFromPath(String path) {
        BufferedImage result = null;
        try {
            result = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
