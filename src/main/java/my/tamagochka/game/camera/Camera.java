package my.tamagochka.game.camera;

import java.awt.*;

public class Camera {

    Graphics2D graphics;
    ObservableObject object;

    private double cameraPosX;
    private double cameraPosY;
    private double curSpeed;
    private double maxSpeed;
    private double acceleration;
    private int frameWidth, frameHeight;

    public Camera(Graphics2D g, double maxSpeed, double acceleration, // if maxSpeed == 0 then camera moving instantly.
                  int frameWidth, int frameHeight, ObservableObject object) {
        graphics = g;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.cameraPosX = object.getObservePositionX() - frameWidth / 2;
        this.cameraPosY = object.getObservePositionY() - frameHeight / 2;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.object = object;
    }

    public void setObservableObject(ObservableObject object) {
        this.object = object;
    }

    public void update() { // calls 60 times at second
        int objectPosX = object.getObservePositionX() - frameWidth / 2;
        int objectPosY = object.getObservePositionY() - frameHeight / 2;
        if(maxSpeed == 0) { // if maxSpeed == 0 then camera moving instantly
            cameraPosX = objectPosX;
            cameraPosY = objectPosY;
            return;
        }
        double distance = (float)Math.sqrt(Math.pow(cameraPosX - objectPosX, 2) + Math.pow(cameraPosY - objectPosY, 2));
        if(distance > 0) {                          // calculating acceleration
            double braking_distance = 0;
            double tmp = curSpeed;
            while(tmp > 0) {
                braking_distance += tmp;
                tmp -= acceleration;
            }
            if(curSpeed < maxSpeed && braking_distance < distance)
                curSpeed += acceleration;
            else
                curSpeed -= acceleration;
        }
        if(distance > 0) {                          // calculating camera moving
            double x1 = cameraPosX, y1 = cameraPosY, x2 = objectPosX, y2 = objectPosY;
            double alpha = 0;
            if(x1 != x2) {
                if(y1 != y2) {
                    double k = (y2 - y1) / (x2 - x1);
                    alpha = k >= 0 ? Math.atan(k) : Math.PI - Math.atan(Math.abs(k));
                    alpha = y2 < y1 ? Math.PI + alpha : alpha;
                } else {
                    alpha = x2 < x1 ? Math.PI : 0;
                }
            } else {
                alpha = y2 < y1 ? Math.PI : 0;
            }
            double dx = Math.cos(alpha);
            double dy = Math.sin(alpha);
            if(distance < curSpeed) {
                cameraPosX = objectPosX;
                cameraPosY = objectPosY;
                curSpeed = 0;
            } else {
                cameraPosX = cameraPosX + dx * curSpeed;
                cameraPosY = cameraPosY + dy * curSpeed;
            }
        }
    }

    public boolean isCameraMoving() {
        return curSpeed > 0 ||
                cameraPosX != object.getObservePositionX() - frameWidth / 2 ||
                cameraPosY != object.getObservePositionY() - frameHeight / 2;
    }

    public void render(Renderable renderableObject) {
        renderableObject.render(graphics, this);
    }

    public int getCameraPosX() {
        return (int)Math.round(cameraPosX);
    }

    public int getCameraPosY() {
        return (int)Math.round(cameraPosY);
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

}
