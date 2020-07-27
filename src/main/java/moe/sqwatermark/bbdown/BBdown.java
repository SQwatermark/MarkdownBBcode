package moe.sqwatermark.bbdown;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * markdown转为mcbbs的bbcode
 */
public class BBdown {

    public static void main(String[] args) {
        File file = new File("D:\\MINECRAFT\\vuepress\\ctm.md");
        MarkdownHolder markdownHolder = new MarkdownHolder(file);
        BBHolder bbHolder = new BBHolder(markdownHolder);
        File writename = new File("D:\\MINECRAFT\\vuepress\\ctm.md.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(writename), StandardCharsets.UTF_8));
            out.write(bbHolder.lines + "\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
