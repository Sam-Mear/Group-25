package com.group25.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

public class Slime extends Enemy{

    private int health ;
    private int damage, range;
    private int attackSpeed;
    private EnitiyAnimation animation;
    private TextureRegion current;


    public Slime(Level level,  float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed, int range, int damage,int  attackSpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height),  //Hitbox
                new Rectangle((int)positionX,(int)positionY,400,400)); //AlertArea

                this.health = health;
                this.damage = damage;
                this.range = range;
                this.attackSpeed = attackSpeed;
                level.addEnemy(this);

                Sprite slimes = new Sprite(new Texture(("slimes.png")));

                animation = new EnitiyAnimation(slimes, 3, 15, 0, 1);
                
    }

    private boolean attacked = false;
    int counter = 0;


    public void update(){
        if(getMoving()){
            animation.update(1);
            current = animation.getCurrentFrame();
        }else{
            animation.setCurrentFrameNumber(0);
        }

        if(attacked && counter > 0){
            counter++;
            if(counter == 15){
                attacked = false;
                counter = 0;
            }
            animation.setCurrentFrameNumber(2);    
        }
       
        current = animation.getCurrentFrame();
    }

    public void setAttacked(){
        attacked = true;
        counter = 1;
    }

    public TextureRegion getTexture(){
        return current;
    }

    @Override
    public void explore(Player player) {
        this.getHitbox().setLocation((int) this.getX(), (int) this.getY());
        this.getAlertArea().setLocation((int) (this.getX() - (200 - this.getWidth()) / 2), (int) (this.getY() - (200 - this.getHeight()) / 2));
       if(!this.chasePlayer(player, 30, 5, 15, this)) {
           this.updateMovement();
       }
    }
}
