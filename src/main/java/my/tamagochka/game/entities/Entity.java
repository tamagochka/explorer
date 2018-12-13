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
    private float x;
    private float y;
    private float speed;
    private float animationSpeed;
    private DirectionMoving direction;
    private Map<DirectionMoving, Sprite> spriteMap;


    private int distance = 0;
    private int currentFrame = 0;

    protected Entity(EntityType type, float x, float y, float speed,
                     DirectionMoving direction, float animationSpeed, Map<DirectionMoving, Sprite> spriteMap) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.animationSpeed = animationSpeed;
        this.direction = direction;
        this.spriteMap = spriteMap;
        this.index = nextIndex();
    }

    public int getIndex() {
        return index;
    }

    public void render(Graphics2D g) {
        spriteMap.get(direction).render(g, x, y, currentFrame);
    }

    public void nextFrame() {
        if(currentFrame >= spriteMap.get(direction).getCountFrames() - 1)
            currentFrame = 0;
        currentFrame++;
    }

    public void resetFrame() {
        currentFrame = 0;
    }


    public void update(Action action) {
        float newX = x;
        float newY = y;

        switch(action.getAction()) {
            case MOVE_UP:
                direction = DirectionMoving.NORTH;
                newY -= speed;
                break;
            case MOVE_DOWN:
                direction = DirectionMoving.SOUTH;
                newY += speed;
                break;
            case MOVE_LEFT:
                direction = DirectionMoving.WEST;
                newX -= speed;
                break;
            case MOVE_RIGHT:
                direction = DirectionMoving.EAST;
                newX += speed;
                break;
        }

        if(( x != newX) || ( y != newY)) {

            distance += speed;
            currentFrame = (int)(distance / animationSpeed) % spriteMap.get(direction).getCountFrames();
            // TODO collisions detection


        } else {
            distance = 0;
            currentFrame = 0;

        }

        x = newX;
        y = newY;

    }

}
