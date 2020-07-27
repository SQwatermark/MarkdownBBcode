package moe.sqwatermark.bbdown.objects;

import java.util.regex.Pattern;

public class ObjectSingleChar extends BaseObject {
    public ObjectSingleChar(char c) {
        super(c);
        setFinished();
    }

    @Override
    public String generateBBCode() {
        return getMarkdownString();
    }

    @Override
    public Pattern getPattern() {
        return null;
    }

}
