package moe.sqwatermark.bbdown.objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectUrl extends BaseObject {

    public ObjectUrl(char c) {
        super(c);
    }

    @Override
    public String generateBBCode() { ;
        StringBuilder s = new StringBuilder();
        String markdownString = getMarkdownString();

        // 获取文本
        String text = "未找到URL内的文本";
        Matcher textMatcher = Pattern.compile("(?<=\\[).*(?=])").matcher(markdownString);
        if (textMatcher.find()) text = textMatcher.group(0);
        text = new ObjectText(text.toCharArray()).generateBBCode();

        //获取URL
        String url = "未找到URL";
        Matcher urlMatcher = Pattern.compile("(?<=\\().*(?=\\))").matcher(markdownString);
        if (urlMatcher.find()) url = urlMatcher.group(0);

        //生成bbcode
        s.append("[url=").append(url).append("]").append(text).append("[/url]");
        return s.toString();
    }

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^\\[.*]\\(.*\\)$");
    }

}
