package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Factory which will only spawn monsters
 */
public class SlimeFactory implements EnemyFactory{

    /**
     * Function which will create a new monster object and return it
     * @param x         - x position of the monster
     * @param y         - y position of the monster
     * @param width     - width of monster
     * @param height    - height of monster
     * @param health    - monsters health
     * @param img       - sprite of how the monster will look like
     * @param entitySpeed   - How quickly the monster wil travel across the map
     * @return new Slime
     */

    @Override
    public Enemy getNewMonster(Level level, int x,int y,int width, int height, int health, Sprite img, float entitySpeed) {
        return new Slime(level, x,y,width,height,health,img , entitySpeed, 30, 5, 15);
    }

   
}
