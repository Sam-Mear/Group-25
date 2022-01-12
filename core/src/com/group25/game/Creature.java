package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public abstract class Creature extends GameEntity{

    public Creature(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed, Rectangle hitbox){
        super(positionX, positionY, width, height, img, entitySpeed,hitbox);
        hitbox = new Rectangle((int)positionX,(int)positionY,width,height);
    }



}
