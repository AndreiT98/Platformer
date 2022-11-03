package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Character {
    public float getSpeed() {
        return speed;
    }

    private final float speed;


    private MainGame game;
    Texture charImage;

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    private float xPos;

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    private float yPos;
    private float height;
    private float width;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    private String state = "Grounded";
    private float jumpHeight = 25;


    public Character(int speed, MainGame game) {
        this.speed = speed;
        this.game = game;
        charImage = new Texture(Gdx.files.internal("character_0006.png"));
        initPlayer();

    }


    public void initPlayer() {
        this.xPos = 50;
        this.yPos = game.getTileHeight();
        this.height = 70;
        this.width = 50;

    }

    public void movePlayer() {
        xPos += 4;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public Texture getCharImage() {
        return charImage;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }


    public void checkState() {
        switch (state) {
            case "Grounded":
                this.xPos += speed * Gdx.graphics.getDeltaTime();
                break;

            case "Ascending":
                if (jumpHeight == 0) {
                    state = "Descending";
                    this.xPos += speed * Gdx.graphics.getDeltaTime();
                    System.out.println(jumpHeight);
                    break;
                }
                this.xPos += speed * Gdx.graphics.getDeltaTime();
                this.yPos += jumpHeight;
                jumpHeight -= 1;

                break;

            case "Descending":
                if (jumpHeight == 25) {
                    state = "Grounded";
                    this.xPos += speed * Gdx.graphics.getDeltaTime();
                    this.yPos -= jumpHeight;
                    System.out.println(jumpHeight);
                    break;
                }
                this.xPos += speed * Gdx.graphics.getDeltaTime();
                this.yPos -= jumpHeight;
                jumpHeight += 1;

                break;


        }


    }
}
