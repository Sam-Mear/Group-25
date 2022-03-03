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

public class Loading implements Screen{

    private Music menuMusic;
    private boolean sound;

    private SpriteBatch batch;
    private Sprite backgroundImage;
    private SpriteBatch UIElements;

    private OrthographicCamera camera;
    private FitViewport viewport;

    /**
     * Menu screens should have the same width and height of the camera.
     */
    final int GAME_WORLD_WIDTH = 840;
	final int GAME_WORLD_HEIGHT = 563;
    
    public Loading(){

        backgroundImage = new Sprite(new Texture("Backgrounds/Loading-screen.png"));
		backgroundImage.setSize(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);


        backgroundImage.setX(0);
		backgroundImage.setY(0);
        batch = new SpriteBatch();



        //The stuff below is not needed in here and works without it
        camera = new OrthographicCamera();
        viewport = new FitViewport(840, 563,camera);
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

        if(Gdx.input.isTouched()){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
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