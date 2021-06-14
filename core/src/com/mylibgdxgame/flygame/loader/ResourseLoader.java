package com.mylibgdxgame.tartagliagame.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ResourseLoader {

    private static TextureAtlas atlas;
    public static Sprite logo, tartagliaAndEnemies, background, grass, tartaglia1, tartaglia2, tartaglia3,
            enemy, webUp,webDown, playButtonUp, playButtonDown, ready, retry, gameOver,
            highScore, scoreboard, starOn, starOff;
    public static Animation tartagliaAnimation;
    public static Sound dead, flap, coin, fall;
    public static Music tartaglia;
    public static BitmapFont font, shadow, whiteFont;

    public static void load(){

        atlas = new TextureAtlas(Gdx.files.internal("texture/texture.pack"), true);

        logo = new Sprite(atlas.findRegion("logo"));
        logo.flip(false, true);
        playButtonUp = new Sprite(atlas.findRegion("buttonOff"));
        playButtonDown = new Sprite(atlas.findRegion("buttonOn"));
        ready = new Sprite(atlas.findRegion("tapToFly"));
        retry = new Sprite(atlas.findRegion("retry"));
        gameOver = new Sprite(atlas.findRegion("gameOver"));
        scoreboard = new Sprite(atlas.findRegion("wood"));
        starOn = new Sprite(atlas.findRegion("starOn"));
        starOff = new Sprite(atlas.findRegion("starOff"));
        highScore = new Sprite(atlas.findRegion("highScore"));
        tartagliaAndEnemies = new Sprite(atlas.findRegion("tartagliaAndSpyders"));
        background = new Sprite(atlas.findRegion("background"));
        grass = new Sprite(atlas.findRegion("grass"));
        tartaglia1 = new Sprite(atlas.findRegion("tartaglia1"));
        tartaglia2 = new Sprite(atlas.findRegion("tartaglia2"));
        tartaglia3 = new Sprite(atlas.findRegion("tartaglia3"));
        enemy = new Sprite(atlas.findRegion("enemy"));
        webUp = new Sprite(atlas.findRegion("webUp"));
        webDown = new Sprite(atlas.findRegion("webDown"));

        TextureRegion[] tartaglia = {tartaglia1, tartaglia2, tartaglia3};
        tartagliaAnimation = new Animation(0.06f, tartaglia);
        tartagliaAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        dead = Gdx.audio.newSound(Gdx.files.internal("sounds/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("sounds/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("sounds/fall.wav"));
        ResourseLoader.tartaglia = Gdx.audio.newMusic(Gdx.files.internal("sounds/tartaglia.mp3"));

        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        whiteFont = new BitmapFont(Gdx.files.internal("fonts/whitetext.fnt"));
        whiteFont.getData().setScale(.1f, -.1f);
        shadow = new BitmapFont(Gdx.files.internal("fonts/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

    }
    public static void dispose(){
        atlas.dispose();

        dead.dispose();
        flap.dispose();
        coin.dispose();
        tartaglia.dispose();

        font.dispose();
        shadow.dispose();
    }

}
