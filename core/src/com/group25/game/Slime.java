package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public class Slime extends Enemy{

    public Slime(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,new Rectangle((int)positionX,(int)positionY,200,200));
        this.setSpeed(8);
    }
}
