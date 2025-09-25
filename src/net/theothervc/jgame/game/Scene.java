package net.theothervc.jgame.game;


import java.awt.*;


public interface Scene {


    boolean paused = false;

    void pause();

    void resume();

    void update();


    void draw(Graphics g,Dimension screenSize);

}
