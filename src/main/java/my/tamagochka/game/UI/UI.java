package my.tamagochka.game.UI;

import my.tamagochka.game.camera.Camera;
import my.tamagochka.game.camera.Renderable;
import my.tamagochka.graphics.sprites.Sprite;
import my.tamagochka.graphics.textureAtlas.AtlasManager;

import java.awt.*;
import java.util.Date;

public class UI implements Renderable {

    private Darkness darkness;
    private GameTimer gameTimer;

    public UI(AtlasManager atlasManager, float scaleSize) {
        darkness = new Darkness(
                new Sprite(atlasManager.getSpriteSheet("UI", "DARKNESS"), scaleSize),
                new Sprite(atlasManager.getSpriteSheet("UI", "LIGHT_RADIUS"), scaleSize),
                5
        );

        gameTimer = new GameTimer(10, 10, new Sprite(atlasManager.getSpriteSheet("UI", "DIGITS"), scaleSize));

    }

    public Date getGameTimer() {
        return gameTimer.getTime();
    }

    public void startGameTimer() {
        gameTimer.startTimer();
    }

    public void pauseGameTimer() {
        gameTimer.pauseTimer();
    }

    public void stopGameTimer() {
        gameTimer.stopTimer();
    }

    @Override
    public void render(Graphics2D g, Camera camera) {
        darkness.render(g, camera);
        gameTimer.render(g);



    }
}
