package com.group25.game;

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

public class Player extends Creature{

    Level currentLevel;

    private int coins;
    private int currentMana;
    private int manaLimit = 100;
    private int healthLimit = 10;
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

        

        animatePlayer(img, 10, startFrame, endFrame);

    }


    /**
     * MOVEMENT WITH W A S D FOR PLAYER
     */
    public void checkKeysPressed(){
        WPressed();
        APressed();
        SPressed();
        DPressed();
        animatePlayer(img, 10, startFrame, endFrame);

        mousePressed();

    }

    private void setStartAndEndFrame(int startFrame, int endFrame){
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    private void WPressed(){
        if(Gdx.input.isKeyPressed(Keys.W)){
            if(currentLevel.checkForCollision('y',getY() + getSpeed())){
				setY(getY() + getSpeed());
                setStartAndEndFrame(3, 5);
			}
            moveUpAnimation = true;
        }
        else{
            moveDownAnimation = false;
        }
    }
    
    private void SPressed(){
        if(Gdx.input.isKeyPressed(Keys.S)){
            if(currentLevel.checkForCollision('y',getY() - getSpeed())){
				setY(getY() - getSpeed());
                setStartAndEndFrame(0, 2);
                animatePlayer(img, 10, startFrame, endFrame);
			}
            moveDownAnimation = true;
        }
        else{
            moveDownAnimation = false;
        }
    }

    private void APressed(){
        if(Gdx.input.isKeyPressed(Keys.A)){
            if(currentLevel.checkForCollision('x',getX() - getSpeed())){
				setX(getX() - getSpeed());
                setStartAndEndFrame(8, 9);
			}
            moveLeftAnimation = true;
        }
        else{
            moveLeftAnimation = false;
        }
        
    }
   
    private void DPressed(){
        if(Gdx.input.isKeyPressed(Keys.D)){
            if(currentLevel.checkForCollision('x',getX() + getSpeed())){
				setX(getX() + getSpeed());
                setStartAndEndFrame(6, 7);
			}
            moveRightAnimation = true;
        }
        else{
            moveRightAnimation = false;
        }
    }

    /**
     * ATTACK ELEMENTS
     */






    /**
     * function that returns true if the left mouse is pressed
     */
    private void mousePressed(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Buttons.LEFT) {
                    System.out.print("left");
                    leftMousePressed();
                    return true;
                }
                return false;
            }
        });
    }


    public void leftMousePressed(){
        currentLevel.attack(xAttackRange, yAttackRange, 1);
    }




    /**
     *  METHOD TO ANIMATE THE PLAYER
     * @param direction
     * @param nbOfFrames
     * @param startFrame
     * @param endFrame
     */
    private void animatePlayer(Sprite direction, int nbOfFrames, int startFrame, int endFrame){
        TextureRegion selected = new TextureRegion(img);

     
        animation = new EnitiyAnimation(selected, nbOfFrames, 20, startFrame, endFrame);
        currentTexture = animation.getCurrentFrame();
    }

    public void update() {
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

   
}
