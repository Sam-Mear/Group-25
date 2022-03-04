package com.group25.game;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Mummy extends Enemy{


    private int healt ;
    private int damage, range;
    private int attackSpeed; 
    private EnitiyAnimation animation;
    private TextureRegion current;

    private boolean attacked = false;
    int counter = 0;

    private boolean started = false;
    private int startFrame, endFrame;
    private int frameCounter = 0;

    /**
     *  a child class of enemy
     * @param level     current level
     * @param positionX
     * @param positionY
     * @param width
     * @param height
     * @param health
     * @param img       the sprite of the char if it is does not change
     * @param entitySpeed
     */
    public Mummy(Level level, float positionX, float positionY, int width, int height, int health, Sprite img, float entitySpeed) {
                super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height),  //Hitbox
                new Rectangle((int)positionX,(int)positionY,200,200)); //AlertArearea
        //TODO Auto-generated constructor stub

        this.health = health;
        this.damage = damage;
        this.range = range;
        this.attackSpeed = attackSpeed;

        level.addEnemy(this);

        animation = new EnitiyAnimation(new Sprite(new Texture(("mummy.png"))), 4, 20, 1, 1);
    }

    /**
     *  update the current frames from the sprite sheet so that the sprite is in concordance with the movement
     */
    public void update(){
        String direction = getDirection();
        if(this.getMoving()){

            if(direction == "up"){
                endFrame = 0;
                startFrame = 0;
            }else if(direction == "down"){
                endFrame = 1;
                startFrame = 1;
            }
            else if(direction == "right"){
                endFrame = 2;
                startFrame = 2;
            }
            else if(direction == "left"){
                endFrame = 3;
                startFrame = 3;
            }
            animation = new EnitiyAnimation(new Sprite(new Texture(("mummy.png"))), 4, 15, startFrame, endFrame);
        }
        current = animation.getCurrentFrame();
    }

    /**
     * 
     * @return current sprite
     */
    public TextureRegion getTexture(){
        return animation.getCurrentFrame();
    }

    @Override
    public void explore(Player player) {
        this.getHitbox().setLocation((int) this.getX(), (int) this.getY());
        this.getAlertArea().setLocation((int) (this.getX() - (200 - this.getWidth()) / 2), (int) (this.getY() - (200 - this.getHeight()) / 2));
        this.chasePlayer(player, 30, 5, 15, this, 50, 50);
    } 

    public double changeAngle(double b,double x){
        double top = 1;
        double bottom = 1+Math.exp(-b*Math.tan(Math.PI*(x-0.5)));
        return top/bottom;
    }
}
