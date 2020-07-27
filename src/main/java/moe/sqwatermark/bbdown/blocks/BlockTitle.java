package moe.sqwatermark.bbdown.blocks;

public class BlockTitle extends BaseBlock {

    public String title = "NULL";
    public int stage;

    public BlockTitle(String s) {
        super(s);
        setFinished();
        String prefix = "###";
        String[] split = s.split(" ");
        if (split.length > 0) {
            prefix = split[0];
            title = s.replace(split[0] + " ", "");
        }
        stage = prefix.length();
    }

    @Override
    public String generateBBCode() {
        return "[size=" + (7 - stage) + "][b]" +
                title +
                "[/b][/size]";
    }

}