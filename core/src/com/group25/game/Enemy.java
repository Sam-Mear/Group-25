package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends Creature{

    private Rectangle alertArea;
    private int coinDrop = 100;
    private int manaDrop  = 100;
    private int heartDrop = 100;
    private float range;
    private float damage;
    private boolean alive = true;
    private double ySpeed;
    private double xSpeed;
    private Random r;
    private String direction;
    private boolean isMoving = false;
    private boolean looted = false;

    /**
     * 
     * @param positionX     positon X of the enemy
     * @param positionY     positon Y of the enemy
     * @param width         width of the enemy
     * @param height        height of the enemy
     * @param health        helth of the enemy
     * @param img           sprite of the enemy(if the enemy uses only 1 sprite)
     * @param entitySpeed       speed o the enemy
     * @param hitbox            rectange hitbox of the enemy
     * @param alertArea         rectangle alert area where enemies will start following he player if it is in there
     */
    public Enemy(float positionX, float positionY, int width, int height, int health, Sprite img, float entitySpeed,Rectangle hitbox, Rectangle alertArea) {
        super(positionX, positionY, width, height, health, img, entitySpeed,hitbox);
        this.alertArea = alertArea;
        r = new Random();
        this.xSpeed = entitySpeed;
        this.ySpeed = entitySpeed;

        this.xSpeed = 1;
        this.ySpeed = 1;
    }
    public Rectangle getAlertArea(){
        return alertArea;
    }

    //We want all mosntsers to have some sort of explore function that allows them to walk around

    /**
     * Explore - allows the enemy to explore a certain region of the map
     * Additionaly, if the enemy has noticed the player it will move towards them
     */
    public abstract void explore(Player player);

    private int counter = 0;

    /**
     *  the enemy will start chasing the plyar if it is in the alert area
     * @param player
     * @param range
     * @param damage
     * @param attackCounter     freqeuncy of the attacks
     * @param attacker          the enemy that attacks
     * @param safetyX              how far in X coord will the range attack spawn so it doesn't damage itslef
     * @param safetyY           how far in X coord will the range attack spawn so it doesn't damage itslef
     */
    public boolean chasePlayer(Player player, int range, int damage, int  attackCounter, Creature attacker, int safetyX, int safetyY){

        if(attacker.alive()){
            counter++;
            if((this instanceof Slime || this instanceof Bat || this instanceof Boss) && counter%attackCounter==0 && player.alive()){
               suroundAttack(player, range, damage);
            }
            
            /**
             * based on where the player is related to the enemy choose where the enemy should go and where to attack
             * based on the type of enemy choose what attacks are available for that type
             */
            if (this.getAlertArea().contains(player.getHitbox())) {
                isMoving = true;
                if (this.getY() < player.getY()) {
                    this.direction = "up";
                    this.setY(this.getY() + this.getSpeed());
                    if(counter% (attackCounter*10) ==0){
                        if(this instanceof Mummy || this instanceof Boss)
                            rangeAttack(player, range, damage, "up", getX() ,getY()+safetyY);
                        if(!(this instanceof Slime))
                            directedShortAttack(player, range, damage/5, direction, getX(), getY());
                    }
                }
    
                if (this.getY() > player.getY()) {
                    this.direction = "down";
                    this.setY(this.getY() - this.getSpeed());
                    if(counter% (attackCounter*10)==0){
                        if(this instanceof Mummy || this instanceof Boss)
                            rangeAttack(player, range, damage, "down", getX() ,getY()-safetyY);
                        if(!(this instanceof Slime))
                            directedShortAttack(player, range, damage/5, direction, getX(), getY());
                    }
                }
    
                if (this.getX() < player.getX()) {
                    this.direction = "right";
                    this.setX(this.getX() + this.getSpeed());
                    if(counter% (attackCounter*10)==0){
                        if(this instanceof Mummy || this instanceof Boss)
                            rangeAttack(player, range, damage, "right", getX()+safetyX ,getY());
                        if(!(this instanceof Slime))
                            directedShortAttack(player, range, damage/5, direction, getX(), getY());
                    }
                }

                if (this.getX() > player.getX()) {
                    this.direction = "left";
                    this.setX(this.getX() - this.getSpeed());
                    if(counter% (attackCounter*10)==0){
                        if(this instanceof Mummy || this instanceof Boss)
                            rangeAttack(player, range, damage, "left", getX()-safetyX ,getY());
                        if(!(this instanceof Slime))
                            directedShortAttack(player, range, damage/5, direction, getX(), getY());
                    }
                }
                return true;
            }else{
                isMoving = false;
            }
        }
        return false;
    }


    protected boolean getMoving(){
        return isMoving;
    }

    public String getDirection(){
        return direction;
    }

    /**
     *  creates a projectile that shoots in the directions the enemy is moving
     * @param player
     * @param range
     * @param damage
     * @param direction
     * @param xStart
     * @param yStart
     */
    private void rangeAttack(Player player, float range, int damage, String direction, float xStart, float yStart){
        RangeAttack rangeAttack = new RangeAttack(player.getLevel(), direction, 200, xStart, yStart, 3);
        player.getLevel().addProjectile(rangeAttack);
    }

    /**
     *  calls the playerAttack class in Creature that attacks short range just in front of the enemy
     * @param player
     * @param range
     * @param damage
     * @param direction
     * @param xStart
     * @param yStart
     */
    private void directedShortAttack(Player player, float range, int damage, String direction, float xStart, float yStart){
        playerAttack(player.getLevel(), (int)damage, (int)range, (int)range*2);
    }

    /**
     * attacks in a radius around the enemy
     * @param player
     * @param range
     * @param damage
     */
    private void suroundAttack(Player player, int  range, int damage){
        if(Math.abs(player.getX() - this.getX()) <= range)
        if(Math.abs(player.getY() - this.getY()) <= range){
            player.setHealth(player.getHealth()-damage);
         }
    }

    public boolean isLooted() {
        return looted;
    }

    public void setLooted(boolean looted) {
        this.looted = looted;
    }


    
    public void takeDamage(int damage){
        //If the damage accumulated is more than the enemy health they will die
        if(getHealth()-damage<0){
            alive = false;
        }
        else{
            this.editHealth(-damage);
        }
    }

    public int getCoinDrop() {
        return coinDrop;
    }

    public int getManaDrop() {
        return manaDrop;
    }

    public int getHeartDrop() {
        return heartDrop;
    }

    /**
     * Update movement tick, will update the enemies x and y speed
     *  based on the change in angle it will move around smoothly and well dsitributed
     */
    public void updateMovement(){
        double xTemp = xSpeed;  //Current xSpeed
        double yTemp = ySpeed;  //Current ySpeed
        double deltaA = changeAngle(100,r.nextDouble()) * 2*Math.PI;    //Change in angle
        ySpeed = xTemp*Math.sin(deltaA)+yTemp*Math.cos(deltaA); //Updated ySpeed
        xSpeed = xTemp*Math.cos(deltaA)-yTemp*Math.sin(deltaA); //Update xSpeed

        //Sends movement boundries
        if(this.x>this.width && this.x<Level.GAME_WORLD_WIDTH-this.width){
            this.x += xSpeed;
        }
        if(this.y>this.height && this.y<Level.GAME_WORLD_HEIGHT-this.height){
            this.y += ySpeed;
        }
    }

    /**
     * Change Angle - determines the probabilty of an angle change in the enemies movement
     * The probability is distributed so that more likely to be on either ends of 0-1 and less liekly in the middle
     * @param b //Number of trials it will run
     * @param x //A random float which will calculate the odds of getting it
     * @return
     */
    public double changeAngle(double b,double x){
        double top = 1;
        double bottom = 1+Math.exp(-b*Math.tan(Math.PI*(x-0.5)));
        return top/bottom;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }
}
