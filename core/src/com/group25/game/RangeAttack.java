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
    
    public RangeAttack(Level level, Creature creature, int range, int xSize, int ySize){

        attack = new Sprite(new Texture("heart.png"));

    }

    public TextureRegion getTexture(){
        return attack;
    }


    public int getX(){
        return x;
    }

    public void getY(){

    }

    public void setX(int x){
        this.x = x;
    }

    public void getY(int y){
        this.y = y;
    }
    



}
