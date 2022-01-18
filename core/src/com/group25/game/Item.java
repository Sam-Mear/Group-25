package com.group25.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

//Items will not appear on the game window i.e. on the floor
//This only really applies to drop items i guess?
public abstract class Item extends GameEntity {

    public Item(float positionX, float positionY, int width, int height, Sprite img) {
        super(positionX, positionY, width, height, img);
    }

    public Item(float positionX, float positionY, int width, int height, Sprite img, Rectangle hitbox) {
        super(positionX, positionY, width, height, img, hitbox);
    }
}