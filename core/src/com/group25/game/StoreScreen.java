package com.group25.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class StoreScreen implements Screen{

    private SpriteBatch batch;
    private Sprite backgroundImage;
    private SpriteBatch UIElements;

    private Texture purchaseButton;
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
    
    public StoreScreen(){

        backgroundImage = new Sprite(new Texture("Backgrounds/store-frame.png"));
        backgroundImage.setX(0);
		backgroundImage.setY(0);
        batch = new SpriteBatch();

        purchaseButton = new Texture("GUI/Complete.png");
        purchaseButtonHighlight = new Texture("GUI/Complete-highlight.png");
        purchasedButton = new Texture("GUI/Locked.png");

        exitButton = new Texture("GUI/Create-level.png");
        exitButtonHighlight = new Texture("GUI/Create-level-highlight.png");

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
         */
        int centerX = 407;//centerX is the x value to place
        //buttons at center of map




        //Play button
        if ((Gdx.input.getX() > 177 && Gdx.input.getX() < 177 + BUTTON_WIDTH) && (Gdx.input.getY() < 106 && Gdx.input.getY() > 106 + BUTTON_HEIGHT)){
            System.out.println("test");
            batch.draw(playButtonHighlight,177,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level());
            }
        }else{
            batch.draw(playButton,177,106);
        }


        // Exit toggle
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            System.out.println("test");
            batch.draw(exitButtonHighlight,336,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level());
            }
        }else{
            batch.draw(exitButton,336,106);
        }

        // Item 1
        if (item1Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(purchaseButtonHighlight,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchaseButton,629,473);
            }
        }


        // Item 2
        if (item2Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(purchaseButtonHighlight,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchaseButton,629,409);
            }
        }

        // Item 3
        if (item3Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(purchaseButtonHighlight,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchaseButton,629,342);
            }
        }



        // Item 4
        if (item4Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                System.out.println("test");
                batch.draw(purchaseButtonHighlight,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchaseButton,629,276);
            }
        }



        // Item 5
        if (item5Purcahsed == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                System.out.println("test");
                batch.draw(purchaseButtonHighlight,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(purchaseButton,629,212);
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
