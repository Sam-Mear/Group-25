package com.group25.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Creating a spawner (point) where monsters will re-appear ones killed or some threshold is crossed
 */
public class EnemySpawner extends GameEntity {
    private EnemyType spawnType;    //Type of monster which will be spawned
    private int spawnLimit = 10;     //We want to only spawn enemies when the limit is reached
    private ArrayList<Enemy> enemies;

    public EnemySpawner(float positionX, float positionY, int width, int height, Sprite img, EnemyFactory factory) {
        super(positionX, positionY, width, height, img);
        this.spawnType = spawnType;
        enemies = new ArrayList<Enemy>();
        System.out.println(((Object) spawnType).getClass().getName());
    }

    public EnemySpawner(float positionX, float positionY, int width, int height, Sprite img, Rectangle hitbox,EnemyFactory factory) {
        super(positionX, positionY, width, height, img, hitbox);
        this.spawnType = spawnType;
        enemies = new ArrayList<Enemy>();
        System.out.println(((Object) spawnType).getClass().getName());
    }

    /**
     * Generate an enemy of a given type, the type was set/provided in the constructor
     */

}
