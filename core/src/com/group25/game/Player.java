package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Creature{

    
    public Player(int positionX, int positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed);
        this.setSpeed(8);
    }
}
