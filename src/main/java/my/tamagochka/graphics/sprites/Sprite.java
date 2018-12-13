package my.tamagochka.graphics.sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {

    private SpriteSheet sheet;
    private float scale;
    private ArrayList<BufferedImage> images;

    public Sprite(SpriteSheet sheet, float scale) {
        this.sheet = sheet;
        this.scale = scale;
        images = new ArrayList<>();
        for(int i = 0; i < sheet.getSpriteCount(); i++) {
            BufferedImage image = sheet.getSprite(i);
            int newWidth = (int) (image.getWidth() * scale);
            int newHeight = (int) (image.getHeight() * scale);
            BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            newImage.getGraphics().drawImage(image, 0, 0, newWidth, newHeight, null);
            images.add(newImage);
        }
    }

    public void render(Graphics2D g, float x, float y, int num) {
        g.drawImage(images.get(num), (int)x, (int)y, null);
    }

}
