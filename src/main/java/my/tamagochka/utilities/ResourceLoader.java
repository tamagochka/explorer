package my.tamagochka.utilities;

import my.tamagochka.graphics.textureAtlas.ImageLoader;
import my.tamagochka.graphics.textureAtlas.XMLLoader;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceLoader implements ImageLoader, XMLLoader {

    private String PATH = "resources/";

    public ResourceLoader(String path) {
        this.PATH = path;
    }

    @Override
    public BufferedImage loadImage(String fileName) {
        BufferedImage image = (BufferedImage)Toolkit.getDefaultToolkit().getImage(PATH + fileName);
        return image;
    }

    @Override
    public Document loadXMLDocument(String fileName) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(PATH + fileName);
            return doc;
        } catch(ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
