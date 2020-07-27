package moe.sqwatermark.bbdown.blocks;

import java.util.ArrayList;

public abstract class BaseBlock extends ArrayList<String> {

    public BaseBlock(String s) {
        this.add(s);
    }

    public boolean finished = false;

    public abstract String generateBBCode();

    public void setFinished() {
        this.finished = true;
    }

}
