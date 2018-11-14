package my.tamagochka.display;

import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Display {

    private static Display instance;

    private Frame window;
    private Canvas content;
    private BufferedImage buffer;
    private int[] bufferData;
    private Graphics bufferGraphics;
    private BufferStrategy bufferStrategy;

    private int width;
    private int height;
    private String title;
    private int clearColor;
    private int countBuffers;

    public static void create(int width, int height, String title, int clearColor, int countBuffers) {
        if(instance != null) return;
        instance = new Display(width, height, title, clearColor, countBuffers);
    }

    private Display(int width, int height, String title, int clearColor, int countBuffers) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.clearColor = clearColor;
        this.countBuffers = countBuffers;

        content = new Canvas();
        content.setPreferredSize(new Dimension(width, height));

        window = new Frame(title);
        window.setResizable(false);
        window.add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        content.createBufferStrategy(countBuffers);
        bufferStrategy = content.getBufferStrategy();
    }

    public static void clear() {
        Arrays.fill(instance.bufferData, instance.clearColor);
    }

    public static int getColor(int x, int y) {
        return instance.buffer.getRGB(x, y);
    }

    public static void swapBuffers() {
        Graphics g = instance.bufferStrategy.getDrawGraphics();
        g.drawImage(instance.buffer, 0, 0, null);
        instance.bufferStrategy.show();
    }

    public static Graphics2D getGraphics() {
        return (Graphics2D)instance.bufferGraphics;
    }

    public static void destroy() {
        if(instance != null) instance.window.dispose();
    }

    public static void setTitle(String title) {
        instance.window.setTitle(title);
    }

    public static void addWindowClosingListener(WindowListener closingListener) {
        instance.window.addWindowListener(closingListener);
    }

    // TODO
/*
    public static void addInputListener(Input inputListener) {
        instance.window.add(inputListener);
    }
*/


}
