package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends GameEntity{
    
    public Player(int positionX, int positionY,int width, int height, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, img, entitySpeed);
        this.setHealth(100);
        this.setSpeed(8);
    }
}
