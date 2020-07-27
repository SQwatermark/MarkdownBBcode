package moe.sqwatermark.bbdown.objects;

import java.util.regex.Pattern;

public class ObjectLittleBlock extends BaseObject {

    public ObjectLittleBlock(char c) {
        super(c);
    }

    @Override
    public String generateBBCode() {
        String s = this.getMarkdownString();
        s = s.substring(1, s.length()-1);
        return s;
    }

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^`.*`$");
    }

}