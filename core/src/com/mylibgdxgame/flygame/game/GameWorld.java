package com.mylibgdxgame.tartagliagame.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mylibgdxgame.tartagliagame.loader.ResourseLoader;
import com.mylibgdxgame.tartagliagame.objects.Tartaglia;
import com.mylibgdxgame.tartagliagame.objects.MovHandler;

public class GameWorld {

    private Tartaglia tartaglia;
    private MovHandler movHandler;
    private Rectangle ground;

    private int midPointY;
    private int midPointX;
    private int score = 0;
    private  float runTime = 0;

    private GameRender renderer;
    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }


    public GameWorld(int midPointY, int midPointX) {
        currentState = GameState.MENU;
        this.midPointX = midPointX;
        this.midPointY = midPointY;

        tartaglia = new Tartaglia(33, midPointY - 5, 17, 12);
        movHandler = new MovHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;

        }
    }

    private void updateReady(float delta) {
        tartaglia.updateReady(runTime);
        movHandler.updateReady(delta);
    }

    public void updateRunning(float delta){
        if (delta > 0.15f) {
            delta = 0.15f;
        }

        tartaglia.update(delta);
        movHandler.update(delta);

        if (movHandler.collides(tartaglia) && tartaglia.isAlive()) {
            movHandler.stop();
            tartaglia.die();
            tartaglia.cling();
            ResourseLoader.fall.play();
            renderer.prepareTransition(255, 255, 255, 0.3f);
            currentState = GameState.GAMEOVER;
        }
        if (Intersector.overlaps(tartaglia.getCircle(), ground)) {
            if (tartaglia.isAlive()) {
                ResourseLoader.dead.play();
                tartaglia.die();
                renderer.prepareTransition(255, 255, 255, 0.3f);
            }
            movHandler.stop();
            tartaglia.cling();
            currentState = GameState.GAMEOVER;

            if (score > ResourseLoader.getHighScore()) {
                ResourseLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }

    }

      public MovHandler getMovHandler() {
        return movHandler;
    }

    public Tartaglia getTartaglia() {
        return tartaglia;
    }

    public void setRenderer(GameRender renderer) {
        this.renderer = renderer;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public void ready() {
        currentState = GameState.READY;
        renderer.prepareTransition(0, 0, 0, 1f);
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        score = 0;
        tartaglia.onRestart(midPointY - 5);
        movHandler.onRestart();
        ready();
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public int getMidPointY() {
        return midPointY;
    }

    public int getMidPointX() {
        return midPointX;
    }
}
