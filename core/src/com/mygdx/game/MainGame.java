package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class MainGame implements Screen {

    private final int screenWidth = Gdx.graphics.getWidth();
    private final int screenHeight = Gdx.graphics.getHeight();
    private final int tileY = 0;

    private Platformer platformer;
    private Texture tileImage;
    private Texture characterImage;
    private OrthographicCamera camera;
    private Character player;


    private int tileWidth;
    private int tileHeight;
    private ArrayList<Rectangle> tilesArray = new ArrayList<>();


    public MainGame(Platformer game) {
        this.platformer = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        initTile();
        player = new Character(100, this);


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
        if ((player.getxPos() - (tilesArray.get(0).x + getTileWidth())) > 50) {
            tilesArray.remove(0);
            tilesArray.add(new Rectangle(tilesArray.get(tilesArray.size() - 1).x + tileWidth, tileY, tileWidth, tileHeight));
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(135 / 255f, 206 / 255f, 235 / 255f, 1);
        camera.position.x += player.getSpeed() * Gdx.graphics.getDeltaTime();
        camera.update();
        platformer.batch.setProjectionMatrix(camera.combined);
        updateTiles();
        platformer.batch.begin();
        for (Rectangle tmp : tilesArray) {
            platformer.batch.draw(tileImage, tmp.x, tmp.y, tmp.height, tmp.width);
        }
        platformer.batch.draw(player.getCharImage(), player.getxPos(), player.getyPos(), player.getWidth(), player.getHeight());

        platformer.batch.end();

        player.checkState();
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.getState() == "Grounded")
            player.setState("Ascending");

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

    @Override
    public void dispose() {
        tileImage.dispose();
        characterImage.dispose();
        player.charImage.dispose();

    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
