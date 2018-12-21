package my.tamagochka.game.entities;

public enum EntityType {

    PLAYER(0),
    EMPTY(1),
    WALL(2);

    private int number;

    EntityType(int number) {
        this.number = number;
    }

}
