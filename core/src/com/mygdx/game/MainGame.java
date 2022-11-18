package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;


public class MainGame implements Screen {

    private final int screenWidth = Gdx.graphics.getWidth();
    private final int screenHeight = Gdx.graphics.getHeight();
    private final int tileY = 0;
    private ArrayList<Rectangle> tilesArray = new ArrayList<>();
    private final int tileWidth = 100;
    private final int tileHeight = 100;

    private Platformer platformer;
    private Texture tileImage;
    private OrthographicCamera camera;
    private Character player;
    private Obstacle obstacle;
    private String state;


    //Initializes all needed Objects
    public MainGame(Platformer game) {
        this.platformer = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        tileImage = new Texture(Gdx.files.internal("tileSkin.png"));
        fillTiles();
        player = new Character(350, this);
        obstacle = new Obstacle(this);
        platformer.font.getData().setScale(1, 1.5F);
        setState("Playing");

    }

    //Method fills the whole ground with Tiles
    public void fillTiles() {
        int tmp = screenWidth / tileWidth;
        for (int i = 0; i <= tmp; i++) {
            tilesArray.add(i, new Rectangle(i * tileWidth, tileY, tileWidth, tileHeight));

        }
    }

    //Method removes Tiles when they exit the Screen to save memory + adds new ones so there is a neverending ground
    public void updateTiles() {
        if ((player.getPlayer().x - (tilesArray.get(0).x + getTileWidth())) > 50) {
            tilesArray.remove(0);
            tilesArray.add(new Rectangle(tilesArray.get(tilesArray.size() - 1).x + tileWidth, tileY, tileWidth, tileHeight));
        }
    }

    public void checkResume() {
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            platformer.font.setColor(Color.WHITE);
            setState("Playing");
        }
    }


    // Method contains all Information needed to draw every Object in the render Method
    public void batchDrawing() {
        platformer.batch.begin();
        for (Rectangle tmp : tilesArray) {
            platformer.batch.draw(tileImage, tmp.x, tmp.y, tmp.height, tmp.width);
        }
        platformer.batch.draw(player.getCharImage(), player.getPlayer().x, player.getPlayer().y, player.getPlayer().width, player.getPlayer().height);
        for (Rectangle tmp : obstacle.getObstacles()) {
            platformer.batch.draw(obstacle.getObstacleImage(), tmp.x, tmp.y, tmp.width, tmp.height);
        }
        platformer.batch.end();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(135 / 255f, 206 / 255f, 235 / 255f, 1);
        camera.update();
        platformer.batch.setProjectionMatrix(camera.combined);
        updateTiles();
        batchDrawing();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            setState("Pause");

        switch (state) {
            case "Playing":
                platformer.batch.begin();
                platformer.font.draw(platformer.batch, "Score: " + obstacle.getScore(), camera.position.x + 500, camera.position.y + 375);
                System.out.println(camera.position.y);
                platformer.batch.end();
                camera.position.x += player.getSpeed() * Gdx.graphics.getDeltaTime();
                if (TimeUtils.nanoTime() - obstacle.getLastSpawnTime() > 500000000) obstacle.manageObstacles();
                player.checkState();
                obstacle.checkCollision();
                if (Gdx.input.isKeyPressed(Input.Keys.UP) && "Grounded".equals(player.getState()))
                    player.setState("Ascending");
                break;


            case "Game Over":
                platformer.setScreen(new GameOver(platformer, this));
                break;

            case "Pause":
                platformer.batch.begin();
                platformer.font.setColor(Color.WHITE);
                platformer.font.draw(platformer.batch, "Score: " + obstacle.getScore(), camera.position.x + 500, camera.position.y + 375);
                platformer.font.setColor(Color.BLACK);
                platformer.font.draw(platformer.batch, "Game Paused", camera.position.x, camera.position.y);
                platformer.font.draw(platformer.batch, "Press R to Resume!", camera.position.x, camera.position.y - 50);
                platformer.batch.end();
                checkResume();

                break;
        }

    }

    //GETTERS
    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public ArrayList<Rectangle> getTilesArray() {
        return tilesArray;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Obstacle getObstacle() {return obstacle;}

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Character getPlayer() {
        return player;
    }

    @Override
    public void dispose() {
        tileImage.dispose();
        player.getCharImage().dispose();
        obstacle.getObstacleImage().dispose();

    }


    @Override
    public void show() {


    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }


}


