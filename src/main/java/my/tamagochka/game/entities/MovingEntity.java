package my.tamagochka.game.entities;

import my.tamagochka.graphics.SpriteSheet;

import java.awt.*;
import java.util.Map;

public abstract class MovingEntity extends Entity {

    private DirectionMoving direction;
    private float speed;

    private Map<DirectionMoving, SpriteSheet> spriteMap;

    protected MovingEntity(EntityType type, float x, float y, DirectionMoving direction, float speed, Map<DirectionMoving, SpriteSheet> spriteMap) {
        super(type, x, y);
        this.speed = speed;
        this.direction = direction;
        this.spriteMap = spriteMap;
    }

    @Override
    public void render(Graphics2D g) {

    }

}
