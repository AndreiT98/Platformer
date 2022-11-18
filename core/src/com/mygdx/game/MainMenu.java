package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {

    public Platformer platformer;
    public Skin skin;
    public Stage stage;
    private TextButton newGame;
    private TextButton exit;

    // Location of the Buttons, centered to the screen while using the Window bounds created in DesktopLauncher
    private final float  newGameLocationx = Gdx.graphics.getWidth() / 2 - 100;
    private final float newGameLocationy = Gdx.graphics.getHeight()/2;
    private final float exitLocationx = Gdx.graphics.getWidth() / 2 - 100;
    private final float exitLocationy = Gdx.graphics.getHeight()/2 - 200;


    public MainMenu(Platformer platformer) {
        this.platformer = platformer;
        init();

    }

    // Method initializes Buttons/Skins/Stage used in this Screen + Adds Actionlisteners to the Buttons
    public void init() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();

        newGame = new TextButton("New Game", skin);
        newGame.setBounds(newGameLocationx, newGameLocationy, 200, 100);
        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                platformer.setScreen(new MainGame(platformer));
            }
        });

        exit = new TextButton("Exit", skin);
        exit.setBounds(exitLocationx, exitLocationy, 200, 100);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(newGame);
        stage.addActor(exit);

    }


    // Sets Input priority to this Screen when the Screen is in-use.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    // Renders everything
    @Override
    public void render(float delta) {
        ScreenUtils.clear(135 / 255f, 206 / 255f, 235 / 255f, 1);
        platformer.batch.begin();
        platformer.font.draw(platformer.batch, "WELCOME!", Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight() - 200);
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

    // Calls the dispose method so the Screen disposes itself since it won't be needed again in the future
    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();


    }
}
