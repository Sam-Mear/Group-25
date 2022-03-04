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

    public Drop(float positionX, float positionY, int width, int height, Sprite img, int frames, int animationSpeed, int amount,DropType type) {
        super(positionX, positionY, width, height, img, frames, animationSpeed);
        this.amount = amount;
        this.type = type;
    }

    public Drop(float positionX, float positionY, int width, int height, Sprite img, int frames, int animationSpeed, Rectangle hitbox,int amount,DropType type) {
        super(positionX, positionY, width, height, img, frames, animationSpeed, hitbox);
        this.amount = amount;
        this.type = type;
    }

    /**
     * How much does the value contain
     * @return
     */
    public int getAmount(){
        return amount;
    }

    /**
     * What is the corrosponding enum for this
     * @return
     */
    public DropType getType(){
        return type;
    }
}
