package my.tamagochka.graphics.sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {

    private SpriteSheet sheet;
    private float scale;
    private ArrayList<BufferedImage> images;

    private int width;
    private int height;
    private int scaledWidth;
    private int scaledHeight;

    public Sprite(SpriteSheet sheet, float scale) {
        this.sheet = sheet;
        this.scale = scale;
        images = new ArrayList<>();
        width = sheet.getWidth();
        height = sheet.getHeight();
        resize(scale);
    }

    public void resize(float newScale) {
        scale = newScale;
        images.clear();
        scaledWidth = (int)(width * scale);
        scaledHeight = (int)(height * scale);
        for(int i = 0; i < sheet.getSpriteCount(); i++) {
            BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            newImage.getGraphics().drawImage(sheet.getSprite(i), 0, 0, scaledWidth, scaledHeight, null);
            images.add(newImage);
        }
    }

    public void render(Graphics2D g, float x, float y, int num) {
        g.drawImage(images.get(num), (int)x, (int)y, null);
    }

    public int getCountFrames() {
        return images.size();
    }

    public int getScaledWidth() {
        return scaledWidth;
    }

    public int getScaledHeight() {
        return scaledHeight;
    }

}
