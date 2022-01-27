package com.group25.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Creating a spawner (point) where monsters will re-appear ones killed or some threshold is crossed
 */
public class MonsterSpawner extends GameEntity {
    private Enemy spawnType;    //Type of monster which will be spawned
    private int spawnLimit;     //We want to only spawn enemies when the limit is reached
    private ArrayList<Enemy> enemies;


    public MonsterSpawner(float positionX, float positionY, int width, int height, Sprite img,Enemy spawnType) {
        super(positionX, positionY, width, height, img);
        this.spawnType = spawnType;
        enemies = new ArrayList<Enemy>();
        System.out.println(((Object) spawnType).getClass().getName());
    }

    public MonsterSpawner(float positionX, float positionY, int width, int height, Sprite img, Rectangle hitbox,Enemy spawnType) {
        super(positionX, positionY, width, height, img, hitbox);
        this.spawnType = spawnType;
        enemies = new ArrayList<Enemy>();
        System.out.println(((Object) spawnType).getClass().getName());
    }

    public void spawn(){
        if(enemies.size()<spawnLimit){
            for(int i=0;i<spawnLimit-enemies.size();i++){
                //enemies.add();
            }
            System.out.println("");
        }
    }

}
