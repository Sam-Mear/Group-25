package com.group25.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import java.awt.*;

public class Player extends Creature implements ApplicationListener{

    Level currentLevel;         

    private int coins;
    private int currentMana;
    private int manaLimit = 100;
    private int healthLimit = 100;
    private EnitiyAnimation animation;
    private TextureRegion currentTexture;

    private final int xAttackRange = 20;
    private final int yAttackRange = 10;
   
    public boolean leftMouseClicked = false;

    private boolean moveUpAnimation;
    private boolean moveDownAnimation;
    private boolean moveRightAnimation;
    private boolean moveLeftAnimation;

    private int startFrame, endFrame = 0;

    private Sprite img;

    public Player(Level currentLevel, float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height));

        this.img = img;
        this.currentLevel = currentLevel;

        animatePlayer(startFrame, endFrame);
    }


    /**
     * MOVEMENT WITH W A S D FOR PLAYER
     */
    public void checkKeysPressed(){
        WPressed();
        APressed();
        SPressed();
        DPressed();
        leftMousePressed();
        rightMousePressed();
    }

    private void setStartAndEndFrame(int startFrame, int endFrame){
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    private void WPressed(){
        if(Gdx.input.isKeyPressed(Keys.W)){
            setDirection("up");
            if(currentLevel.checkForCollision('y',getY() + getSpeed())){
				setY(getY() + getSpeed());
			}
            if(!moveDownAnimation && !moveLeftAnimation && !moveRightAnimation)
                moveUpAnimation = true;
        }
        else{
            if(moveUpAnimation) animation.end();
            moveUpAnimation = false;
            upStarted = false;
        }
    }

    
    private void SPressed(){
        if(Gdx.input.isKeyPressed(Keys.S)){
            setDirection("down");
            if(currentLevel.checkForCollision('y',getY() - getSpeed())){
				setY(getY() - getSpeed());
			}
            if(!moveUpAnimation && !moveLeftAnimation && !moveRightAnimation)
                moveDownAnimation = true;
        }
        else{
            if(moveDownAnimation) animation.end();
            moveDownAnimation = false;
            downStarted = false;
        }
    }

    private void APressed(){
        if(Gdx.input.isKeyPressed(Keys.A)){
            setDirection("left");
            if(currentLevel.checkForCollision('x',getX() - getSpeed())){
				setX(getX() - getSpeed());
			}
            if(!moveDownAnimation && !moveUpAnimation && !moveRightAnimation)
                moveLeftAnimation = true;
        }
        else{
            if(leftStarted) animation.end();
            moveLeftAnimation = false;
            leftStarted = false;  
        }
        
    }
   
    private void DPressed(){
        if(Gdx.input.isKeyPressed(Keys.D)){
            setDirection("right");
            if(currentLevel.checkForCollision('x',getX() + getSpeed())){
				setX(getX() + getSpeed());
			}
            if(!moveDownAnimation && !moveLeftAnimation && !moveUpAnimation)
                moveRightAnimation = true;
        }
        else{
            if(rightStarted) animation.end();
            moveRightAnimation = false;
            rightStarted = false;
        }
    }


    public void leftMousePressed(){
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
           playerAttack(currentLevel, 10, 50, 50);
            
        }
    }

    
    public void rightMousePressed(){
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            RangeAttack range = new RangeAttack(currentLevel, this, 300, 10, 10);
            range.getTexture();          
        }
    }

    


    /**
     *  METHOD TO ANIMATE THE PLAYER
     * @param direction
     * @param nbOfFrames
     * @param startFrame
     * @param endFrame
     */
    private void animatePlayer(int startFrame, int endFrame){
        TextureRegion selected = new TextureRegion(img);
     
        animation = new EnitiyAnimation(selected, 10, 20, startFrame, endFrame);
        currentTexture = animation.getCurrentFrame();
    }


    boolean downStarted, upStarted, leftStarted, rightStarted = false;

    public void update() {

        System.out.println(this.health);

        int currentFrame = animation.getCurrentFrameNumber();

        if(currentFrame > 0 && currentFrame < 3 && !moveDownAnimation){
            animation.setCurrentFrameNumber(0);
            setStartAndEndFrame(0, 0);
            animatePlayer(startFrame, endFrame);
            downStarted = upStarted = leftStarted = rightStarted = false;
        }

        if(currentFrame > 3 && currentFrame < 6 && !moveUpAnimation){
            animation.setCurrentFrameNumber(3);
            setStartAndEndFrame(3, 3);
            animatePlayer(startFrame, endFrame);
            downStarted = upStarted = leftStarted = rightStarted = false;
        }

       
        if(moveDownAnimation && !downStarted){
            upStarted = leftStarted = rightStarted = false;
            moveLeftAnimation = moveRightAnimation = moveUpAnimation = false;
            downStarted = true;
            animatePlayer(1, 2);
        }
        if(moveUpAnimation && !upStarted){
            upStarted = true;
            downStarted = leftStarted = rightStarted = false;
            moveLeftAnimation = moveRightAnimation = moveDownAnimation = false;
            animatePlayer(4, 5);
        }
        if(moveLeftAnimation && !leftStarted){
            leftStarted = true;
            downStarted = upStarted = rightStarted = false;
            moveRightAnimation = moveUpAnimation = moveDownAnimation = false;
            animatePlayer(8, 9);
        }
        if(moveRightAnimation && !rightStarted){
            downStarted = upStarted = leftStarted = false;
            moveLeftAnimation= moveUpAnimation = moveDownAnimation = false;
            rightStarted = true;
            animatePlayer(6, 7);
        }


       // System.out.println("Breka");
        this.getHitbox().setLocation((int) this.getX(), (int) this.getY());
       // System.out.printf("Player hitBox x: %d y: %d\n", (int) this.getX(), (int) this.getY());
        //System.out.println("Coin amount: "+coins);
        animation.update(1);
       // animation.getCurrentFrame();
        currentTexture = animation.getCurrentFrame();
    }

    /*
    If the player has stepped over/picked up a drop if so,
     */
    public void pickUp(Drop drop){
        if(this.getHitbox().contains(drop.getHitbox())){
            drop.pickUp();
            if(drop.getType().equals(DropType.MANA)){
                if(currentMana+drop.getAmount()>manaLimit){
                    currentMana = manaLimit;
                }
                else{
                    this.currentMana +=drop.getAmount();
                }
            }
            else if(drop.getType().equals(DropType.COIN)){
                this.coins +=drop.getAmount();
            }
            else if(drop.getType().equals(DropType.HEART)){
                if(health+drop.getAmount()>healthLimit){
                    this.health = healthLimit;
                }
                else{
                    this.health +=drop.getAmount();
                }
            }
        }
    }
    public TextureRegion getTexture(){
        return currentTexture;
    }

    /*
    Negative values, when purchasing or spending coins
    Positive value when gaining coins i.e picking them up
     */
    public void editCoins(int cost){
        this.coins += cost;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getMana() {
        return currentMana;
    }
    /*
    Man can be used up through casting spells
     */
    public void spendMana(int mana) {
        this.currentMana -= mana;
    }

    public void gainMana(int mana){
        this.currentMana += mana;
    }

    public int getManaLimit() {
        return manaLimit;
    }

    public void setManaLimit(int manaLimit) {
        this.manaLimit = manaLimit;
    }

    public int getHealthLimit() {
        return healthLimit;
    }

    public void setHealthLimit(int healthLimit) {
        this.healthLimit = healthLimit;
    }
    public void spendCoins(int coins){
        coins -= coins;
    }


    @Override
    public void create() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void render() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

   
}
