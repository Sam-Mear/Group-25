package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameEntity {
    protected float x = 0;
    protected float y = 0;
    protected int width;
    protected int height;

    protected Sprite sprite;
    protected int speed;
    protected boolean collidable = true;

    public GameEntity(float positionX, float positionY, int width, int height, Sprite img, int entitySpeed){
        x = positionX;
        y = positionY;
        sprite = img;
        speed = entitySpeed;
        this.width = width;
        this.height = height;
    }
    
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}
}
