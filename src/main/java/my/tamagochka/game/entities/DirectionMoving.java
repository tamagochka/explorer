package my.tamagochka.game.entities;

public enum DirectionMoving {

    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0),
    NOPE(0, 0);

    private int vx, vy;

    DirectionMoving(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

}
