package com.group25.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen implements Screen{

    private boolean level1complete = false;
    private boolean level2complete = false;
    private boolean level3complete = false;
    private boolean level4complete = false;
    private boolean level5complete = false;

    private Music menuMusic;
    private boolean sound;

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
    private final int BUTTON_HEIGHT = 40; // change this
    private final int BUTTON_WIDTH = 128;
    private OrthographicCamera camera;
    private FitViewport viewport;

    /**
     * Menu screens should have the same width and height of the camera.
     */
    final int GAME_WORLD_WIDTH = 960;
	final int GAME_WORLD_HEIGHT = 720;
    
    public MenuScreen(){
        sound = true;
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/bg-music.mp3"));

        if (sound == true) {
            menuMusic.setLooping(true);
            menuMusic.play();
        }
        else {
            menuMusic.setLooping(false);
            menuMusic.stop();
        }

        backgroundImage = new Sprite(new Texture("Backgrounds/menu-background.png"));
        backgroundImage.setX(0);
		backgroundImage.setY(0);
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


        Vector3 mousePos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
        viewport.unproject(mousePos);


        //Play button
        //System.out.println(GAME_WORLD_HEIGHT-Gdx.input.getY)
        if ((mousePos.x > 177 && mousePos.x < (177 + BUTTON_WIDTH))&&((mousePos.y > 106) && (mousePos.y < (106 + BUTTON_HEIGHT)))){
            //System.out.println("test");
            batch.draw(playGameButtonHighlighted,177,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level("NewLevel"));
            }
        }else{
            batch.draw(playGameButton,177,106);
        }


        // Sound toggle
        if ((mousePos.x > 336 && mousePos.x < (336 + BUTTON_WIDTH))&&((mousePos.y > 106) && (mousePos.y < (106 + BUTTON_HEIGHT)))){
            System.out.println("test");
            batch.draw(soundToggleOn,336,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Level("NewLevel"));
            }
        }else{
            batch.draw(soundToggleOff,336,106);
        }

        // Create Level
        if ((mousePos.x > 490 && mousePos.x < (490 + BUTTON_WIDTH))&&((mousePos.y > 106) && (mousePos.y < (106 + BUTTON_HEIGHT)))){
            batch.draw(createLevelButtonHighlighted,490,106);
            if(Gdx.input.isTouched()){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
            }
        }else{
            batch.draw(createLevelButton,490,106);
        }




        //Level 1

        if (level1complete == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(levelCompleteButtonHighlighted,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(levelCompleteButton,629,473);
            }
        } else {
            batch.draw(levelCompleteButton,629,473);
        }
        

        if (level2complete == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(levelCompleteButtonHighlighted,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(levelCompleteButton,629,409);
            }
        }


        // Level 3
        if (level3complete == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                batch.draw(levelCompleteButtonHighlighted,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(levelCompleteButton,629,342);
            }
        }



        // Level 4
        if (level4complete == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                System.out.println("test");
                batch.draw(levelCompleteButtonHighlighted,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(levelCompleteButton,629,276);
            }
        }



        // Level 5
        if (level5complete == false){
            if ((Gdx.input.getX() < 43 + BUTTON_WIDTH && Gdx.input.getX()>centerX) && (GAME_WORLD_HEIGHT - Gdx.input.getY()< 149 + BUTTON_HEIGHT && GAME_WORLD_HEIGHT - Gdx.input.getY()>149)){
                System.out.println("test");
                batch.draw(levelCompleteButtonHighlighted,629,470);
                if(Gdx.input.isTouched()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelCreator());
                }
            }else{
                batch.draw(levelCompleteButton,629,212);
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

    private void toggleMusic(){

        if (sound == true){
            sound = false;
        }

        if (sound == false) {
            sound = true;
        }
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