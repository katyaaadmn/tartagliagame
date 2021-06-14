package com.mylibgdxgame.tartagliagame.objects;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Web extends Moving{

    private Random r;

    private Rectangle enemy, webUp, webDown;

    public static final int GAP = 45;
    private boolean isScored = false;

    private float groundY;

    public Web(float x, float y, int width, int height, float movSpeed, float groundY) {
        super(x, y, width, height, movSpeed);
        r = new Random();
        enemy = new Rectangle();
        webUp = new Rectangle();
        webDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        webUp.set(position.x, position.y, width, height);
        webDown.set(position.x, position.y + height + GAP, width, groundY - (position.y + height + GAP));
        enemy.set(position.x - (24 - width) / 2, position.y + height - 11, 24, 11);
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        height = r.nextInt(90) + 15;
        isScored = false;
    }

    public boolean collides(Tartaglia tartaglia) {
        if (position.x < tartaglia.getX() + tartaglia.getWidth()) {
            return (Intersector.overlaps(tartaglia.getCircle(), webUp)
                    || Intersector.overlaps(tartaglia.getCircle(), webDown)
                    || Intersector.overlaps(tartaglia.getCircle(), enemy));
        }
        return  false;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean b) {
        isScored = b;
    }

    public void onRestart(float x, float movSpeed) {
        velocity.x = movSpeed;
        reset(x);
    }

}
