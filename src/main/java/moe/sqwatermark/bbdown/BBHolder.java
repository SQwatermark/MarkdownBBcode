package moe.sqwatermark.bbdown;

import moe.sqwatermark.bbdown.blocks.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

//TODO 拆分这个特长的类
/**
 * 包装一个用于mcbbs的bbcode文本文件
 */
public class BBHolder {

    public MarkdownHolder markdown;
    public String path = "";
    public String title = "NULL";
    public String subtitle;
    public String colorTitleBackground = "rgb(255,204,153)";
    public String colorTitleForeground = "rgb(255,153,102)";
    public String colorMainBackground = "rgb(255,255,206)";
    public String colorMainForeground = "rgb(255,255,243)";
    public ArrayList<BaseBlock> blocks;
    public StringBuilder lines;
    public StringBuilder titleBlock;
    public StringBuilder mainBlock;
    public StringBuilder mainBody;

    public BBHolder(MarkdownHolder markdownInput) {
        this.markdown = markdownInput;
        this.path = markdownInput.path + ".bbcode";
        this.setTitle();
        this.subtitle = markdownInput.name.replace("-", " ").replace("_", " ");
        this.blocks = new ArrayList<>();
        this.lines = new StringBuilder();
        this.titleBlock = new StringBuilder();
        this.mainBlock = new StringBuilder();
        this.mainBody = new StringBuilder();
        this.generateTitleBlock();
        this.generateBlocks();
        this.generateMainBody();
        this.generateMainBlock();
        this.generateLines();

    }

    /**
     * 设置标题
     * 第一行必须以# 开头作为一级标题
     */
    public void setTitle() {
        if (!markdown.lines.isEmpty()) {
            String titleLine = markdown.lines.get(0);
            if (titleLine.length() >= 3) {
                if (titleLine.startsWith("# ")) {
                    this.title = titleLine.replace("# ", "");
                }
            }
        }
    }

    /**
     * 生成标题块
     */
    public void generateTitleBlock() {
        titleBlock.append("[table]").append("\n");
        titleBlock.append("[tr=").append(colorTitleBackground).append("][td][align=center][table]").append("\n");
        titleBlock.append("[tr=").append(colorTitleForeground).append("][td][align=center][font=Tahoma][size=5][color=#ffffff]")
                .append(title).append("[/color][/size][/font][/align][align=center]");
        titleBlock.append("[font=Tahoma][size=3][color=#ffffff]").append(subtitle).append("[/color][/size][/font]").append("\n");
        titleBlock.append("[/align][/td][/tr]").append("\n");
        titleBlock.append("[/table][/align][/td][/tr]").append("\n");
        titleBlock.append("[/table]");
    }

    public void generateLines() {
        lines.append(titleBlock);
        lines.append(mainBlock);
    }

    /**
     * 给正文套上外壳
     */
    public void generateMainBlock() {
        mainBlock.append("[table]").append("\n");
        mainBlock.append("[tr=").append(colorMainBackground).append("][td][align=center][table]").append("\n");
        mainBlock.append("[tr=").append(colorMainForeground).append("][td]");
        mainBlock.append(mainBody);
        mainBlock.append("[/td][/tr]").append("\n");
        mainBlock.append("[/table][/align][/td][/tr]").append("\n");
        mainBlock.append("[/table]");
    }

    public void generateMainBody() {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) instanceof BlockCode || blocks.get(i) instanceof BlockQuote || blocks.get(i) instanceof BlockSpoiler) {
                mainBody.append(blocks.get(i).generateBBCode()).append("\n");
            } else if (blocks.get(i) instanceof BlockTitle && (((BlockTitle) blocks.get(i)).stage) > 3) {
                mainBody.append(blocks.get(i).generateBBCode()).append("\n");
            } else if (i+1 < blocks.size()) {
                if (blocks.get(i+1) instanceof BlockCode) {
                    mainBody.append(blocks.get(i).generateBBCode()).append("\n");
                } else {
                    mainBody.append(blocks.get(i).generateBBCode()).append("\n\n");
                }
            } else mainBody.append(blocks.get(i).generateBBCode()).append("\n\n");
        }
    }

    /**
     * 将所有行分为若干语句块
     */
    public void generateBlocks() {
        for (int i = 1; i < markdown.lines.size(); i++) {
            String thisLine = markdown.lines.get(i);
            String nextLine = i+1 < markdown.lines.size() ? markdown.lines.get(i+1) : "null";
            if (!blocks.isEmpty()) {
                BaseBlock lastObject = blocks.get(blocks.size()-1);
                if (!lastObject.finished) {
                    lastObject.add(thisLine);
                    if (thisLine.startsWith("```") && lastObject instanceof BlockCode) {
                        lastObject.setFinished();
                    } else if (thisLine.startsWith(":::") && (lastObject instanceof BlockTip || lastObject instanceof BlockWarning || lastObject instanceof BlockDanger || lastObject instanceof BlockSpoiler || lastObject instanceof BlockBv)) {
                        lastObject.setFinished();
                    } else if (lastObject instanceof BlockQuote && !nextLine.startsWith(">")) {
                        lastObject.setFinished();
                    } else if (lastObject instanceof BlockList && !nextLine.startsWith("- ")) {
                        lastObject.setFinished();
                    } else if (lastObject instanceof BlockNumberList && !Pattern.matches("^[0-9]*\\.\\s.*", nextLine)) {
                        lastObject.setFinished();
                    }
                    continue;
                }
            }
            BaseBlock newBlock;
            if (thisLine.startsWith("```")) {
                newBlock = new BlockCode(thisLine);
            } else if (thisLine.startsWith("::: tip")) {
                newBlock = new BlockTip(thisLine);
            } else if (thisLine.startsWith("::: warning")) {
                newBlock = new BlockWarning(thisLine);
            } else if (thisLine.startsWith("::: danger")) {
                newBlock = new BlockDanger(thisLine);
            } else if (thisLine.startsWith("::: bv")) {
                newBlock = new BlockBv(thisLine);
            } else if (thisLine.startsWith("::: details")) {
                newBlock = new BlockSpoiler(thisLine);
            } else if (thisLine.startsWith(">")) {
                newBlock = new BlockQuote(thisLine);
            } else  if (thisLine.startsWith("#")) {
                newBlock = new BlockTitle(thisLine);
            } else if (thisLine.startsWith("- ")) {
                newBlock = new BlockList(thisLine);
            } else if (thisLine.startsWith("1. ")) {
                newBlock = new BlockNumberList(thisLine);
            }
            else {
                newBlock = new BlockSingle(thisLine);
            }
            blocks.add(newBlock);

        }
    }

}
