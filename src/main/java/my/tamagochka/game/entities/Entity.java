package my.tamagochka.game.entities;

import my.tamagochka.game.camera.Camera;
import my.tamagochka.game.camera.ObservableObject;
import my.tamagochka.game.camera.Renderable;
import my.tamagochka.game.engine.Collisional;
import my.tamagochka.graphics.sprites.Sprite;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Entity implements Renderable, ObservableObject {

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
    private int cameraHorizontalOffset;
    private int cameraVerticalOffset;

    private int distance = 0;
    private int currentFrame = 0;

    private List<Collisional> barriers;

    protected Entity(EntityType type, float x, float y, float speed,
                     DirectionMoving direction, float animationSpeed, Map<DirectionMoving, Sprite> spriteMap,
                     int cameraHorizontalOffset, int cameraVerticalOffset) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.animationSpeed = animationSpeed;
        this.direction = direction;
        this.spriteMap = spriteMap;
        this.index = nextIndex();
        this.cameraHorizontalOffset = cameraHorizontalOffset;
        this.cameraVerticalOffset = cameraVerticalOffset;
        barriers = new LinkedList<>();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void render(Graphics2D g, Camera camera) {
        spriteMap.get(direction).render(g, x - camera.getCameraPosX(), y - camera.getCameraPosY(), currentFrame);
    }

    public void addBarrier(Collisional barrier) {
        barriers.add(barrier);
    }

    public void update(Action action) {
        float newX = x;
        float newY = y;

        switch(action.getAction()) {
            case MOVE_UP:
                direction = DirectionMoving.NORTH;
                newY += speed * direction.getVy();
                break;
            case MOVE_DOWN:
                direction = DirectionMoving.SOUTH;
                newY += speed * direction.getVy();
                break;
            case MOVE_LEFT:
                direction = DirectionMoving.WEST;
                newX += speed * direction.getVx();
                break;
            case MOVE_RIGHT:
                direction = DirectionMoving.EAST;
                newX += speed * direction.getVx();
                break;
        }

        if((x != newX) || (y != newY)) {

            distance += speed;
            currentFrame = (int)(distance / animationSpeed) % spriteMap.get(direction).getCountFrames();
            // TODO will need upgrade changing frames
            // TODO collisions detection

            boolean collision = false;
            for(Collisional barrier : barriers) {
                if(barrier.checkCollision((int)newX, (int)newY) ||
                        barrier.checkCollision((int)newX, (int)newY + spriteMap.get(direction).getScaledHeight()) ||
                        barrier.checkCollision((int)newX + spriteMap.get(direction).getScaledWidth(), (int)newY) ||
                        barrier.checkCollision((int)newX + spriteMap.get(direction).getScaledWidth(),
                                (int)newY + spriteMap.get(direction).getScaledHeight())) {
                    distance = 0;
                    currentFrame = 0;
                    return;
                }
            }

        } else {
            distance = 0;
            currentFrame = 0;
        }

        x = newX;
        y = newY;

    }

    @Override
    public int getObservePositionX() {
        return (int)x + spriteMap.get(direction).getScaledWidth() / 2 + (cameraHorizontalOffset * direction.getVx());
    }

    @Override
    public int getObservePositionY() {
        return (int)y + spriteMap.get(direction).getScaledHeight() / 2 + (cameraVerticalOffset * direction.getVy());
    }

}
