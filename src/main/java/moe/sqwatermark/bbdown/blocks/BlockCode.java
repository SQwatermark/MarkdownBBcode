package moe.sqwatermark.bbdown.blocks;

public class BlockCode extends BaseBlock {

    public BlockCode(String s) {
        super(s);
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        s.append("[code]");
        for (int i = 1; i < this.size() - 1; i++) { // 不含首尾
            s.append(this.get(i));
            if (i != this.size()-2) s.append("\n");
        }
        s.append("[/code]");
        return s.toString();
    }

}
