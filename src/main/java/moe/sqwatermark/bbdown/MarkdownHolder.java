package moe.sqwatermark.bbdown;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 包装一个markdown文本文件
 */
public class MarkdownHolder {

    public String name;
    public String path;
    public ArrayList<String> lines;

    public MarkdownHolder(File file) {
        lines = new ArrayList<>();
        path = file.getAbsolutePath();
        name = file.getName();
        // 读取文件
        try {
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
