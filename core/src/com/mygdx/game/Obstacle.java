package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;


public class Obstacle {

    private MainGame game;
    private Texture obstacleImage;
    private Rectangle obst;
    private float min;
    private float max;
    private int score;
    private long lastSpawnTime;

    public ArrayList<Rectangle> getObstacles() {
        return obstacles;
    }

    private ArrayList<Rectangle> obstacles = new ArrayList<>();


    public Obstacle(MainGame game) {
        this.game = game;
        this.obstacleImage = new Texture(Gdx.files.internal("tile_0032.png"));
        initObstacle();

    }

    public void initObstacle() {
        obst = new Rectangle();
        obst.x = 1150;
        obst.y = game.getTileHeight();
        obst.width = 50;
        obst.height = 100;
        obstacles.add(obst);
        min = obst.x + 200;
        max = min + 500;
        lastSpawnTime = TimeUtils.nanoTime();

    }

    public void manageObstacles() {
        float range = max - min + 1;
        float rand = (float) (Math.random() * range) + min;

        obstacles.add(new Rectangle(rand, game.getTileHeight(), 50, 100));
        min = rand + 250;
        max = min + 200;
        lastSpawnTime = TimeUtils.nanoTime();

        if (obstacles.get(0).x < (game.getCamera().position.x - 600)) {
            obstacles.remove(0);
            score++;

        }
    }

    public void checkCollision() {
        if (obstacles.get(0).overlaps(game.getPlayer().getPlayer())) {
            game.setState("Game Over");
        }
    }

    public int getScore() {
        return score;
    }
    public long getLastSpawnTime() {
        return lastSpawnTime;
    }

    public Texture getObstacleImage() {
        return obstacleImage;
    }

}
