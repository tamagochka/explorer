package my.tamagochka.game.entities;

import java.awt.*;

public abstract class Entity {

    private static int countEntities = 0;

    private static int nextIndex() {
        return countEntities++;
    }

    private EntityType type;
    private float x;
    private float y;
    private int index;

    protected Entity(EntityType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.index = nextIndex();
    }

    public int getIndex() {
        return index;
    }

    protected abstract void render(Graphics2D g);
    protected abstract void update(/* TODO interface updater */);

}
