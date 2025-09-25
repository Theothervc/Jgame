package net.theothervc.jgame.util;

import java.util.ArrayList;

public abstract class CharOps {

    public static String toStringArray(ArrayList<Character> chars) {
        StringBuilder sb = new StringBuilder(chars.size());

        for (char letter : chars) {
            sb.append(letter);
        }
        return sb.toString();
    }
}
