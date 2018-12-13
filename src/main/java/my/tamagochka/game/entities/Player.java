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
    public void update(Action action) {
        float newX = super.x;
        float newY = super.y;

        switch(action.getAction()) {
            case MOVE_UP:
                super.direction = DirectionMoving.NORTH;
                newY -= super.speed;
                break;
            case MOVE_DOWN:
                super.direction = DirectionMoving.SOUTH;
                newY += super.speed;
                break;
            case MOVE_LEFT:
                super.direction = DirectionMoving.WEST;
                newX -= super.speed;
                break;
            case MOVE_RIGHT:
                super.direction = DirectionMoving.EAST;
                newX += super.speed;
                break;
            default:
                return;
        }
        if((super.x != newX) || (super.y != newY)) {



        }

        super.x = newX;
        super.y = newY;
    }
}
