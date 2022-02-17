package com.group25.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

//Items will not appear on the game window i.e. on the floor
//This only really applies to drop items i guess?
public abstract class Item extends EnviromentAnimated {// TODO : make item extend EnviromentAnimated maybe for the animationsQ!!!!!

    private boolean pickedUp = false;   //This would mean an item has been rendered

    public Item(float positionX, float positionY, int width, int height, Sprite img, int frames, int animationSpeed) {
        super(positionX, positionY, width, height, img, frames, animationSpeed);
    }

    public Item(float positionX, float positionY, int width, int height, Sprite img, int frames, int animationSpeed, Rectangle hitbox) {
        super(positionX, positionY, width, height, img, frames, animationSpeed, hitbox);
    }

    public void pickUp(){
        this.pickedUp = true;
    }

    public boolean isPickedUp(){
        return pickedUp;
    }
}