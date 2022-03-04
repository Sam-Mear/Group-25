package com.group25.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class StoreScreen implements Screen{

    private SpriteBatch batch;
    private Sprite backgroundImage;
    private SpriteBatch UIElements;

    private Texture purchase10Coins;
    private Texture purchase10CoinsHighlight;

    private Texture purchase30Coins;
    private Texture purchase30CoinsHighlight;

    private Texture purchase50Coins;
    private Texture purchase50CoinsHighlight;

    private Texture purchase80Coins;
    private Texture purchase80CoinsHighlight;

    private Texture purchase100Coins;
    private Texture purchase100CoinsHighlight;

    private Texture purchaseButtonHighlight;
    private Texture purchasedButton;

    private Texture exitButton;
    private Texture exitButtonHighlight;
    private Texture playButton;
    private Texture playButtonHighlight;

    private final int BUTTON_HEIGHT = 43; // change this
    private final int BUTTON_WIDTH = 145;
    private OrthographicCamera camera;
    private FitViewport viewport;

    private boolean item1Purcahsed = false;
    private boolean item2Purcahsed = false;
    private boolean item3Purcahsed = false;
    private boolean item4Purcahsed = false;
    private boolean item5Purcahsed = false;

    /**
     * Menu screens should have the same width and height of the camera.
     */
    final int GAME_WORLD_WIDTH = 960;
	final int GAME_WORLD_HEIGHT = 720;

    Screen previousScreen;

    
    public StoreScreen(Screen previousScreen){

        this.previousScreen = previousScreen;

        backgroundImage = new Sprite(new Texture("Backgrounds/store-frame.png"));
        backgroundImage.setX(0);
		backgroundImage.setY(0);
        batch = new SpriteBatch();

        purchase10Coins = new Texture("GUI/Store/10-coins.png");
        purchase10CoinsHighlight = new Texture("GUI/Store/10-coins-highlight.png");

        purchase30Coins = new Texture("GUI/Store/30-coins.png");
        purchase30CoinsHighlight = new Texture("GUI/Store/30-coins-highlight.png");

        purchase50Coins = new Texture("GUI/Store/50-coins.png");
        purchase50CoinsHighlight = new Texture("GUI/Store/50-coins-highlight.png");

        purchase80Coins = new Texture("GUI/Store/80-coins.png");
        purchase80CoinsHighlight = new Texture("GUI/Store/80-coins-highlight.png");

        purchase100Coins = new Texture("GUI/Store/100-coins.png");
        purchase100CoinsHighlight = new Texture("GUI/Store/100-coins-highlight.png");

        purchasedButton = new Texture("GUI/purchased.png");

        playButton = new Texture("GUI/Play-game.png");
        playButtonHighlight = new Texture("GUI/Play-game-highlight.png");


        //The stuff below is not needed in here and works without it
        camera = new OrthographicCamera();
        viewport = new FitViewport(960 , 720, camera);
        viewport.apply();
        camera.position.set(GAME_WORLD_WIDTH/2,GAME_WORLD_HEIGHT/2,0);

    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(0, 0, 0, 0);//red background



		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
        backgroundImage.draw(batch);

        /**
         * button drawing, hover detection and click detection...
         * TODO : very artistic.
         * 
         */

         int centerX = 10;//deleteme, just putting this here cause fuck messing with the other buttons.


        Vector3 mousePos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
        viewport.unproject(mousePos);

        //Play button
        if ((mousePos.x > 177 && mousePos.x < (177 + BUTTON_WIDTH))&&((mousePos.y > 130) && (mousePos.y < (130 + BUTTON_HEIGHT)))){
            System.out.println("test");
            batch.draw(playButtonHighlight,177,130);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(previousScreen);
            }
        }else{
            batch.draw(playButton,177,130);
        }


        /*// Exit toggle
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            System.out.println("test");
            batch.draw(exitButtonHighlight,336,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level("NewLevel"));
            }
        }else{
            batch.draw(exitButton,336,106);
        }*/

        // Item 1
        if (item1Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(purchase10CoinsHighlight,570,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchase10Coins,570,464);
            }
        }


        // Item 2
        if (item2Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(purchase30CoinsHighlight,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchase30Coins,570,406);
            }
        }

        // Item 3
        if (item3Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(purchase50CoinsHighlight,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchase50Coins,570,350);
            }
        }



        // Item 4
        if (item4Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                System.out.println("test");
                batch.draw(purchase80CoinsHighlight,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchase80Coins,570,294);
            }
        }



        // Item 5
        if (item5Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                System.out.println("test");
                batch.draw(purchase100CoinsHighlight,629,490);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchase100Coins,570,235);
            }
        }



        // //Level creator button
        // if ((Gdx.input.getX()< centerX + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 300 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>300)){
        //     batch.draw(levelCreatorHighlight,centerX,300);
        //     if(Gdx.input.isTouched()){
        //         ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
        //     }
        // }else{
        //     batch.draw(levelCreatorButton,centerX,300);
        // }



        // //EXit button
        // if ((Gdx.input.getX()< centerX + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 100 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>100)){
        //     batch.draw(exitButtonHighlight,centerX,100);
        //     if(Gdx.input.isTouched()){
        //         Gdx.app.exit();
        //     }
        // }else{
        //     batch.draw(exitButton,centerX,100);
        // }

        batch.end();

    }


    @Override
    public void resize(int width, int height){
        viewport.update(width,height);
    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }


    @Override
    public void hide(){

    }

    @Override
    public void dispose(){

    }
}
