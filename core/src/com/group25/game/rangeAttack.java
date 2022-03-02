package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class RangeAttack{

    private float x, y;
    private boolean end;
    Batch batch;
    private Sprite attack;
    private boolean alive;
    private Level level;
    private int distance = 0;
    private int range;
    private String direction;
    private Creature creature;
    private float xStart;
    private float yStart;
    private int size;
    
    public RangeAttack(Level level,String direction, int range, float xStart, float yStart, int size){
        attack = new Sprite(new Texture("coin.png"));;
        this.range = range;
        this.x = xStart;
        this.y = yStart;
        this.level = level;
        this.direction = direction;
        this.creature = creature;
        this.alive = true;
    }


    public void update(){
        boolean found = true;
        if(distance >= range){
            alive = false;
        }else{
            distance++;
            if(direction == "up" && found){
                this.setY(y + 1);
                found = false;
             }
             if(direction == "down" && found){
                 this.setY(y - 1);
                 found = false;
             }
             if(direction == "right" && found){
                 this.setX(x + 1);
                 found = false;
             }
             if(direction == "left" && found){
                this.setX(x - 1);
                found = false;
             }
        }


    }

    public String getDirection(){
        return direction;
    }

    public int getSize(){
        return size;
    }

    public TextureRegion getTexture(){
        return this.attack;
    }

    public boolean getAlive(){
        return alive;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
    



}
