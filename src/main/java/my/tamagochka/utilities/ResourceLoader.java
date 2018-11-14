package my.tamagochka.utilities;

import my.tamagochka.graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ResourceLoader implements ImageLoader {

    private String PATH = "res/";

    public ResourceLoader(String path) {
        this.PATH = path;
    }

    @Override
    public BufferedImage loadImage(String fileName) {
        BufferedImage image = (BufferedImage)Toolkit.getDefaultToolkit().getImage(fileName);
        return image;
    }

    public

}
