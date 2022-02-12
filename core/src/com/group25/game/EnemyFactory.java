package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

/**
 * Enemy Factory will be used to create more monsters on a given spawner, to create more monsters you will have to add them into here so it can break them down and add them
 */
public interface EnemyFactory {

    /**
     * Returns a new monster based on the type you have passed it will then create that type of monster
     * @param width
     * @param height
     * @param health
     * @param img
     * @param entitySpeed
     * @param hitbox
     * @param alertArea
     * @return
     */
    public Enemy getNewMonster(int width, int height, int health, Sprite img, float entitySpeed);
}
