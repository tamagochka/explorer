package my.tamagochka.graphics.sprites;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;
    private int spriteCount; // count sprites at image
    private int width; // one sprite size
    private int height;
    private int spritesInLine; // count sprites at one line of image

    public SpriteSheet(BufferedImage sheet, int spriteCount, int width, int height) {
        this.sheet = sheet;
        this.spriteCount = spriteCount;
        this.width = width;
        this.height = height;
        this.spritesInLine = sheet.getWidth() / width; // count sprites in one line
    }

    public BufferedImage getSprite(int index) {
        index = index % (spriteCount); // 0 <= index < spriteCount
        int x = index % spritesInLine * width;
        int y = index / spritesInLine * height;
        return sheet.getSubimage(x, y, width, height);
    }

    public int getSpriteCount() {
        return spriteCount;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
