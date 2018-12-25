package my.tamagochka.game.UI;

import my.tamagochka.game.camera.Camera;
import my.tamagochka.game.camera.Renderable;
import my.tamagochka.graphics.sprites.Sprite;
import my.tamagochka.graphics.textureAtlas.AtlasManager;

import java.awt.*;

public class UI implements Renderable {

    private Darkness darkness;

    public UI(AtlasManager atlasManager, float scaleSize) {
        darkness = new Darkness(
                new Sprite(atlasManager.getSpriteSheet("UI", "DARKNESS"), scaleSize),
                new Sprite(atlasManager.getSpriteSheet("UI", "LIGHT_RADIUS"), scaleSize),
                5
        );



    }

    @Override
    public void render(Graphics2D g, Camera camera) {
        darkness.render(g, camera);



    }
}
