package net.theothervc.jgame.game;

import java.awt.event.KeyListener;


public class GameLoop implements Runnable {

    //Variable Tower
    private Thread gt;
    public int fps = 0;
    private double fpsTimer = 0;
    private int counter;
    private final double pt;
    public Scene currentScene;
    public Game parent;
    public GamePanelClass gamePanel;
    public KeyListener keyListener = null;

    public GameLoop(int framesPerSecond, Scene defaultScene) {
        pt = 1000000000 / (double) framesPerSecond;
        gt = new Thread(this);
        currentScene = defaultScene;

    }

    //Function Worm Valley
    public void start() {
        gt.start();
    }

    public void exit() {
        gt = null;
    }

    public void changeScene(Scene newScene) {currentScene = newScene;}

    public void changeSceneKeyListener(KeyListener newListener) {
        if (keyListener != null) gamePanel.canvas.removeKeyListener(keyListener);
        gamePanel.canvas.addKeyListener(newListener);
        keyListener = newListener;
    }

    @Override
    public void run() {
      double lt = System.nanoTime();
      double dt = 0;
        while (gt != null) {
            double ct = System.nanoTime();
            dt += (ct - lt)/pt;
            fpsTimer += ct-lt;
            lt = ct;
            if (dt >= 1) {
                update();
                draw();
                counter++;
                dt--;
            }
            if (fpsTimer >= 1000000000) {
                fps = counter;
                fpsTimer = 0;
                counter = 0;
                System.out.println(fps);
            }

        }
    }

   private void update() {
    currentScene.update();
   }

   private void draw() {
    gamePanel.canvas.repaint();
   }
}
