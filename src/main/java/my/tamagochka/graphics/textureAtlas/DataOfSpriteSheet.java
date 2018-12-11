package my.tamagochka.graphics.textureAtlas;

public class DataOfSpriteSheet {

    private int l, t, w, h, sw, sh, sc;
    private boolean m;

    public DataOfSpriteSheet(int l, int t, int w, int h, int sw, int sh, int sc, boolean m) {
        this.l = l;
        this.t = t;
        this.w = w;
        this.h = h;
        this.m = m;
        this.sw = sw;
        this.sh = sh;
        this.sc = sc;
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

    public int getSpriteWidth() {
        return sw;
    }

    public int getSpriteHeight() {
        return sh;
    }

    public int getSpritesCount() {
        return sc;
    }
}
