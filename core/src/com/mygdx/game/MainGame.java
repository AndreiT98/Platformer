package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class MainGame implements Screen {

    private final int screenWidth = Gdx.graphics.getWidth();
    private final int screenHeight = Gdx.graphics.getHeight();
    private final int tileY = 0;

    private Platformer platformer;
    private Texture tileImage;

    public OrthographicCamera getCamera() {
        return camera;
    }

    private OrthographicCamera camera;

    public Character getPlayer() {
        return player;
    }

    private Character player;
    private Obstacle obstacle;

    private int tileWidth;
    private int tileHeight;
    private BitmapFont font = new BitmapFont();


    public ArrayList<Rectangle> getTilesArray() {
        return tilesArray;
    }

    private ArrayList<Rectangle> tilesArray = new ArrayList<>();


    public MainGame(Platformer game) {
        this.platformer = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        initTile();
        player = new Character(350, this);
        obstacle = new Obstacle(this);
        font.getData().setScale(1,1.5F);


    }

    public void initTile() {
        tileImage = new Texture(Gdx.files.internal("tile_0000.png"));
        tileWidth = 100;
        tileHeight = 100;
        fillTiles();

    }

    public void fillTiles() {
        int tmp = screenWidth / tileWidth;
        for (int i = 0; i <= tmp; i++) {
            tilesArray.add(i, new Rectangle(i * tileWidth, tileY, tileWidth, tileHeight));

        }
    }

    public void updateTiles() {
        if ((player.getPlayer().x - (tilesArray.get(0).x + getTileWidth())) > 50) {
            tilesArray.remove(0);
            tilesArray.add(new Rectangle(tilesArray.get(tilesArray.size() - 1).x + tileWidth, tileY, tileWidth, tileHeight));
        }
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(135 / 255f, 206 / 255f, 235 / 255f, 1);
        camera.position.x += player.getSpeed() * Gdx.graphics.getDeltaTime();
        camera.update();
        platformer.batch.setProjectionMatrix(camera.combined);
        updateTiles();
        if(TimeUtils.nanoTime() - obstacle.getLastSpawnTime() > 400000000 ) obstacle.manageObstacles();


        platformer.batch.begin();
        for (Rectangle tmp : tilesArray) {
            platformer.batch.draw(tileImage, tmp.x, tmp.y, tmp.height, tmp.width);
        }
        platformer.batch.draw(player.getCharImage(), player.getPlayer().x, player.getPlayer().y, player.getPlayer().width, player.getPlayer().height);
        for(Rectangle tmp: obstacle.getObstacles()){
            platformer.batch.draw(obstacle.getObstacleImage(), tmp.x,tmp.y,tmp.width,tmp.height);
        }
        font.draw(platformer.batch, "Score: " + obstacle.getScore(), camera.position.x+500, Gdx.graphics.getHeight()-25);

        platformer.batch.end();

        player.checkState();
        obstacle.checkCollision();
        System.out.println(Gdx.graphics.getFramesPerSecond());

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.getState() == "Grounded")
            player.setState("Ascending");

    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    @Override
    public void dispose() {
        tileImage.dispose();
        player.charImage.dispose();

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

    }


}


