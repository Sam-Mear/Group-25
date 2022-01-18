package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public class Slime extends Enemy{

    public Slime(float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height),  //Hitbox
                new Rectangle((int)positionX,(int)positionY,200,200)); //AlertArea
    }

    @Override
    public void update() {
        this.getHitbox().setLocation((int)this.getX(),(int)this.getY());
        this.getAlertArea().setLocation((int)(this.getX()-(200-this.getWidth())/2),(int)(this.getY()-(200-this.getHeight())/2));
        System.out.printf("Slime alertArea left x: %d bottom y: %d\n",(int)(this.getX()-(200-this.getWidth())/2),(int)(this.getY()-(200-this.getHeight())/2));
        System.out.printf("Slime alertArea right x: %d top y: %d\n",(int)(this.getX()-(200-this.getWidth())/2) + 200,(int)(this.getY()-(200-this.getHeight())/2)+200);
        System.out.printf("Slime alertArea: width: %d height: %d\n",200,200);
        //float xDiff = (float)this.getAlertArea().getX() - this.getX();
        //float yDiff = (float)this.getAlertArea().getY() - this.getY();

       // this.getAlertArea().setLocation((int)(this.getAlertArea().getX()+xDiff),(int)(this.getAlertArea().getY()+yDiff));
    }
}
