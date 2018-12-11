package my.tamagochka.game.entities;

import my.tamagochka.graphics.sprites.SpriteSheet;
import my.tamagochka.graphics.textureAtlas.AtlasManager;

import java.util.Map;

public class EntityFactory {

    private AtlasManager atlasManager;
    private float scaleSize;

    private Map<DirectionMoving, SpriteSheet> spriteMap;

    public EntityFactory(AtlasManager atlasManager, float scaleSize) {
        this.atlasManager = atlasManager;
        this.scaleSize = scaleSize;

//        spriteMap.put(DirectionMoving.NORTH, );


    }

    public Entity build(EntityType type, float x, float y, DirectionMoving direction, float speed) {
        Entity entity = null;
        switch(type) {
            case PLAYER:



                entity = new Player(type, x, y, direction, speed, spriteMap);
                break;

        }




        return null;
    }

    public Entity build(EntityType type, float x, float y) {
        switch(type) {
            case WALL:

                break;
        }
        return null;
    }


}
