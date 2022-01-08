package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public abstract class GameEntity {
    protected float x = 0;
    protected float y = 0;
    protected int width;
    protected int height;

    protected Sprite sprite;
    protected int speed;
    protected boolean collidable = true;

	protected Rectangle hitbox;

	private int health;

    public GameEntity(float positionX, float positionY, int width, int height, Sprite img, int entitySpeed, Rectangle hitbox) {
		x = positionX;
		y = positionY;
		sprite = img;
		speed = entitySpeed;
		this.width = width;
		this.height = height;
		this.hitbox = hitbox;
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

	public void takeDamage(int damage){
		health -= damage;
	}

	public void setHealth(int health){
		this.health = health;
	}

	public int getHealth(){
		return health;
	}

	public abstract void update();

	public Rectangle getHitbox() {
		return hitbox;
	}
}
