package my.tamagochka.game.entities;

import my.tamagochka.graphics.sprites.Sprite;

import java.util.Map;

public class Player extends Entity {

    private static final EntityType type = EntityType.PLAYER;

    public Player(float x, float y, DirectionMoving direction, float speed,
                  float animationSpeed, Map<DirectionMoving, Sprite> spriteMap,
                  int cameraHorizontalOffset, int cameraVerticalOffset) {
        super(type, x, y, speed, direction, animationSpeed, spriteMap,
                cameraHorizontalOffset, cameraVerticalOffset);
    }

}
