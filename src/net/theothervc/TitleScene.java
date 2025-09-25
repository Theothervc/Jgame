package net.theothervc;

import net.theothervc.jgame.game.Scene;
import net.theothervc.jgame.components.ParagraphComponent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TitleScene implements Scene, KeyListener {



    public ParagraphComponent text = new ParagraphComponent(0,0,500,300,"$WIf You Leave Now, $RASGORE$W Will Take Your $YSoul",new Font("Z003",Font.PLAIN,28));

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
        text.setAppearance(Color.green);
        text.draw(g, screenSize);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
