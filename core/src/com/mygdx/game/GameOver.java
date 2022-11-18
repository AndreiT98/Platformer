package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOver implements Screen {

    private final int scoreTextx = Gdx.graphics.getWidth() / 2 - 45;
    private final int scoreTexty = Gdx.graphics.getHeight() / 2 + 300;
    private final int buttonWidth = 100;
    private final int buttonHeight = 100;
    private final int exitX = Gdx.graphics.getWidth() / 2 - 50;
    private final int exitY = Gdx.graphics.getHeight() / 2 - 200;
    private final int restartX = Gdx.graphics.getWidth() / 2 - 50;
    private final int restartY = Gdx.graphics.getHeight() / 2;


    private Platformer game;
    private MainGame maingame;
    private Stage gameOver;
    private Skin buttonSkin;
    private TextButton exit;
    private TextButton restart;


    public GameOver(Platformer game, MainGame maingame) {
        this.game = game;
        this.maingame = maingame;
        init();
        handleButtons();

    }

    public void init() {
        gameOver = new Stage();
        buttonSkin = new Skin(Gdx.files.internal("uiskin.json"));
        restart = new TextButton("Restart", buttonSkin);
        exit = new TextButton("Exit", buttonSkin);

    }

    public void handleButtons() {
        restart.setBounds(restartX, restartY, buttonWidth, buttonHeight);
        exit.setBounds(exitX, exitY, buttonWidth, buttonHeight);
        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGame(game));
            }

        });
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        gameOver.addActor(restart);
        gameOver.addActor(exit);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(135 / 255f, 206 / 255f, 235 / 255f, 1);
        game.batch.begin();
        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch, "Final Score: " + maingame.getObstacle().getScore(), scoreTextx, scoreTexty);
        gameOver.draw();
        game.batch.end();


    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
        buttonSkin.dispose();


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameOver);

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

}

