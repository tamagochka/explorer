package my.tamagochka.graphics.textureAtlas;

import java.awt.image.BufferedImage;

public class TextureAtlas {

    BufferedImage image;

    public TextureAtlas(ImageLoader loader, String imageName) {
        image = loader.loadImage(imageName);
    }

    public BufferedImage cut(int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
    }




}
