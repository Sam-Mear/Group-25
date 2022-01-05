package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Creature extends GameEntity{

    private int health;

    public Creature(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, img, entitySpeed);
    }    

    public void takeDamage(int damage){
        health -= damage;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }

}
