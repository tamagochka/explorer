package my.tamagochka.game.level;

public enum TileType {

    GRASS(0),
    VERTICAL_UP_TERMINATED_WALL(10),
    VERTICAL_MIDDLE_WALL(11),
    VERTICAL_DOWN_TERMINATED_WALL(12),
    HORIZONTAL_LEFT_TERMINATED_WALL(13),
    HORIZONTAL_MIDDLE_WALL(14),
    HORIZONTAL_RIGHT_TERMINATED_WALL(15),
    LEFT_TOP_CORNER_WALL(16),
    RIGHT_TOP_CORNER_WALL(17),
    LEFT_BOTTOM_CORNER_WALL(18),
    RIGHT_BOTTOM_CORNER_WALL(19),
    CROSS_WALLS(20),
    VERTICAL_LEFT_WALL(21),
    VERTICAL_RIGHT_WALL(22),
    HORIZONTAL_DOWN_WALL(23),
    HORIZONTAL_UP_WALL(24),
    CLOSED_DOOR(50),
    OPENED_DOOR(51);

    private int n;

    TileType(int n) {
        this.n = n;
    }

    public int getNum() {
        return n;
    }

    public static TileType fromNum(int n) {
        for(TileType t : TileType.values()) {
            if(t.getNum() == n) return t;
        }
        return null;
    }


}
