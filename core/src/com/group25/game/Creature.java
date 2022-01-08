package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public abstract class Creature extends GameEntity{

    private Rectangle hitbox;

    public Creature(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, img, entitySpeed);
        hitbox = new Rectangle((int)positionX,(int)positionY,width,height);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }
}
