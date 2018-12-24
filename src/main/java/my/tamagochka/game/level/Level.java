package my.tamagochka.game.level;

import my.tamagochka.game.camera.Camera;
import my.tamagochka.game.camera.Renderable;
import my.tamagochka.game.engine.Collisional;
import my.tamagochka.graphics.sprites.Sprite;
import my.tamagochka.graphics.textureAtlas.AtlasManager;
import my.tamagochka.maze.generator.*;
import my.tamagochka.maze.generator.Dimension;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Level implements Renderable, Collisional {

    private int[][] tileMap;
    private Map<TileType, Tile> tiles;
    private int startPositionX, startPositionY;

    protected Level(int width, int height,
                    int startX, int startY,
                    int finishX, int finishY,
                    AtlasManager atlasManager, float scaleSize) {
        tiles = new HashMap<>();
        tiles.put(TileType.GRASS, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "GRASS"), scaleSize), TileType.GRASS));
        tiles.put(TileType.VERTICAL_UP_TERMINATED_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "VERTICAL_UP_TERMINATED_WALL"), scaleSize), TileType.VERTICAL_UP_TERMINATED_WALL));
        tiles.put(TileType.VERTICAL_MIDDLE_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "VERTICAL_MIDDLE_WALL"), scaleSize), TileType.VERTICAL_MIDDLE_WALL));
        tiles.put(TileType.VERTICAL_DOWN_TERMINATED_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "VERTICAL_DOWN_TERMINATED_WALL"), scaleSize), TileType.VERTICAL_DOWN_TERMINATED_WALL));
        tiles.put(TileType.HORIZONTAL_LEFT_TERMINATED_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "HORIZONTAL_LEFT_TERMINATED_WALL"), scaleSize), TileType.HORIZONTAL_LEFT_TERMINATED_WALL));
        tiles.put(TileType.HORIZONTAL_MIDDLE_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "HORIZONTAL_MIDDLE_WALL"), scaleSize), TileType.HORIZONTAL_MIDDLE_WALL));
        tiles.put(TileType.HORIZONTAL_RIGHT_TERMINATED_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "HORIZONTAL_RIGHT_TERMINATED_WALL"), scaleSize), TileType.HORIZONTAL_RIGHT_TERMINATED_WALL));
        tiles.put(TileType.LEFT_TOP_CORNER_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "LEFT_TOP_CORNER_WALL"), scaleSize), TileType.LEFT_TOP_CORNER_WALL));
        tiles.put(TileType.RIGHT_TOP_CORNER_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "RIGHT_TOP_CORNER_WALL"), scaleSize), TileType.RIGHT_TOP_CORNER_WALL));
        tiles.put(TileType.LEFT_BOTTOM_CORNER_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "LEFT_BOTTOM_CORNER_WALL"), scaleSize), TileType.LEFT_BOTTOM_CORNER_WALL));
        tiles.put(TileType.RIGHT_BOTTOM_CORNER_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "RIGHT_BOTTOM_CORNER_WALL"), scaleSize), TileType.RIGHT_BOTTOM_CORNER_WALL));
        tiles.put(TileType.CROSS_WALLS, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "CROSS_WALLS"), scaleSize), TileType.CROSS_WALLS));
        tiles.put(TileType.VERTICAL_LEFT_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "VERTICAL_LEFT_WALL"), scaleSize), TileType.VERTICAL_LEFT_WALL));
        tiles.put(TileType.VERTICAL_RIGHT_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "VERTICAL_RIGHT_WALL"), scaleSize), TileType.VERTICAL_RIGHT_WALL));
        tiles.put(TileType.HORIZONTAL_DOWN_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "HORIZONTAL_DOWN_WALL"), scaleSize), TileType.HORIZONTAL_DOWN_WALL));
        tiles.put(TileType.HORIZONTAL_UP_WALL, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "HORIZONTAL_UP_WALL"), scaleSize), TileType.HORIZONTAL_UP_WALL));
        tiles.put(TileType.CLOSED_DOOR, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "CLOSED_DOOR"), scaleSize), TileType.CLOSED_DOOR));
        tiles.put(TileType.OPENED_DOOR, new Tile(new Sprite(atlasManager.getSpriteSheet("TILE", "OPENED_DOOR"), scaleSize), TileType.OPENED_DOOR));

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
                        tileMap[j][i] = TileType.GRASS.getNum();
