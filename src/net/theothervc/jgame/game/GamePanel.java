package net.theothervc.jgame.game;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class GamePanel extends GamePanelClass {

    public GamePanel() {
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (System.getProperty("os.name").toLowerCase().contains("linux")) Toolkit.getDefaultToolkit().sync();
                super.paintComponent(g);
                calculateBackground(g);
                parent.loop.currentScene.draw(g,canvas.getSize());
            }
        };
        canvas.setBackground(Color.WHITE);
        canvas.setFocusable(true);
        canvas.setDoubleBuffered(true);
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        frame = new JPanel(new BorderLayout());
        frame.setBackground(Color.BLACK);
        frame.add(canvas);
    }


}
