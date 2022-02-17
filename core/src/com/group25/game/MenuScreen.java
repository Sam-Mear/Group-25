package com.group25.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen implements Screen{

    private Music menuMusic;

    private SpriteBatch batch;
    private Sprite backgroundImage;
    private SpriteBatch UIElements;

    private Texture levelCompleteButton;
    private Texture levelCompleteButtonHighlighted;
    private Texture levelLockedButton;

    private Texture playGameButton;
    private Texture playGameButtonHighlighted;

    private Texture soundToggleOn;
    private Texture soundToggleOff;

    private Texture leaderboardButton;
    private Texture leaderboardButtonHighlighted;

    private Texture createLevelButton;
    private Texture createLevelButtonHighlighted;






    private Texture playButton;
    private Texture playButtonHighlight;
    private Texture levelCreatorButton;
    private Texture levelCreatorHighlight;
    private Texture exitButton;
    private Texture exitButtonHighlight;
    private final int BUTTON_HEIGHT = 43; // change this
    private final int BUTTON_WIDTH = 145;
    private OrthographicCamera camera;
    private FitViewport viewport;

    /**
     * Menu screens should have the same width and height of the camera.
     */
    final int GAME_WORLD_WIDTH = 960;
	final int GAME_WORLD_HEIGHT = 720;
    
    public MenuScreen(){
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Steventhedreamer - The bandit's wagon.mp3"));
        backgroundImage = new Sprite(new Texture("Backgrounds/menu-background.png"));
        backgroundImage.setX(0);
		backgroundImage.setY(0);
        //menuMusic.setLooping(true);
        menuMusic.play();
        batch = new SpriteBatch();

        levelCompleteButton = new Texture("GUI/Complete.png");
        levelCompleteButtonHighlighted = new Texture("GUI/Complete-highlight.png");
        levelLockedButton = new Texture("GUI/Locked.png");

        createLevelButton = new Texture("GUI/Create-level.png");
        createLevelButtonHighlighted = new Texture("GUI/Create-level-highlight.png");

        playGameButton = new Texture("GUI/Play-game.png");
        playGameButtonHighlighted = new Texture("GUI/Play-game-highlight.png"); 

        soundToggleOff = new Texture("GUI/Sound-off.png");
        soundToggleOn = new Texture("GUI/Sound-on.png");

        leaderboardButton = new Texture("GUI/Leaderboard.png");
        leaderboardButtonHighlighted = new Texture("GUI/Leaderboard-highlight.png");








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
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(playGameButtonHighlighted,177,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level());
            }
        }else{
            batch.draw(playGameButton,177,106);
        }


        // Sound toggle
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(soundToggleOn,336,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level());
            }
        }else{
            batch.draw(soundToggleOff,336,106);
        }

        // Leaderboard
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(leaderboardButtonHighlighted,490,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level());
            }
        }else{
            batch.draw(leaderboardButton,490,106);
        }

        // Create Level
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(createLevelButtonHighlighted,647,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
            }
        }else{
            batch.draw(createLevelButton,647,106);
        }







        // Level 1
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(levelCompleteButtonHighlighted,629,470);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
            }
        }else{
            batch.draw(levelCompleteButton,629,473);
        }


        // Level 2
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(levelCompleteButtonHighlighted,629,470);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
            }
        }else{
            batch.draw(levelCompleteButton,629,409);
        }


        // Level 3
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(levelCompleteButtonHighlighted,629,470);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
            }
        }else{
            batch.draw(levelCompleteButton,629,342);
        }



        // Level 4
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(levelCompleteButtonHighlighted,629,470);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
            }
        }else{
            batch.draw(levelCompleteButton,629,276);
        }



        // Level 5
        if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
            batch.draw(levelCompleteButtonHighlighted,629,470);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
            }
        }else{
            batch.draw(levelCompleteButton,629,212);
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