/*
                        if(maze.getPoint(new Dimension(i, j)) == 0) {
                            if(maze.getPoint(new Dimension(i + 1, j)) == 1)
                                tileMap[j][i] = TileType.EMPTY_SHR.getNum();
                            if(maze.getPoint(new Dimension(i, j + 1)) == 1)
                                tileMap[j][i] = TileType.EMPTY_SHD.getNum();
                            if(maze.getPoint(new Dimension(i + 1, j)) == 1 &&
                                    maze.getPoint(new Dimension(i, j + 1)) == 1)
                                tileMap[j][i] = TileType.EMPTY_SHDR.getNum();
                        }
*/
                        break;
                    case 1: // x, y
                        byte dir = 0;
                        Dimension position = new Dimension(i, j);
                        if(i - 1 >= 0 && maze.getPoint(position.subHorizontal(1)) == 1)
                            dir |= 0b00000010;
                        if(i + 1 < maze.getSize().horizontal() && maze.getPoint(position.addHorizontal(1)) == 1)
                            dir |= 0b00000001;
                        if(j - 1 >= 0 && maze.getPoint(position.subVertical(1)) == 1)
                            dir |= 0b00001000;
                        if(j + 1 < maze.getSize().vertical() && maze.getPoint(position.addVertical(1)) == 1)
                            dir |= 0b00000100;
                        switch(dir) {
                            case 0b0001:
                                tileMap[j][i] = TileType.HORIZONTAL_LEFT_TERMINATED_WALL.getNum();
                                break;
                            case 0b0010:
                                tileMap[j][i] = TileType.HORIZONTAL_RIGHT_TERMINATED_WALL.getNum();
                                break;
                            case 0b0011:
                                tileMap[j][i] = TileType.HORIZONTAL_MIDDLE_WALL.getNum();
                                break;
                            case 0b0100:
                                tileMap[j][i] = TileType.VERTICAL_UP_TERMINATED_WALL.getNum();
                                break;
                            case 0b0101:
                                tileMap[j][i] = TileType.LEFT_TOP_CORNER_WALL.getNum();
                                break;
                            case 0b0110:
                                tileMap[j][i] = TileType.RIGHT_TOP_CORNER_WALL.getNum();
                                break;
                            case 0b0111:
                                tileMap[j][i] = TileType.HORIZONTAL_DOWN_WALL.getNum();
                                break;
                            case 0b1000:
                                tileMap[j][i] = TileType.VERTICAL_DOWN_TERMINATED_WALL.getNum();
                                break;
                            case 0b1001:
                                tileMap[j][i] = TileType.LEFT_BOTTOM_CORNER_WALL.getNum();
                                break;
                            case 0b1010:
                                tileMap[j][i] = TileType.RIGHT_BOTTOM_CORNER_WALL.getNum();
                                break;
                            case 0b1011:
                                tileMap[j][i] = TileType.HORIZONTAL_UP_WALL.getNum();
                                break;
                            case 0b1100:
                                tileMap[j][i] = TileType.VERTICAL_MIDDLE_WALL.getNum();
                                break;
                            case 0b1101:
                                tileMap[j][i] = TileType.VERTICAL_RIGHT_WALL.getNum();
                                break;
                            case 0b1110:
                                tileMap[j][i] = TileType.VERTICAL_LEFT_WALL.getNum();
                                break;
                            case 0b1111:
                                tileMap[j][i] = TileType.CROSS_WALLS.getNum();
                                break;
                        }




//                        tileMap[j][i] = TileType.WALL.getNum();
                        break;
                    case 5: // start point
                        tileMap[j][i] = TileType.CLOSED_DOOR.getNum();
                        startPositionX = i * tiles.get(TileType.CLOSED_DOOR).getWidth();
                        startPositionY = j * tiles.get(TileType.CLOSED_DOOR).getWidth();
                        break;
                    case 7: // end point
                        tileMap[j][i] = TileType.OPENED_DOOR.getNum();

                        break;
                }
            }
        }

    }

    public int getStartPositionX() {
        return startPositionX;
    }

    public int getStartPositionY() {
        return startPositionY;
    }

    public TileType getTileType(int x, int y) {
        return TileType.fromNum(tileMap[y / tiles.get(TileType.GRASS).getHeight()][x / tiles.get(TileType.GRASS).getWidth()]);
    }

    public void render(Graphics2D g, Camera camera) {
        for(int i = 0; i < tileMap.length; i++) {
            for(int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.GRASS);
                tile.render(g,
                        (int)(j * tile.getWidth()) - camera.getCameraPosX(),
                        (int)(i * tile.getHeight()) - camera.getCameraPosY(),
                        0);
                tile = tiles.get(TileType.fromNum(tileMap[i][j]));
                tile.render(g,
                        (int)(j * tile.getWidth()) - camera.getCameraPosX(),
                        (int)(i * tile.getHeight()) - camera.getCameraPosY(),
                        0);
            }
        }
    }

    @Override
    public boolean checkCollision(int x, int y) {
        if(getTileType(x, y) == TileType.GRASS ||
                getTileType(x, y) == TileType.CLOSED_DOOR ||
                getTileType(x, y) == TileType.OPENED_DOOR)
            return false; // not collision
        else
            return true; // collision detected
    }
}
