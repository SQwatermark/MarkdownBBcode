package moe.sqwatermark.bbdown.blocks;

public class BlockBv extends BaseBlock {
    public BlockBv(String s) {
        super(s);
    }

    @Override
    public String generateBBCode() {
        StringBuilder s = new StringBuilder();
        String bv = this.get(0).replace("::: bv", "").trim();
        if (bv.isEmpty()) bv = "未找到bv";
        String url = "https://www.bilibili.com/video/BV" + bv;
        s.append("[size = 3]").append("[b]")
                .append("[url=").append(url).append("]").append(url).append("[/url]")
                .append("[/b]").append("[/size]");
        return s.toString();
    }
}
