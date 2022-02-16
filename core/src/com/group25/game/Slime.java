package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.Random;

public class Slime extends Enemy{

    Random r;

    public Slime(float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height),  //Hitbox
                new Rectangle((int)positionX,(int)positionY,200,200)); //AlertArea
        r = new Random();
    }

    @Override
    public void explore(Player player) {
        this.getHitbox().setLocation((int) this.getX(), (int) this.getY());
        this.getAlertArea().setLocation((int) (this.getX() - (200 - this.getWidth()) / 2), (int) (this.getY() - (200 - this.getHeight()) / 2));

        this.chasePlayer(player);
        this.moveX(speed*r.nextFloat());
        System.out.println("X MOVE: "+this.getX());
        this.moveY(speed*r.nextFloat());
        System.out.println("Y MOVE: "+this.getY());
        System.out.println("--------------------");
    }
}
