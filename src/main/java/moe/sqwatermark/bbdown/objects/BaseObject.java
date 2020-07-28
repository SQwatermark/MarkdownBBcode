package moe.sqwatermark.bbdown.objects;

import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class BaseObject extends ArrayList<Character> {

    public BaseObject(char c) {
        this.add(c);
    }

    public boolean finished = false;

    public abstract String generateBBCode();

    //TODO
    public abstract Pattern getPattern();

    public void setFinished() {
        this.finished = true;
    }

    public String getMarkdownString() {
        StringBuilder s = new StringBuilder();
        for (Character character : this) {
            s.append(character);
        }
        return s.toString();
    }

}