package com.group25.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Player extends Creature{

    private int coins;
    private int currentMana;
    private int manaLimit = 100;
    private int healthLimit = 10;
    private EnitiyAnimation animation;
    private TextureRegion currentTexture;
    
    private SpriteBatch mainCharacterSpriteBatch;

    TextureRegion[][] allMovementSprites;
    TextureRegion[] movementUp;
    TextureRegion[] movementDown;
    TextureRegion[] movementleft;
    TextureRegion[] movementRight;

    Animation<TextureRegion> upWalkAnimation;
    Animation<TextureRegion> dowonWalkAnimation;
    Animation<TextureRegion> rightWalkAnimation;
    Animation<TextureRegion> leftWalkAnimation;
    

    Texture walkSheet;

    private Level level;


    public Player(float positionX, float positionY,int width, int height,int health, Sprite img, int entitySpeed, Level level){
        super(positionX, positionY, width, height, health, img, entitySpeed,
                new Rectangle((int)positionX,(int)positionY,width,height));
        
        this.level = level;
        mainCharacterSpriteBatch = new SpriteBatch();

        walkSheet = new Texture(Gdx.files.internal("C:/Users/Banica/Documents/GitHub/Group-25/core/assets/Main_character_sprite.png"));

        allMovementSprites = TextureRegion.split(walkSheet, 50, 48);
        
        for(int i=0; i<=2; i++){
            movementUp[i] = allMovementSprites[i][0];
        }
        for(int i=3; i<=5; i++){
            movementUp[i] = allMovementSprites[i][0];
        }
        for(int i=5; i<=7; i++){
            movementUp[i] = allMovementSprites[i][0];
        }
        for(int i=7; i<=9; i++){
            movementUp[i] = allMovementSprites[i][0];
        }



        animation = new EnitiyAnimation(new TextureRegion(img),3,20);
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


    public void keyboardInput(){
        if(Gdx.input.isKeyPressed(Keys.W)){
            System.out.println("W");
			if(level.checkForCollision('y',this.getY() + this.getSpeed())){
				System.out.println("PRESSING UP");
				System.out.println("Speed: "+ this.getSpeed());
				this.setY(this.getY() +this.getSpeed());
			}
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			if(level.checkForCollision('y', this.getY() - this.getSpeed())){
				this.setY(this.getY() - this.getSpeed());
			}
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			if(level.checkForCollision('x',this.getX() -this.getSpeed())){
				this.setX(this.getX() -this.getSpeed());
			}
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			if(level.checkForCollision('x',this.getX() + this.getSpeed())){
				this.setX(this.getX() + this.getSpeed());
			}
    }
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
