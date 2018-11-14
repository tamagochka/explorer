package my.tamagochka.game.entities;

import my.tamagochka.graphics.SpriteSheet;

import java.util.Map;

public class Player extends MovingEntity {

    public Player(EntityType type, float x, float y, DirectionMoving dirrection, float speed, Map<DirectionMoving, SpriteSheet> spriteMap) {
        super(type, x, y, dirrection, speed, spriteMap);
    }

    @Override
    protected void update() {

    }
}
