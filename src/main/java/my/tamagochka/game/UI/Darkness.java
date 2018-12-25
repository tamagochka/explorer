package my.tamagochka.game.UI;

import my.tamagochka.game.camera.Camera;
import my.tamagochka.graphics.sprites.Sprite;

import java.awt.*;

public class Darkness {

    private Sprite darkness;
    private Sprite lightCircle;

    private float lightRadius;

    public Darkness(Sprite darkness, Sprite lightCircle, float lightRadius) {
        this.darkness = darkness;
        this.lightCircle = lightCircle;
        this.lightRadius = lightRadius;
        lightCircle.resize(lightRadius);
    }

    public void setLightRadius(int newLightRadius) {
        lightRadius = newLightRadius;
        lightCircle.resize(lightRadius);
    }

    public void render(Graphics2D g, Camera camera) {
        int xLightPos = camera.getFrameWidth() / 2 - (lightCircle.getScaledWidth() / 2);
        int yLightPos = camera.getFrameHeight() / 2 - (lightCircle.getScaledHeight() / 2);
        lightCircle.render(g, xLightPos, yLightPos, 0);
        int y = camera.getFrameHeight();
        while(y > 0) {
            y -= darkness.getScaledHeight();
            int x1 = xLightPos;
            while(x1 > 0) {
                x1 -= darkness.getScaledWidth();
                darkness.render(g, x1, y, 0);
            }
            int x2 = xLightPos + lightCircle.getScaledWidth() - darkness.getScaledWidth();
            while(x2 < camera.getFrameWidth()) {
                x2 += darkness.getScaledWidth();
                darkness.render(g, x2, y, 0);
            }
        }
        int x = xLightPos + lightCircle.getScaledWidth();
        while(x > xLightPos) {
            x -= darkness.getScaledWidth();
            int y1 = yLightPos;
            while(y1 > 0) {
                y1 -= darkness.getScaledHeight();
                darkness.render(g, x, y1, 0);
            }
            int y2 = yLightPos + lightCircle.getScaledHeight() - darkness.getScaledHeight();
            while(y2 < camera.getFrameHeight()) {
                y2 += darkness.getScaledHeight();
                darkness.render(g, x, y2, 0);
            }
        }
    }
}
