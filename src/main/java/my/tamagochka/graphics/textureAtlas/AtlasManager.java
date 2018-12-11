package my.tamagochka.graphics.textureAtlas;

import my.tamagochka.graphics.sprites.SpriteSheet;
import my.tamagochka.graphics.utilities.Pair;

import java.util.LinkedList;
import java.util.List;

public class AtlasManager {

    private List<Pair<DataOfAtlas, TextureAtlas>> data;

    public AtlasManager() {
        data = new LinkedList();
    }

    public void addAtlas(ImageLoader imageLoader, XMLLoader xmlLoader, String fileName) {
        DataOfAtlas dataOfAtlas = new DataOfAtlas(xmlLoader, fileName);
        TextureAtlas textureAtlas = new TextureAtlas(imageLoader, dataOfAtlas.getImageFileName());
        data.add(new Pair<>(dataOfAtlas, textureAtlas));
    }

    public SpriteSheet getSpriteSheet(String entityName, String spriteSheetName) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).first.getEntityByName(entityName) != null) {
                SpriteSheet spriteSheet = new SpriteSheet(
                        data.get(i).second.cut(
                                data.get(i).first.getEntityByName(entityName).getSpriteSheetByName(spriteSheetName).getLeft(),
                                data.get(i).first.getEntityByName(entityName).getSpriteSheetByName(spriteSheetName).getTop(),
                                data.get(i).first.getEntityByName(entityName).getSpriteSheetByName(spriteSheetName).getWidth(),
                                data.get(i).first.getEntityByName(entityName).getSpriteSheetByName(spriteSheetName).getHeight()
                        ),
                        data.get(i).first.getEntityByName(entityName).getSpriteSheetByName(spriteSheetName).getSpritesCount(),
                        data.get(i).first.getEntityByName(entityName).getSpriteSheetByName(spriteSheetName).getWidth(),
                        data.get(i).first.getEntityByName(entityName).getSpriteSheetByName(spriteSheetName).getHeight()
                );
                return spriteSheet;
            }
        }
        return null;
    }

}
