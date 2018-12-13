package my.tamagochka.IO;

import my.tamagochka.game.entities.Action;
import my.tamagochka.game.entities.Actions;

import java.awt.event.KeyEvent;

public class PerformAction extends Input implements Action {

    @Override
    public Actions getAction() {
        if(super.getKey(KeyEvent.VK_UP)) {
            return Actions.MOVE_UP;
        } else if(super.getKey(KeyEvent.VK_DOWN)) {
            return Actions.MOVE_DOWN;
        } else if(super.getKey(KeyEvent.VK_LEFT)) {
            return Actions.MOVE_LEFT;
        } else if(super.getKey(KeyEvent.VK_RIGHT)) {
            return Actions.MOVE_RIGHT;
        }
        return Actions.STAND_STILL;
    }
}
