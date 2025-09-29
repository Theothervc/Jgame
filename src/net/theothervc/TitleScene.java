package net.theothervc;

import net.theothervc.jgame.game.Scene;
import net.theothervc.jgame.components.ParagraphComponent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TitleScene implements Scene, KeyListener {



    public ParagraphComponent text = new ParagraphComponent(0,0,500,300,"Lorem Ipsum\nDolor Sit\nAmet",new Font("Z003",Font.PLAIN,28));

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g,Dimension screenSize) {
        text.setAppearance(true);
        text.justification_Horizontal = ParagraphComponent.JUSTIFICATION_CENTER;
        text.justification_Vertical = ParagraphComponent.JUSTIFICATION_CENTER;
        text.setAppearance(Color.green);
        text.draw(g, screenSize);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        text.setWidth(text.getWidth() + 50);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
