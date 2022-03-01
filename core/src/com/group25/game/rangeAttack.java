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
    
    public RangeAttack(Level level, Creature creature, int range, int xSize, int ySize){
        attack = new Sprite(new Texture("coin.png"));
        this.level = level;
    }

    public boolean attack(String direction, float f, float g){
        if(direction == "up" && Math.abs(g - y) <= range){
           this.setY(y + 1);
            return true;
        }
        if(direction == "down" && Math.abs(g + y) <= range){
            this.setY(y + 1);
            return true;
        }
        if(direction == "right" && Math.abs(f - x) <= range){
            this.setY(y + 1);
            return true;
        }
        if(direction == "left" && Math.abs(f - y) <= range){
            this.setY(y + 1);
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
