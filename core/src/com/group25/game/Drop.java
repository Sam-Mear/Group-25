package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

/**
 * Drop will be used to define different pickables which the player can pick up
 * Drop will define: mana,health and coins but can be expanded to include more dropable items
 */
public class Drop extends Item{

    private final int amount;
    private final DropType type;

    public Drop(float positionX, float positionY, int width, int height, Sprite img,int amount,DropType type) {
        super(positionX, positionY, width, height, img);
        this.amount = amount;
        this.type = type;
    }

    public Drop(float positionX, float positionY, int width, int height, Sprite img, Rectangle hitbox,int amount,DropType type) {
        super(positionX, positionY, width, height, img, hitbox);
        this.amount = amount;
        this.type = type;
    }

    public int getAmount(){
        return amount;
    }

    public DropType getType(){
        return type;
    }
}
