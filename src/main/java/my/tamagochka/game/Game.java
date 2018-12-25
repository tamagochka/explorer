package my.tamagochka.game;

import my.tamagochka.IO.PerformAction;
import my.tamagochka.display.Display;
import my.tamagochka.game.UI.UI;
import my.tamagochka.game.camera.Camera;
import my.tamagochka.game.camera.Dummy;
import my.tamagochka.game.entities.*;
import my.tamagochka.game.level.Level;
import my.tamagochka.game.level.LevelFactory;
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
    private float SCALE_SIZE = 0.7f;
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
    private Level level;
    private UI ui;
    private Camera camera;

    private Graphics2D graphics;
    private PerformAction input;

    public Game() {
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, COUNT_BUFFERS);
        Display.addWindowClosingListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
                super.windowClosing(e);
            }
        });
        input = new PerformAction();
        Display.addListener(input);

        graphics = Display.getGraphics();

        ResourceLoader loader = new ResourceLoader("resources/");
        AtlasManager atlasManager = new AtlasManager();
        atlasManager.addAtlas(loader, loader, ATLAS_FILENAME);

        LevelFactory levelFactory = new LevelFactory(atlasManager, SCALE_SIZE);
        level = levelFactory.generate(11, 11, 1, 1, 9, 9);

        entities = new ArrayList<>();
        EntityFactory factory = new EntityFactory(atlasManager, SCALE_SIZE);

        Player player = (Player)factory.build(EntityType.PLAYER, level.getStartPositionX(), level.getStartPositionY(), DirectionMoving.SOUTH, 3, 5, 150, 150);
        player.addBarrier(level); // player collision with level objects
        entities.add(player);

        ui = new UI(atlasManager, SCALE_SIZE);

        camera = new Camera(graphics, 20, 0.1, 0, 0, 0, 0, WIDTH, HEIGHT, player);


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

        camera.render(level);

        for(int i = 0; i < entities.size(); i++) {
            camera.render(entities.get(i));
        }

        camera.render(ui);


        Display.swapBuffers();
    }

    private void update() { // update state game gameObjects

        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).update(input);
        }

        camera.update();

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
