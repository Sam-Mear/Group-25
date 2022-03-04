package com.group25.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class Bat extends Enemy{

    private int health ;
    private int damage, range;
    private int attackSpeed;
    private EnitiyAnimation animation;
    private TextureRegion current;

    /**
     *  child class of enemy 
     * @param level
     * @param positionX
     * @param positionY
     * @param width
     * @param height
     * @param health
     * @param img
     * @param entitySpeed
     * @param range
     * @param damage
     * @param attackSpeed
     */
    public Bat(Level level, float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed, int range, int damage,int  attackSpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
        new Rectangle((int)positionX,(int)positionY,width,height),  //Hitbox
        new Rectangle((int)positionX,(int)positionY,500,500)); //AlertArea

        this.health = health;
        this.damage = damage;
        this.range = range;
        this.attackSpeed = attackSpeed;
        Sprite bats = new Sprite(new Texture(("bats.png")));
        level.addEnemy(this);

        animation = new EnitiyAnimation(bats, 4, 20, 0, 3);    
    }

    /**
     * constanly rotate throught the frames
     */
    public void update(){
        animation.update(1);
        current = animation.getCurrentFrame();
    }

    /**
     * return the current sprite
     * @return
     */
    public TextureRegion getTexture(){
        return current;
    }

    @Override
    public void explore(Player player) {
        this.getHitbox().setLocation((int) this.getX(), (int) this.getY());
        this.getAlertArea().setLocation((int) (this.getX() - (200 - this.getWidth()) / 2), (int) (this.getY() - (200 - this.getHeight()) / 2));
        this.chasePlayer(player, 30, 5, 30, this, 30, 30);
        if( this.getMoving()){
                this.updateMovement();
        }
       
    }

    public double changeAngle(double b,double x){
        double top = 1;
        double bottom = 1+Math.exp(-b*Math.tan(Math.PI*(x-0.5)));
        return top/bottom;
    }

}

