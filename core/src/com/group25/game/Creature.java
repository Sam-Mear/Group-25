package com.group25.game;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;

public abstract class Creature extends GameEntity{

    private Rectangle DamageArea;
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

    //Attack Section
    public void DamageArea(int takeDamage){
        if (this.getDamageArea().contains(Creature.getposition())) {
            if (this.getY() < Creature.getY()) {

                this.health = this.health - takeDamage;
            }

            if (this.getY() > Creature.getY()) {

                this.health = this.health - takeDamage;
            }

            if (this.getX() < Creature.getY()) {

                this.health = this.health - takeDamage;
            }

            if (this.getX() > Creature.getY() ) {

                this.health = this.health - takeDamage;
            }
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
}
