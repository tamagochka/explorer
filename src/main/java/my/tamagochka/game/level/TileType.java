package my.tamagochka.game.level;

public enum TileType {

    EMPTY(0), WALL(1), EMPTY_SHD(10), EMPTY_SHR(11), EMPTY_SHDR(12),
    EMPTY_CLOSED_DOOR(13), EMPTY_OPENED_DOOR(14);

    private int n;

    TileType(int n) {
        this.n = n;
    }

    public int getNum() {
        return n;
    }

    public static TileType fromNum(int n) {
        switch(n) {
            case 0:
                return EMPTY;
            case 1:
                return WALL;
            case 10:
                return EMPTY_SHD;
            case 11:
                return EMPTY_SHR;
            case 12:
                return EMPTY_SHDR;
            case 13:
                return EMPTY_CLOSED_DOOR;
            case 14:
                return EMPTY_OPENED_DOOR;
            default:
                return EMPTY;
        }
    }


}
