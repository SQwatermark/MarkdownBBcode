package moe.sqwatermark.bbdown.blocks;

public class BlockQuote extends BaseBlock {
    public BlockQuote(String s) {
        super(s);
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        s.append("[quote]");
        for (int i = 0; i < this.size(); i++) {
            String line = this.get(i).replace(">", "");
            line = new BlockSingle(line).generateBBCode();
            if (!line.isEmpty()) s.append(line);
            if (i != this.size()-1) s.append("\n");
        }
        s.append("[/quote]");
        return s.toString();
    }
}
