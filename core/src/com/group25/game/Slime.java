package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

import java.awt.*;
import java.util.Random;

public class Slime extends Enemy{

    private double xSpeed = 1;
    private double ySpeed = 1;
    private Random r;

    public Slime(Level level, float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height),  //Hitbox
                new Rectangle((int)positionX,(int)positionY,200,200)); //AlertArea
        r = new Random();
    }

    /*On exploration we want the enemy to:
    Move randomly
    Appraoch player if within in range

    */
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

    }

  
}
