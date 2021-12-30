package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Thing {
    int x = 0;
    int y = 0;
    Sprite sprite;
    int speed;
    boolean collidable = true;

    public Thing(int positionX, int positionY, Sprite img, int thingSpeed){
        x = positionX;
        y = positionY;
        sprite = img;
        speed = thingSpeed;
    }
    
}
