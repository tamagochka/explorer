package my.tamagochka.game.entities;

import my.tamagochka.graphics.sprites.Sprite;
import my.tamagochka.graphics.textureAtlas.AtlasManager;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {

    private AtlasManager atlasManager;
    private float scaleSize;

    public EntityFactory(AtlasManager atlasManager, float scaleSize) {
        this.atlasManager = atlasManager;
        this.scaleSize = scaleSize;
    }

    public Entity build(EntityType type, float x, float y, DirectionMoving direction, float speed) {
        Entity entity = null;
        switch(type) {
            case PLAYER:
                Map<DirectionMoving, Sprite> spriteMap = new HashMap<>();
                spriteMap.put(DirectionMoving.NORTH, new Sprite(atlasManager.getSpriteSheet("PLAYER", "NORTH"), scaleSize));
                spriteMap.put(DirectionMoving.EAST, new Sprite(atlasManager.getSpriteSheet("PLAYER", "EAST"), scaleSize));
                spriteMap.put(DirectionMoving.SOUTH, new Sprite(atlasManager.getSpriteSheet("PLAYER", "SOUTH"), scaleSize));
                spriteMap.put(DirectionMoving.WEST, new Sprite(atlasManager.getSpriteSheet("PLAYER", "WEST"), scaleSize));
                entity = new Player(x, y, direction, speed, spriteMap);
                break;

        }

        return entity;
    }

    public Entity build(EntityType type, float x, float y) {
        return build(type, x, y, DirectionMoving.NOPE, 0);
    }


}
