package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public abstract class GameEntity {
    protected float x = 0;
    protected float y = 0;
    protected int width;
    protected int height;

    protected Sprite sprite;
    protected float speed;
    protected boolean collidable = true;
	protected Rectangle hitbox;

    public GameEntity(float positionX, float positionY, int width, int height, Sprite img){
		x = positionX;
		y = positionY;
		sprite = img;
		this.width = width;
		this.height = height;
		hitbox = new Rectangle((int)positionX,(int)positionY,width,height);
	}
	
	/**
	 * This constructor would be used when we want to create a GameEntity
	 * with a hitbox that is different to the width and height of the object
	 * @param positionX
	 * @param positionY
	 * @param width
	 * @param height
	 * @param img
	 * @param hitbox
	 */
	public GameEntity(float positionX, float positionY, int width, int height, Sprite img, Rectangle hitbox) {
		x = positionX;
		y = positionY;
		sprite = img;
		this.width = width;
		this.height = height;
		this.hitbox = hitbox;
	}

	public void dispose() {
		//dispose sprite
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

	public void update(){

	}

	public void updateHitbox() {
        this.getHitbox().setLocation((int)this.getX(),(int)this.getY());
    }
  
	public Rectangle getHitbox() {
		return hitbox;
	}
}
