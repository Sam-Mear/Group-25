package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public abstract class Creature extends GameEntity{

    protected float speed;
    protected int health;
    protected float attackSpeed;
    protected float attackDamage;

    public Creature(float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed, Rectangle hitbox){
        super(positionX, positionY, width, height, img,hitbox);
        this.health = health;
        this.speed = entitySpeed;
    }

    public Creature(float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed){
        super(positionX, positionY, width, height, img);
        this.health = health;
        this.speed = entitySpeed;
    }

    public void editHealth(int edit){
        health += edit;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void moveX(float x){
        this.setX(this.getX()+x);
    }

    public void moveY(float y){
        this.setY(this.getY()+y);
    }
}
