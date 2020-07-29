package moe.sqwatermark.bbdown.blocks;

public class BlockWarning extends BaseBlock {
    public BlockWarning(String s) {
        super(s);
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        String prefix = this.get(0).replace("::: warning", "").trim();
        if (prefix.isEmpty()) prefix = "注意";
        s.append("[color=#8b0000]");
        s.append("[size=3][b]").append(prefix).append("：").append("[/b][/size]");
        for (int i = 1; i < this.size()-1; i++) {
            s.append(new BlockSingle(this.get(i)).generateBBCode());
            if (i != this.size()-2) s.append("\n");
        }
        s.append("[/color]");
        return s.toString();
    }
}