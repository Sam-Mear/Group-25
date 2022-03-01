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
    private int spawnLimit = 10;    //We want to only spawn enemies when the limit is reached

    private int spawnTime;          //How often we want enemies to spawn
    private int repeatedFrames;     //How many times have the frames been cycled
    private EnemyFactory factory;   //Factory design pattern to spawn enemies
    private Sprite img;             //Image of what the spawner looks like

    private Enemy[] associatedEnemies;

    /**
     *
     * @param positionX - x position of where the spanwer appears
     * @param positionY - y position of where the spawner appears
     * @param width - width of the spanwer
     * @param height - height of the spawner
     * @param img - display image which will display the spawner
     * @param factory - Factory which will be responsible for creating the appointed enemies
     * @param spawnTime - Frames taken to create a new monster
     */
    public EnemySpawner(float positionX, float positionY, int width, int height, Sprite img,EnemyFactory factory, int spawnTime) {
        super(positionX, positionY, width, height, img);
        this.factory = factory;
        this.img = img;
        this.spawnTime = spawnTime;
        repeatedFrames = 0;
        associatedEnemies = new Enemy[spawnLimit];
       // spawnLimit = 1;
    }

    public EnemySpawner(float positionX, float positionY, int width, int height, Sprite img, Rectangle hitbox,EnemyFactory factory, int spawnTime) {
        super(positionX, positionY, width, height, img, hitbox);
        this.factory = factory;
        this.img = img;
        this.spawnTime = spawnTime;
        repeatedFrames = 0;
        associatedEnemies = new Enemy[spawnLimit];
        //spawnLimit = 1;
    }

    /**
     * Function which over a certain amount of frames will create a new monster and add it to the level currently running
     * @param enemies   - list of enemies to which we want to add the monster
     * @param positionX - position X where the monster will spawn
     * @param positionY - position Y where the monster will spawn
     * @param width     - Width of the monster
     * @param height    - height of the monster
     * @param health    - health of the monster
     * @param img       - image of how the monster will look like
     * @param speed     - how quickly the monster travels
     */
    public void spawnNewMonster(ArrayList<Enemy> enemies, int positionX, int positionY, int width,int height,int health, Sprite img,int speed){
        if(enemies.size()<spawnLimit){
            if(repeatedFrames == spawnTime){
                repeatedFrames = 0;
                enemies.add(factory.getNewMonster(positionX,positionY,width,height,health,img,speed));
            }
            repeatedFrames++;
        }
    }

}
