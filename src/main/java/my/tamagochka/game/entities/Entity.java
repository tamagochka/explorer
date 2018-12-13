package my.tamagochka.game.entities;

import my.tamagochka.graphics.sprites.Sprite;

import java.awt.*;
import java.util.Map;

public abstract class Entity {

    private static int countEntities = 0;

    private static int nextIndex() {
        return countEntities++;
    }

    private int index;

    private EntityType type;
    protected float x;
    protected float y;
    protected float speed;
    protected DirectionMoving direction;
    private Map<DirectionMoving, Sprite> spriteMap;

    protected Entity(EntityType type, float x, float y, float speed,
                     DirectionMoving direction, Map<DirectionMoving, Sprite> spriteMap) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.spriteMap = spriteMap;
        this.index = nextIndex();
    }

    public int getIndex() {
        return index;
    }

    public void render(Graphics2D g) {
        spriteMap.get(direction).render(g, x, y, 0);
    }

    public abstract void update(Action action);

}
