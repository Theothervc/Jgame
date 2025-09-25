package net.theothervc.jgame.game;

import net.theothervc.jgame.util.AspectRatio;
import org.cef.OS;

import javax.swing.*;
import java.awt.*;

public class GamePanelAspectLocked extends GamePanelClass {

    public AspectRatio aspectRatio;

    public GamePanelAspectLocked(Dimension lockedAspectRatio) {
        canvas = new JPanel() {
            @Override
            public Dimension getPreferredSize() {return aspectRatio.fitToDimension(frame.getSize());}

            @Override
            protected void paintComponent(Graphics g) {
                if (OS.isLinux()) Toolkit.getDefaultToolkit().sync();
                super.paintComponent(g);
                calculateBackground(g);
                parent.loop.currentScene.draw(g,canvas.getSize());

            }
        };
        canvas.setBackground(Color.WHITE);
        canvas.setFocusable(true);
        canvas.setDoubleBuffered(true);
        aspectRatio = new AspectRatio(lockedAspectRatio.width,lockedAspectRatio.height);
        frame = new JPanel(new GridBagLayout());
        frame.setBackground(Color.BLACK);
        frame.add(canvas);
    }




}
