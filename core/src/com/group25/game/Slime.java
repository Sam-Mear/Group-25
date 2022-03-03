package com.group25.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

public class Slime extends Enemy{

    private double xSpeed = 1;
    private double ySpeed = 1;
    private Random r;
    private int healt ;
    private int damage, range;
    private int attackSpeed; 
    private EnitiyAnimation animation;
    private TextureRegion current;
    Player player;


    public Slime(Level level,Player player, float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed, int range, int damage,int  attackSpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height),  //Hitbox
                new Rectangle((int)positionX,(int)positionY,200,200)); //AlertArea
        r = new Random();
        this.player = player;

    /*On exploration we want the enemy to:
    Move randomly
    Appraoch player if within in range

    */

                this.health = health;
                this.damage = damage;
                this.range = range;
                this.attackSpeed = attackSpeed;

                level.addEnemy(this);

                Sprite slimes = new Sprite(new Texture(("slimes.png")));

                animation = new EnitiyAnimation(slimes, 3, 15, 0, 1);
                
    }

    private boolean attacked = false;
    int counter = 0;


    public void update(){
        if(getMoving()){
            animation.update(1);
            current = animation.getCurrentFrame();
        }else{
            animation.setCurrentFrameNumber(0);
        }

        if(attacked && counter > 0){
            counter++;
            if(counter == 15){
                attacked = false;
                counter = 0;
            }
            animation.setCurrentFrameNumber(2);    
        }
       
        current = animation.getCurrentFrame();
    }

    public void setAttacked(){
        attacked = true;
        counter = 1;
    }


    public TextureRegion getTexture(){
        return current;
    }



    @Override
    public void explore(Player player) {
        this.getHitbox().setLocation((int) this.getX(), (int) this.getY());
        this.getAlertArea().setLocation((int) (this.getX() - (200 - this.getWidth()) / 2), (int) (this.getY() - (200 - this.getHeight()) / 2));
       // System.out.printf("Slime alertArea left x: %d bottom y: %d\n", (int) (this.getX() - (200 - this.getWidth()) / 2), (int) (this.getY() - (200 - this.getHeight()) / 2));
       // System.out.printf("Slime alertArea right x: %d top y: %d\n", (int) (this.getX() - (200 - this.getWidth()) / 2) + 200, (int) (this.getY() - (200 - this.getHeight()) / 2) + 200);
       // System.out.printf("Slime alertArea: width: %d height: %d\n", 200, 200);
       // this.chasePlayer(player);

        double xTemp = xSpeed;
        double yTemp = ySpeed;
        double deltaA = changeAngle(100,r.nextDouble()) * 2*Math.PI;
        ySpeed = xTemp*Math.sin(deltaA)+yTemp*Math.cos(deltaA);
        xSpeed = xTemp*Math.cos(deltaA)-yTemp*Math.sin(deltaA);

        this.x += xSpeed;
        this.y += ySpeed;
    }

    public double changeAngle(double b,double x){
        double top = 1;
        double bottom = 1+Math.exp(-b*Math.tan(Math.PI*(x-0.5)));
        return top/bottom;
    }

    //This will be used to perform turns so the enemy can move in one direction and ever so oftent they can cahnge direction
    @Override
    public void turn() {

        this.chasePlayer(player, range, damage, attackSpeed, this);
    }

  
}
