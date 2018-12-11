package my.tamagochka.graphics.textureAtlas;

public class DataOfSpriteSheet {

    private int l, t, w, h;
    private boolean m;

    public DataOfSpriteSheet(int l, int t, int w, int h, boolean m) {
        this.l = l;
        this.t = t;
        this.w = w;
        this.h = h;
        this.m = m;
    }

    public int getLeft() {
        return l;
    }

    public int getTop() {
        return t;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public boolean isMirroring() {
        return m;
    }
}
