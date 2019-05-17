package hzkj.cc.menuview;

public class ListItem {
    int drawableId;
    String text;

    public int getDrawableId() {
        return drawableId;
    }

    public String getText() {
        return text;
    }

    public ListItem(String text) {
        this.text = text;
    }

    public ListItem(int drawableId, String text) {
        this.drawableId = drawableId;
        this.text = text;
    }
}
