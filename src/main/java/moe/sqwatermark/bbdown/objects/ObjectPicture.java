package moe.sqwatermark.bbdown.objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片
 */
public class ObjectPicture extends BaseObject {

    public ObjectPicture(char c) {
        super(c);
    }

    @Override
    public String generateBBCode() {
        return "[img]" + getUrl() + "[/img]";
    }

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^!\\[.*]\\(.*\\)$");
    }

    private String getUrl() {
        Matcher matcher = Pattern.compile("(?<=\\().*(?=\\))").matcher(this.getMarkdownString());
        String url = "未找到图片地址";
        if (matcher.find()) {
            url = matcher.group(0);
        }
        return url;
    }

}
