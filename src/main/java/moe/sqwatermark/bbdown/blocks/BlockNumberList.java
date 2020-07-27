package moe.sqwatermark.bbdown.blocks;

public class BlockNumberList extends BaseBlock {
    public BlockNumberList(String s) {
        super(s);
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        s.append("[list=1]\n");
        for (String value : this) {
            String line = value.substring(value.indexOf(".") + 2);
            line = new BlockSingle(line).generateBBCode();
            line = line.replace("[size=3]", "").replace("[/size]", "");
            s.append("[*]").append(line).append("\n");
        }
        s.append("[/list]");
        return s.toString();
    }
}
