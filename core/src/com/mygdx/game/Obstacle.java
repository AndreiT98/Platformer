package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;


public class Obstacle {

    private final int obstacleWidth = 50;
    private final int obstacleHeight = 100;
    private final int minDistance = 250;
    private final int maxDistance = 200;

    private MainGame game;
    private Texture obstacleImage;
    private Rectangle obst;
    private float min;
    private float max;
    private float random;
    private float range;
    private int score;
    private long lastSpawnTime;
    private ArrayList<Rectangle> obstacles = new ArrayList<>();


    public Obstacle(MainGame game) {
        this.game = game;
        this.obstacleImage = new Texture(Gdx.files.internal("obstacleSkin.png"));
        initObstacle();

    }

    // Method initializes the first Obstacle on the right of the Screen and sets the variables with values
    // that allow the following Obstacles to spawn from outside of the Camera view
    public void initObstacle() {
        obst = new Rectangle();
        obst.x = 1150;
        obst.y = game.getTileHeight();
        obst.width = obstacleWidth;
        obst.height = obstacleHeight;
        obstacles.add(obst);
        min = obst.x + 200;
        max = min + 500;
        lastSpawnTime = TimeUtils.nanoTime();

    }

    // Method creates a new Obstacle at a random Position in the x-Direction of the Camera after a specific time elapsed
    // Also removes obstacles that are not visibile on the Screen anymore and increases the score when it removes it.
    public void manageObstacles() {
        range = max - min + 1;
        random = (float) (Math.random() * range) + min;

        obstacles.add(new Rectangle(random, game.getTileHeight(), obstacleWidth, obstacleHeight));
        min = random + minDistance;
        max = min + maxDistance;
        lastSpawnTime = TimeUtils.nanoTime();

        if (obstacles.get(0).x < (game.getCamera().position.x - 600)) {
            obstacles.remove(0);
            score++;

        }
    }

    // Method checks if player collided with an Obstacle and sets the GameState to GameOver if true
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

    public ArrayList<Rectangle> getObstacles() {
        return obstacles;
    }

    public Texture getObstacleImage() {
        return obstacleImage;
    }

}
