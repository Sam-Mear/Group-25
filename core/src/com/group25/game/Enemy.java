package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public abstract class Enemy extends Creature{

    private Rectangle alertArea;
    private int coinDrop;
    private int manaDrop;
    private int heartDrop;
    private boolean alive = true;

    public Enemy(float positionX, float positionY, int width, int height, int health, Sprite img, float entitySpeed,Rectangle hitbox, Rectangle alertArea) {
        super(positionX, positionY, width, height, health, img, entitySpeed,hitbox);
        this.alertArea = alertArea;
    }

    public Rectangle getAlertArea(){
        return alertArea;
    }

    //We want all mosntsers to have some sort of explore function that allows them to walk around

    /**
     * Explore - allows the enemy to explore a certain region of the map
     * Additionaly, if the enemy has noticed the player it will move towards them
     */
    public abstract void explore(Player player);

    public void chasePlayer(Player player){
        if (this.getAlertArea().contains(player.getHitbox())) {
            if (this.getY() < player.getY()) {
                //go down
                this.setY(this.getY() + this.getSpeed());
            }

            if (this.getY() > player.getY()) {
                //go down
                this.setY(this.getY() - this.getSpeed());
            }

            if (this.getX() < player.getX()) {
                //go down
                this.setX(this.getX() + this.getSpeed());
            }

            if (this.getX() > player.getX()) {
                //go down
                this.setX(this.getX() - this.getSpeed());
            }
        }
    }
    public void takeDamage(int damage){
        if(getHealth()-damage<0){
            //Then we want to drop all of the things
            //If this happens then we would like to render all of the coins and shit
            alive = false;
        }
        else{
            this.editHealth(-damage);
        }
    }

    public int getCoinDrop() {
        return coinDrop;
    }

    public int getManaDrop() {
        return manaDrop;
    }

    public int getHeartDrop() {
        return heartDrop;
    }
}
