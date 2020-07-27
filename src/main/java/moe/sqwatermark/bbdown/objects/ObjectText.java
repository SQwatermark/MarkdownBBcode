package moe.sqwatermark.bbdown.objects;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ObjectText extends BaseObject {

    private ObjectText(char c) {
        super(c);
    }

    public ObjectText(char[] c) {
        super(' ');
        this.clear();
        for (char value : c) {
            this.add(value);
        }
        setFinished();
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        String line = this.getMarkdownString();
        ArrayList<BaseObject> objects = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            BaseObject lastObject = objects.isEmpty() ? null : objects.get(objects.size()-1);
            if (lastObject != null && !lastObject.finished) {
                lastObject.add(this.get(i));
                if (this.get(i) == '*' && lastObject instanceof ObjectBold) {
                    if (Pattern.matches("^\\*\\*.*\\*\\*$", lastObject.getMarkdownString())) {
                        lastObject.setFinished();
                    }
                } else if (this.get(i) == ')' && lastObject instanceof ObjectUrl) {
                    if (Pattern.matches("^\\[.*]\\(.*\\)$", lastObject.getMarkdownString())) {
                        lastObject.setFinished();
                    }
                } else if (this.get(i) == ')' && lastObject instanceof ObjectPicture) {
                    if (Pattern.matches("^!\\[.*]\\(.*\\)$", lastObject.getMarkdownString())) {
                        lastObject.setFinished();
                    }
                }
            } else {
                if (this.get(i) == '!' && Pattern.matches("^!\\[.*]\\(.*\\).*", line.substring(i))) {
                    objects.add(new ObjectPicture(this.get(i)));
                } else if (this.get(i) == '[' && Pattern.matches("^\\[.*]\\(.*\\).*", line.substring(i))) {
                    objects.add(new ObjectUrl(this.get(i)));
                } else if (this.get(i) == '*' && Pattern.matches("^\\*\\*.*\\*\\*.*", line.substring(i))) {
                    objects.add(new ObjectBold(this.get(i)));
                } else {
                    objects.add(new ObjectSingleChar(this.get(i)));
                }
            }
        }
        for (BaseObject object : objects) {
            s.append(object.generateBBCode());
        }
        return s.toString();
    }

    @Override
    public Pattern getPattern() {
        return null;
    }
}
