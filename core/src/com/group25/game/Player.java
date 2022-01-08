package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public class Player extends Creature{

    private Rectangle hitbox;

    public Player(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed);
        this.setSpeed(8);

        hitbox = new Rectangle((int)positionX,(int)positionY,width,height);
    }

    public Rectangle getHitbox() {
        System.out.println("Hitbox");
        return hitbox;
    }
}
