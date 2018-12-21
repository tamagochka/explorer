package my.tamagochka.game.level;

import my.tamagochka.game.camera.Camera;
import my.tamagochka.game.camera.Renderable;
import my.tamagochka.graphics.sprites.Sprite;
import my.tamagochka.graphics.textureAtlas.AtlasManager;
import my.tamagochka.maze.generator.*;
import my.tamagochka.maze.generator.Dimension;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Level implements Renderable {

    private int[][] tileMap;
    private Map<TileType, Tile> tiles;

    protected Level(int width, int height,
                    int startX, int startY,
                    int finishX, int finishY,
                    AtlasManager atlasManager, float scaleSize) {
        tiles = new HashMap<>();
        tiles.put(TileType.EMPTY, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "EMPTY"), scaleSize), TileType.EMPTY));
        tiles.put(TileType.WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "WALL"), scaleSize), TileType.WALL));
        tiles.put(TileType.EMPTY_SHD, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "EMPTY_SHADOW_DOWN"), scaleSize), TileType.EMPTY_SHD));
        tiles.put(TileType.EMPTY_SHR, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "EMPTY_SHADOW_RIGHT"), scaleSize), TileType.EMPTY_SHR));
        tiles.put(TileType.EMPTY_SHDR, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "EMPTY_SHADOW_DOWN_RIGHT"), scaleSize), TileType.EMPTY_SHDR));
        tiles.put(TileType.EMPTY_CLOSED_DOOR, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "EMPTY_CLOSED_DOOR"), scaleSize), TileType.EMPTY_CLOSED_DOOR));
        tiles.put(TileType.EMPTY_OPENED_DOOR, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "EMPTY_OPENED_DOOR"), scaleSize), TileType.EMPTY_OPENED_DOOR));
        // level generation
        MazeFactory mazeFactory = new MazeFactory(new DefaultTangler());
        Maze maze = null;
        try {
            maze = mazeFactory.generate(new Dimension(width, height),
                    new Dimension(startX, startY), new Dimension(finishX, finishY));
        } catch(MazeWrongSizeException e) {
            e.printStackTrace();
        }

        tileMap = new int[height][width];
        for(int j = 0; j < maze.getSize().vertical(); j++) {
            for(int i = 0; i < maze.getSize().horizontal(); i++) {
                switch(maze.getPoint(new Dimension(i, j))) {
                    case 0:
                        if(maze.getPoint(new Dimension(i, j)) == 0) {
                            if(maze.getPoint(new Dimension(i + 1, j)) == 1)
                                tileMap[j][i] = TileType.EMPTY_SHR.getNum();
                            if(maze.getPoint(new Dimension(i, j + 1)) == 1)
                                tileMap[j][i] = TileType.EMPTY_SHD.getNum();
                            if(maze.getPoint(new Dimension(i + 1, j)) == 1 &&
                                    maze.getPoint(new Dimension(i, j + 1)) == 1)
                                tileMap[j][i] = TileType.EMPTY_SHDR.getNum();
                        }
                        break;
                    case 1:
                        tileMap[j][i] = TileType.WALL.getNum();
                        break;
                    case 5: // start point
                        tileMap[j][i] = TileType.EMPTY_CLOSED_DOOR.getNum();
                        break;
                    case 7: // end point
                        tileMap[j][i] = TileType.EMPTY_OPENED_DOOR.getNum();
                        break;
                }
            }
        }

    }

    public TileType getTileType(int x, int y) {
//        return TileType.fromNum(tileMap[][]);
        return null;
    }

    public void render(Graphics2D g, Camera camera) {
        for(int i = 0; i < tileMap.length; i++) {
            for(int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.fromNum(tileMap[i][j]));
                tile.render(g,
                        (int)(j * tile.getWidth()) - camera.getCameraPosX(),
                        (int)(i * tile.getHeight()) - camera.getCameraPosY(),
                        0);
            }
        }
    }

}
