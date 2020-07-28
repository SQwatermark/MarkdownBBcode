package moe.sqwatermark.bbdown;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * markdown转为mcbbs的bbcode
 */
public class BBdown {

    public static void main(String[] args) {
        File dir = new File("./mds");
        if (!dir.exists()) dir.mkdir();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("md")) {
                MarkdownHolder markdownHolder = new MarkdownHolder(file);
                BBHolder bbHolder = new BBHolder(markdownHolder);
                File writename = new File(file.getAbsolutePath() + ".txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
                try {
                    writename.createNewFile(); // 创建新文件
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(writename), StandardCharsets.UTF_8));
                    out.write(bbHolder.lines.toString());
                    out.flush(); // 把缓存区内容压入文件
                    out.close(); // 最后记得关闭文件
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
                                                                            //TODO 添加更多宏
                                                                            //TODO 配置文件
                                                                            //TODO 检查文件夹内所有md文件
                                                                            //TODO 自动上传图片到sm.ms
