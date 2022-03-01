package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

import java.awt.*;
import java.util.Random;

public class Slime extends Enemy{

    Random r;

    public Slime(float positionX, float positionY,int width, int height,int health, Sprite img, float entitySpeed){
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

        this.chasePlayer(player);
        this.moveX(speed*r.nextFloat());
        float speedDelta = (float)(r.nextInt(100+100)-100)/100;
        this.moveX(speed*speedDelta);
        this.moveY(speed*speedDelta);
        System.out.println("X MOVE: "+this.getX());
        this.moveY(speed*r.nextFloat());
        System.out.println("Y MOVE: "+this.getY());
        System.out.println("--------------------");
//        int x = r.nextInt(360)+1;
//        double ang = produceChanceAngle(x);
//        System.out.printf("Odds of change in angle %d is %.2f\n",x,ang);
    }
    //This determines if the angle should be changed and if it should the odds of it should be lower
    public double produceChanceAngle(int x){
        double lambda = 180;
        double top = Math.exp(lambda*-1);
        double power = Math.pow(lambda,x);
        long factorial = Utils.factorial(x);

        return (top*power)/factorial;
    }

    //This will be used to perform turns so the enemy can move in one direction and ever so oftent they can cahnge direction
    @Override
    public void turn() {

    }

  
}
