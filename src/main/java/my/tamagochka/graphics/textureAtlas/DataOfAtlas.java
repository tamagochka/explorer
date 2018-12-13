package my.tamagochka.graphics.textureAtlas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

public class DataOfAtlas {

    private String imageFileName;
    private Map<String, DataOfEntity> entities;

    public DataOfAtlas(XMLLoader loader, String fileName) {
        entities = new HashMap<>();

        Document doc = loader.loadXMLDocument(fileName); // TODO if doc == null (not load xml-file)?
        NodeList nodeList = doc.getElementsByTagName("textureAtlas");
        imageFileName = ((Element) nodeList.item(0)).getAttribute("file");

        NodeList entities = doc.getElementsByTagName("entities");
        entities = ((Element) entities.item(0)).getElementsByTagName("entity");
        for(int i = 0; i < entities.getLength(); i++) {
            Element entity = (Element) entities.item(i);
            String entityName = entity.getAttribute("name");

            DataOfEntity dataOfEntity = new DataOfEntity();

            NodeList spriteSheets = entity.getElementsByTagName("spriteSheets");
            spriteSheets = ((Element) spriteSheets.item(0)).getElementsByTagName("spriteSheet");
            for(int j = 0; j < spriteSheets.getLength(); j++) {
                Element spriteSheet = (Element) spriteSheets.item(j);
                String spriteSheetName = spriteSheet.getAttribute("name");
                int l = Integer.valueOf(((Element) spriteSheet.getElementsByTagName("l").item(0)).getTextContent());
                int t = Integer.valueOf(((Element) spriteSheet.getElementsByTagName("t").item(0)).getTextContent());
                int w = Integer.valueOf(((Element) spriteSheet.getElementsByTagName("w").item(0)).getTextContent());
                int h = Integer.valueOf(((Element) spriteSheet.getElementsByTagName("h").item(0)).getTextContent());
                int sw = Integer.valueOf(((Element) spriteSheet.getElementsByTagName("sw").item(0)).getTextContent());
                int sh = Integer.valueOf(((Element) spriteSheet.getElementsByTagName("sh").item(0)).getTextContent());
                int sc = Integer.valueOf(((Element) spriteSheet.getElementsByTagName("sc").item(0)).getTextContent());
                DataOfSpriteSheet dataOfSpriteSheet = new DataOfSpriteSheet(l, t, w, h, sw, sh, sc);
                dataOfEntity.addSpriteSheet(spriteSheetName, dataOfSpriteSheet);
            }

            addEntity(entityName, dataOfEntity);
        }

    }

    private void addEntity(String entityName, DataOfEntity entity) {
        entities.put(entityName, entity);
    }

    public DataOfEntity getEntityByName(String entityName) {
        return entities.get(entityName);
    }

    public String getImageFileName() {
        return imageFileName;
    }

}
