package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public class Player extends Creature{

    public Player(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height));
    }

    @Override
    public void update() {
        this.getHitbox().setLocation((int)this.getX(),(int)this.getY());
        System.out.printf("Player hitBox x: %d y: %d\n",(int)this.getX(),(int)this.getY());

    }
}
