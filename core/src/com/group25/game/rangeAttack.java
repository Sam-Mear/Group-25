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

    private int x, y;
    private boolean end;
    Batch batch;
    private Sprite attack;
    private boolean shooting = false;
    private Level level;
    private int distance = 0;
    private int range;
    private String direction;
    private Creature creature;
    private float xStart;
    private float yStart;
    
    public RangeAttack(Level level, Creature creature, String direction, int range, float xStart, float yStart){
        attack = new Sprite(new Texture("coin.png"));;
        this.range = range;
        this.xStart = xStart;
        this.yStart = yStart;
        this.level = level;
        this.direction = direction;
        this.creature = creature;

    }

    public boolean attack(){
        if(direction == "up" && Math.abs(this.y - creature.getY()) <=range){
           this.setY(y + 1);
            return true;
        }
        if(direction == "down" && Math.abs(this.y - creature.getY()) <= range ){
            this.setY(y - 1);
            return true;
        }
        if(direction == "right" && Math.abs(this.x - creature.getX()) <= range){
            this.setY(x + 1);
            return true;
        }
        if(direction == "left" && Math.abs(this.x - creature.getX()) <= range){
            this.setY(x - 1);
            return true;
        }
       return false;
    }


    public TextureRegion getTexture(){
        return this.attack;
    }

    public boolean shooting(){
        return shooting;
    }

    public void setShooting(boolean shooting){
        this.shooting = shooting;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
    



}
