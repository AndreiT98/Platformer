package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Character {
    private final int speed;


    private MainGame game;
    Texture charImage;
    private int xPos;
    private int yPos;
    private int height;
    private int width;


    public Character(int speed, MainGame game){
        this.speed = speed;
        this.game = game;
        charImage = new Texture(Gdx.files.internal("character_0006.png"));
        initPlayer();

    }


    public void initPlayer(){
        this.xPos = 50;
        this.yPos = game.getTileHeight();
        this.height = 70;
        this.width = 50;

    }

    public void movePlayer(){
        this.xPos += 4;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Texture getCharImage() {
        return charImage;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
