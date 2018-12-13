package my.tamagochka.IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean[] map;

    public Input() {
        map = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        map[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        map[e.getKeyCode()] = false;
    }

    public boolean getKey(int keyCode) {
        return map[keyCode];
    }
}
