package my.tamagochka.graphics.textureAtlas;

import java.util.HashMap;
import java.util.Map;

public class DataOfEntity {

    private Map<String, DataOfSpriteSheet> spriteSheets;

    public DataOfEntity() {
        spriteSheets = new HashMap<>();
    }

    public void addSpriteSheet(String spriteSheetName, DataOfSpriteSheet spriteSheet) {
        spriteSheets.put(spriteSheetName, spriteSheet);
    }

    public DataOfSpriteSheet getSpriteSheetByName(String spriteSheetName) {
        return spriteSheets.get(spriteSheetName);
    }

}
