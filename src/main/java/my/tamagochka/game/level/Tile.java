package my.tamagochka.game.level;

import my.tamagochka.graphics.sprites.Sprite;

import java.awt.*;

public class Tile {

    private Sprite sprite;
    private TileType type;

    public Tile(Sprite sprite, TileType type) {
        this.sprite = sprite;
        this.type = type;
    }

    protected void render(Graphics2D g, int x, int y, int num) {
        sprite.render(g, x, y, num);
    }

    public int getWidth() {
        return sprite.getScaledWidth();
    }

    public int getHeight() {
        return sprite.getScaledHeight();
    }

}
