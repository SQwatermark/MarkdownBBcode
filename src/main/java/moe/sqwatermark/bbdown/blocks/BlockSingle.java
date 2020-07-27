package moe.sqwatermark.bbdown.blocks;

import moe.sqwatermark.bbdown.objects.ObjectText;

public class BlockSingle extends BaseBlock {

    public BlockSingle(String s) {
        super(s);
        this.setFinished();
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        s.append("[size=3]");
        s.append(new ObjectText(this.get(0).toCharArray()).generateBBCode());
        s.append("[/size]");
        return s.toString();
    }
}
