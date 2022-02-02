package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class Player extends Creature{

    private int coins;
    private int currentMana;
    private int manaLimit = 100;
    private int healthLimit = 10;
    private EnitiyAnimation animation;
    private TextureRegion currentTexture;

    private boolean moveUpAnimation;
    private boolean moveDownAnimation;
    private boolean moveRightAnimation;
    private boolean moveLeftAnimation;

    private int startFrame, endFrame = 0;

    private Sprite img;

    public Player(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height));

        this.img = img;

        animatePlayer(img, 10, startFrame, endFrame);

    }

    public void moveUpAnimation(){
        moveUpAnimation = true;
        setStartAndEndFrame(0, 2);
        animatePlayer(img, 10, startFrame, endFrame);
    }
    public void moveDownAnimation(){
        moveDownAnimation = true;
        setStartAndEndFrame(3, 5);
        animatePlayer(img, 10, startFrame, endFrame);
    }
    public void moveRightAnimation(){
        moveRightAnimation = true;
        setStartAndEndFrame(6,7);
        animatePlayer(img, 10, startFrame, endFrame);
    }
    public void moveLeftAnimation(){
        moveLeftAnimation = true;
        setStartAndEndFrame(8,9);
        animatePlayer(img, 10, startFrame, endFrame);

    }

    private void setStartAndEndFrame(int startFrame, int endFrame){
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    public void setMoveDownAnimation(boolean moveDownAnimation){
        this.moveDownAnimation = moveDownAnimation;
    }

    public void setMoveUpAniation(boolean moveUpAnimation){
        this.moveUpAnimation = moveUpAnimation;
    }

    public void setMoveLeftAnimation(boolean moveLeftAnimation){
        this.moveLeftAnimation = moveLeftAnimation;
    }

    public void setMoveRightAnimation(boolean moveRightAnimation){
        this.moveRightAnimation = moveRightAnimation;
    }


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
