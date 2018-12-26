package my.tamagochka.game.UI;

import my.tamagochka.graphics.sprites.Sprite;

import java.awt.*;
import java.util.*;

public class GameTimer {

    private int left, top;
    private Sprite digits;
    private Timer timer;
    private Date time;
    private Date elapsedTime;
    private boolean running = false;

    public GameTimer(int left, int top, Sprite digits) {
        this.left = left;
        this.top = top;
        this.digits = digits;
        elapsedTime = new Date(0);
/*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 45);
        elapsedTime = calendar.getTime();
*/
        time = new Date();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Date newTime = new Date();
                if(running) {
                    long diff = newTime.getTime() - time.getTime();
                    elapsedTime.setTime(elapsedTime.getTime() + diff);
                }
                time = newTime;
            }
        }, 0, 100);
    }

    public Date getTime() {
        return elapsedTime;
    }

    public void startTimer() {
        running = true;
    }

    public void pauseTimer() {
        running = false;
    }

    public void stopTimer() {
        timer.cancel();
    }

    public void render(Graphics2D g) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setTime(elapsedTime);
        String time_str = String.format("%03d:%02d.%d", calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND) / 100);
        for(int i = 0; i < time_str.length(); i++) {
            if(time_str.charAt(i) == ':') {
                digits.render(g, left + i * digits.getScaledWidth(), top, 10);
            } else if(time_str.charAt(i) == '.') {
                digits.render(g, left + i * digits.getScaledWidth(), top, 11);
            } else {
                digits.render(g, left + i * digits.getScaledWidth(), top, Character.getNumericValue(time_str.charAt(i)));
            }
        }
    }

}
