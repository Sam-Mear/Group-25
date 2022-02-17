package com.group25.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen implements Screen{

    private Music menuMusic;

    private SpriteBatch batch;
    private Texture playButton;
    private Texture playButtonHighlight;
    private Texture levelCreatorButton;
    private Texture levelCreatorHighlight;
    private Texture exitButton;
    private Texture exitButtonHighlight;
    private final int BUTTON_HEIGHT = 200; // change this
    private final int BUTTON_WIDTH = 400;
    private OrthographicCamera camera;
    private FitViewport viewport;

    /**
     * Menu screens should have the same width and height of the camera.
     */
    final int GAME_WORLD_WIDTH = 960;
	final int GAME_WORLD_HEIGHT = 720;
    
    public MenuScreen(){
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Steventhedreamer - The bandit's wagon.mp3"));
        //menuMusic.setLooping(true);
        menuMusic.play();
        batch = new SpriteBatch();
        playButton = new Texture("GUI/tempPlayButton.png");
        playButtonHighlight = new Texture("GUI/tempPlayButtonHighlight.png");


        levelCreatorButton = new Texture("GUI/tempPlayButton.png");
        levelCreatorHighlight = new Texture("GUI/tempPlayButtonHighlight.png");

        exitButton = new Texture("GUI/tempExitButton.png");
        exitButtonHighlight = new Texture("GUI/tempExitButtonHighlight.png");
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
        ScreenUtils.clear(1, 0, 0, 1);//red background
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

        /**
         * button drawing, hover detection and click detection...
         * TODO : very artistic.
         */
        int centerX = (GAME_WORLD_WIDTH/2)-(BUTTON_WIDTH/2);//centerX is the x value to place
        //buttons at center of map

        //Play button
        if ((Gdx.input.getX()< centerX + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 500 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>500)){
            batch.draw(playButtonHighlight,centerX,500);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level());
            }
        }else{
            batch.draw(playButton,centerX,500);
        }


        //Level creator button
        if ((Gdx.input.getX()< centerX + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 300 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>300)){
            batch.draw(levelCreatorHighlight,centerX,300);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
            }
        }else{
            batch.draw(levelCreatorButton,centerX,300);
        }



        //EXit button
        if ((Gdx.input.getX()< centerX + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 100 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>100)){
            batch.draw(exitButtonHighlight,centerX,100);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }else{
            batch.draw(exitButton,centerX,100);
        }

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
