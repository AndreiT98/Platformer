package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Character {

    private final float speed;
    private final int playerWidth = 50;
    private final int playerHeight = 70;
    private MainGame game;
    private Texture charImage;
    private Rectangle player;
    private String state = "Grounded";
    private float jumpHeight = 20;

    public Character(int speed, MainGame game) {
        this.speed = speed;
        this.game = game;
        charImage = new Texture(Gdx.files.internal("charSkin.png"));
        player = new Rectangle(50, game.getTileHeight(), playerWidth, playerHeight);

    }

    //Method checks the State of the Player and handles the walking/jumping animation based on it's state
    public void checkState() {
        switch (state) {
            case "Grounded":
                player.x += speed * Gdx.graphics.getDeltaTime();
                break;

            case "Ascending":
                if (jumpHeight == 0) {
                    state = "Descending";
                    player.x += speed * Gdx.graphics.getDeltaTime();
                    break;
                }
                player.x += speed * Gdx.graphics.getDeltaTime();
                player.y += jumpHeight;
                jumpHeight -= 1;

                break;

            case "Descending":
                if (jumpHeight == 20) {
                    state = "Grounded";
                    player.x += speed * Gdx.graphics.getDeltaTime();
                    player.y -= jumpHeight;
                    break;
                }
                player.x += speed * Gdx.graphics.getDeltaTime();
                player.y -= jumpHeight;
                jumpHeight += 1;

                break;
        }
    }

    public float getSpeed() {
        return speed;
    }

    public Rectangle getPlayer() {
        return player;
    }

    public Texture getCharImage() {
        return charImage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
