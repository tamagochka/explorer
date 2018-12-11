package my.tamagochka.game.entities;

import my.tamagochka.graphics.textureAtlas.DataOfAtlas;
import my.tamagochka.graphics.SpriteSheet;
import my.tamagochka.graphics.textureAtlas.TextureAtlas;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {

    private Map<DataOfAtlas, TextureAtlas> atlases = new HashMap<>();


    private EntityType type;
    private TextureAtlas atlas;
    private int spriteWidth;
    private int spriteHeight;
    private float scaleSize;

    private Map<DirectionMoving, SpriteSheet> spriteMap;

    public EntityFactory(EntityType type, TextureAtlas atlas, int spriteWidth, int spriteHeight, float scaleSize, int[][] sheets /* TODO */) {

        this.type = type;
        this.atlas = atlas;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.scaleSize = scaleSize;

//        spriteMap.put(DirectionMoving.NORTH, );




    }

    public Entity build(float x, float y, DirectionMoving direction, float speed) {
        Entity entity = null;
        switch(type) {
            case PLAYER:



                entity = new Player(type, x, y, direction, speed, spriteMap);
                break;

        }




        return null;
    }

    public Entity build(float x, float y) {
        switch(type) {
            case WALL:

                break;
        }
        return null;
    }


}
