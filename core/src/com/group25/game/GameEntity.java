package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameEntity {
    int x = 0;
    int y = 0;
    Sprite sprite;
    int speed;
    boolean collidable = true;

    public GameEntity(int positionX, int positionY, Sprite img, int entitySpeed){
        x = positionX;
        y = positionY;
        sprite = img;
        speed = entitySpeed;
    }
    
}
