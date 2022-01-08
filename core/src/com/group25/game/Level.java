package com.group25.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level implements Screen{
    private SpriteBatch batch;
	private Sprite img;
	private Sprite backgroundPicture;
	private OrthographicCamera camera;
	private Player character;
	private FitViewport viewport;

	final int GAME_WORLD_WIDTH = 1536;
	final int GAME_WORLD_HEIGHT = 1536;
	
	public Level() {
		batch = new SpriteBatch();
		img = new Sprite(new Texture("badlogic.jpg"));
		backgroundPicture = new Sprite(new Texture("tempBackground.jpg"));
		backgroundPicture.setSize(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);
		camera = new OrthographicCamera();
		viewport = new FitViewport(960, 720,camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2,GAME_WORLD_HEIGHT/2,0);

		//img.setSize(40,80);
		character = new Player((int)GAME_WORLD_WIDTH/2-80,(int)GAME_WORLD_HEIGHT/2-80,256,256,100,img,2);//probably temp, just getting used to libgdx
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}

	public boolean checkForCollision(char axis, float coordinate){
		//check for map boundaries
		//1280 because length of map-size of character
		if(coordinate < 1280 && coordinate > 0){
			//then check if theres an object(Thing) in the way and if its collidable?
			return true;
		}

		return false;
	}
	
	@Override
	public void render (float delta) {
		ScreenUtils.clear(1, 0, 0, 1);//red background
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundPicture.draw(batch);
		batch.draw(img, character.x, character.y);
		//for attack and shit u wanna do isKeyJustPressed rather than isKeyPressed
		if(Gdx.input.isKeyPressed(Keys.W)){
			if(checkForCollision('y',character.y + character.speed)){
				character.y = character.y + character.speed;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			if(checkForCollision('y',character.y - character.speed)){
				character.y = character.y - character.speed;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			if(checkForCollision('x',character.x - character.speed)){
				character.x = character.x - character.speed;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			if(checkForCollision('x',character.x + character.speed)){
				character.x = character.x + character.speed;
			}
		}

		/**
		 * have camera always follow the player.
		 */
		if(camera.position.x-(character.getWidth()/2) > character.getX()){
			camera.translate(-((camera.position.x-(character.getWidth()/2) - character.getX())/25),0);
		}else if(camera.position.x-(character.getWidth()/2) < character.getX()){
			camera.translate(-((camera.position.x-(character.getWidth()/2) - character.getX())/25),0);
		}
		if(camera.position.y-(character.getHeight()/2) > character.getY()){
			camera.translate(0,-((camera.position.y-(character.getHeight()/2) - character.getY())/25));
		}else if(camera.position.y-(character.getHeight()/2) < character.getY()){
			camera.translate(0,-((camera.position.y-(character.getHeight()/2) - character.getY())/25));
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.getTexture().dispose();
		backgroundPicture.getTexture().dispose();
	}

    @Override
    public void show() {
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

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }
}