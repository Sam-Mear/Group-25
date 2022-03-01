package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

/**
 * Enemy Factory will be used to create more monsters on a given spawner, to create more monsters you will have to add them into here so it can break them down and add them
 */
public interface EnemyFactory {

    /**
     * Returns a new monster based on the type you have passed it will then create that type of monster
     * @param width     - width of monster
     * @param height    - height of monster
     * @param health    - monsters health
     * @param img       - sprite of how the monster will look like
     * @param entitySpeed   - How quickly the monster wil travel across the map
     * @return
     */
    public Enemy getNewMonster(int x, int y,int width, int height, int health, Sprite img, float entitySpeed);
}
