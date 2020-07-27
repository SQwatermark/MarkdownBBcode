package moe.sqwatermark.bbdown.blocks;

public class BlockSpoiler extends BaseBlock {
    public BlockSpoiler(String s) {
        super(s);
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        s.append("[spoiler]");
        for (int i = 1; i < this.size()-1; i++) {
            String line = new BlockSingle(this.get(i)).generateBBCode();
            s.append(line);
            if (i != this.size()-2) s.append("\n\n");
        }
        s.append("[/spoiler]");
        return s.toString();
    }
}