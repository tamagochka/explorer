package my.tamagochka.game.level;

import my.tamagochka.graphics.textureAtlas.AtlasManager;

public class LevelFactory {

    private AtlasManager atlasManager;
    private float scaleSize;

    public LevelFactory(AtlasManager atlasManager, float scaleSize) {
        this.atlasManager = atlasManager;
        this.scaleSize = scaleSize;
    }

    public Level generate(int width, int height, int startX, int startY, int finishX, int finishY) {
        return new Level(width, height, startX, startY, finishX, finishY, atlasManager, scaleSize);
    }
}
