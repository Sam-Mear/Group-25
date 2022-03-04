package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

/**
 * Enviromental obstacles which will be on screen that block the players movements
 */
public class Enviroment extends GameEntity{

    public Enviroment(float positionX, float positionY, int width, int height, Sprite img, Rectangle hitbox) {
        super(positionX, positionY, width, height, img, hitbox);
    }

    public Enviroment(float positionX, float positionY, int width, int height, Sprite img) {
        super(positionX, positionY, width, height, img);
    }
}
