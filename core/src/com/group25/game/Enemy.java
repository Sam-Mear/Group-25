package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public abstract class Enemy extends Creature{

    private Rectangle alertArea;

    public Enemy(float positionX, float positionY, int width, int height, int health, Sprite img, int entitySpeed,Rectangle hitbox, Rectangle alertArea) {
        super(positionX, positionY, width, height, health, img, entitySpeed,hitbox);
        this.alertArea = alertArea;
    }

    public Rectangle getAlertArea(){
        return alertArea;
    }
}
