package com.group25.game;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boss extends Enemy{


    private int healt ;
    private int damage, range;
    private int attackSpeed; 
    private EnitiyAnimation animation;
    private TextureRegion current;

    
    public Boss(Level level, float positionX, float positionY, int width, int height, int health, Sprite img, float entitySpeed) {
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

    private boolean attacked = false;
    int counter = 0;

    private boolean started = false;
    private int startFrame, endFrame;
    private int frameCounter = 0;

    public void update(){
        String direction = getDirection();
        if(this.getMoving()){

            if(direction == "up"){
                endFrame = 0;
                startFrame = 0;
                // startFrame = (counter++) % endFrame;
            }else if(direction == "down"){
                endFrame = 1;
                startFrame = 1;
                // startFrame =( 3+ counter++) % endFrame;
            }
            else if(direction == "right"){
                endFrame = 2;
                startFrame = 2;
                // startFrame = (6+ counter++) % endFrame;
            }
            else if(direction == "left"){
                endFrame = 3;
                startFrame = 3;
                // startFrame =( 9 + counter++) % endFrame;

            }
            animation = new EnitiyAnimation(new Sprite(new Texture(("mummy.png"))), 4, 60, startFrame, endFrame);

        }
       
        current = animation.getCurrentFrame();
    }



    public void setAttacked(){
        attacked = true;
        counter = 1;
    }

    public TextureRegion getTexture(){

        return animation.getCurrentFrame();
    }

    @Override
    public void explore(Player player) {
        this.getHitbox().setLocation((int) this.getX(), (int) this.getY());
        this.getAlertArea().setLocation((int) (this.getX() - (200 - this.getWidth()) / 2), (int) (this.getY() - (200 - this.getHeight()) / 2));
        this.chasePlayer(player, 30, 5, 15, this, 50, 50);
        this.updateMovement();
    }

    public double changeAngle(double b,double x){
        double top = 1;
        double bottom = 1+Math.exp(-b*Math.tan(Math.PI*(x-0.5)));
        return top/bottom;
    }

    
}
