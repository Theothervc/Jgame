package net.theothervc.jgame.localization;

public abstract class Text {

    //TODO: Localization

   public String key;

    public Text(String text) {
        key = text;
    }

    public String toString() {
    return key;
    }
}
