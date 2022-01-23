package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public class Player extends Creature{

    public Player(float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed, new Rectangle((int)positionX,(int)positionY,100,100));
    }

    @Override
    public void update() {
      //  this.getHitbox().setBounds((int)this.getX(),(int)this.getY(),this.getWidth(),this.getHeight());
        this.getHitbox().setLocation((int)this.getX(),(int)this.getY());
    }
}
