package my.tamagochka.game.entities;

import my.tamagochka.graphics.sprites.Sprite;
import my.tamagochka.graphics.sprites.SpriteSheet;

import java.awt.*;
import java.util.Map;

public class Player extends Entity {

    private static final EntityType type = EntityType.PLAYER;

    public Player(float x, float y, DirectionMoving direction, float speed, Map<DirectionMoving, Sprite> spriteMap) {
        super(type, x, y, speed, direction, spriteMap);
    }

    @Override
    protected void update() {

    }
}
