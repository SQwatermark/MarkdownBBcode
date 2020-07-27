package moe.sqwatermark.bbdown.objects;

import java.util.regex.Pattern;

public class ObjectBold extends BaseObject {

    public ObjectBold(char c) {
        super(c);
    }

    @Override
    public String generateBBCode() {
        String s = getMarkdownString();
        s = s.substring(2, s.length()-2);
        s = new ObjectText(s.toCharArray()).generateBBCode();
        return "[b]" + s + "[/b]";
    }

    @Override
    public Pattern getPattern() {
        return Pattern.compile("(^\\*\\*).*(\\*\\*$)");
    }

}
