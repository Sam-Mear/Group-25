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

    //Attack Section
    public void attack(Creature target, int damage, int xRange, int yRange){
    //    if(  (Math.abs(this.getX() + xRange  - 10) < Math.abs(target.getX()/2))
    //         && (Math.abs(this.getY() + yRange   - 10) < Math.abs(target.getY()/2)) ){
                System.out.println(this.getX());
                System.out.println(this.getY());
                System.out.println(target.getX());
                System.out.println(target.getY());

                System.out.println("attack");

                target.setHealth(target.getHealth()-damage);
    //    }
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
    }
}
