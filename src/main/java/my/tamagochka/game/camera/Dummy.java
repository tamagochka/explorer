package my.tamagochka.game.camera;

public class Dummy implements ObservableObject {

    private float x;
    private float y;

    public Dummy(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public int getObservePositionX() {
        return (int)x;
    }

    @Override
    public int getObservePositionY() {
        return (int)y;
    }
}
