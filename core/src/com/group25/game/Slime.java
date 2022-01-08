package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public class Slime extends Enemy{

    public Slime(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,new Rectangle((int)positionX,(int)positionY,width,height),new Rectangle((int)positionX,(int)positionY,width+200,height+200));
        this.setSpeed(8);
    }

    @Override
    public void update() {
        this.getHitbox().setLocation((int)this.getX(),(int)this.getY());
        this.getAlertArea().setLocation((int)this.getX(),(int)this.getY());
    }
}
