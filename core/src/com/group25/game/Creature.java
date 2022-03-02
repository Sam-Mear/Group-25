package com.group25.game;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.lang.Math;
import java.lang.*;




public abstract class Creature extends GameEntity{

    private Rectangle DamageArea;
    protected float speed;
    protected int health = 100;
    protected float attackSpeed;
    protected float attackDamage;
    private boolean alive = true;
    private String direction;

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

    public float getSize(){
        return (width+height)/2;
    }


    public void setDirection(String direction){
        this.direction = direction;
    }

    public String getDirection(){
        return direction;
    }

    public boolean alive(){
        return alive;
    }

    //Attack Section
    public void playerAttack(Level level, int damage, int xRange, int yRange){
        Creature target = level.getEnemy(xRange, yRange, this);
        if(target == null){
            return;
        }else{
            System.out.println(target.getHealth());

            target.setHealth(target.getHealth()-damage);

        }
    }

    private static Object getposition() {
        return getposition();
    }

    private Collection getDamageArea() {
        return Collections.singleton(health);
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

    public void setHealth(int health){
        this.health = health;
        if(health<=0){
            alive = false;
        }
    }
}
