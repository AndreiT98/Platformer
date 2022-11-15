package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {

    public Platformer platformer;
    public Stage stage = new Stage();
    public Table table;
    TextButton newGame;
    TextButton exit;


    public MainMenu(Platformer platformer) {
        this.platformer = platformer;
        init();

    }

    public void init() {
        table = new Table();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        newGame = new TextButton("New Game", skin);
        newGame.setBounds(1200 / 2 - 100, 400, 200, 100);
        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                platformer.setScreen(new MainGame(platformer));
                Gdx.input.setInputProcessor(null);
            }
        });

        exit = new TextButton("Exit", skin);
        exit.setBounds(1200 / 2 - 100, 200, 200, 100);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(newGame);
        stage.addActor(exit);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 1, 1);
        platformer.batch.begin();
        platformer.font.draw(platformer.batch, "WELCOME!", 1200 / 2 - 50, 800 - 200);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        platformer.batch.end();

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
        stage.dispose();

    }
}
