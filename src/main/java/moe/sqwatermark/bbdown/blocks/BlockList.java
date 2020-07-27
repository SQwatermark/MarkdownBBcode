package moe.sqwatermark.bbdown.blocks;

public class BlockList extends BaseBlock {

    public BlockList(String s) {
        super(s);
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        s.append("[list]\n");
        for (String value : this) {
            String line = value.substring(2);
            line = new BlockSingle(line).generateBBCode();
            line = line.replace("[size=3]", "").replace("[/size]", "");
            s.append("[*]").append(line).append("\n");
        }
        s.append("[/list]");
        return s.toString();
    }
}
