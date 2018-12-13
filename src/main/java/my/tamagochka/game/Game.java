package my.tamagochka.game;

import my.tamagochka.display.Display;
import my.tamagochka.game.entities.*;
import my.tamagochka.graphics.textureAtlas.AtlasManager;
import my.tamagochka.utilities.ResourceLoader;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game implements Runnable {

    private int WIDTH = 800;
    private int HEIGHT = 600;
    private float SCALE_SIZE = 5f;
    private int CLEAR_COLOR = 0xFF000000;
    private int COUNT_BUFFERS = 3;

    private final String TITLE = "Explorer";

    private final float UPDATE_RATE = 60.0f;
    private final float UPDATE_INTERVAL = TimeUnit.SECONDS.toNanos(1) / UPDATE_RATE;
    private final long IDLE_TIME = 1;

    private final String ATLAS_FILENAME = "player_green_spritesheet.xml";

    private Thread gameThread;
    private boolean running = false;

    private ArrayList<Entity> entities;

    private Graphics2D graphics;

    public Game() {
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, COUNT_BUFFERS);
        Display.addWindowClosingListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
                super.windowClosing(e);
            }
        });
        graphics = Display.getGraphics();

        entities = new ArrayList<>();

        ResourceLoader loader = new ResourceLoader("resources/");

        AtlasManager atlasManager = new AtlasManager();
        atlasManager.addAtlas(loader, loader, ATLAS_FILENAME);
        EntityFactory factory = new EntityFactory(atlasManager, SCALE_SIZE);
        Player player = (Player) factory.build(EntityType.PLAYER, 100, 100, DirectionMoving.SOUTH, 0);

        entities.add(player);






    }

    public synchronized void start() {
        if(running) return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();

    }

    public synchronized void stop() {
        if(!running) return;
        running = false;
        try {
            gameThread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        clean();
    }

    private void clean() {
        // free all resources
        Display.destroy();
    }

    private void render() {
        Display.clear();

        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).render(graphics);
        }


        Display.swapBuffers();
    }

    private void update() { // update state game objects

    }

    public void run() {
        int fps = 0; // calls render() method
        int upd = 0; // count update() method calls
        int updl = 0; // count update() method calls without render frame
        long count = 0; // counter for update window title information
        float delta = 0; // how match elapsed update intervals
        long lastTime = System.nanoTime();

        while(running) {
            long nowTime = System.nanoTime();
            long elapsedTime = nowTime - lastTime;
            lastTime = nowTime;
            count += elapsedTime;
            boolean render = false;
            delta += elapsedTime / UPDATE_INTERVAL;

            while(delta > 1) {
                update();
                upd++;
                delta--;
                if(render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if(render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(count >= TimeUnit.SECONDS.toNanos(1)) {
                Display.setTitle(TITLE + " || fps: " + fps + " || upd: " + upd + " || updl: " + updl);
                upd = 0;
                updl = 0;
                fps = 0;
                count = 0;
            }
        }
    }
}
