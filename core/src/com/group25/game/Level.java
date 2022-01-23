package com.group25.game;

/**
 * LibGDX Imports....
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Other Imports
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Level implements Screen{
    private SpriteBatch batch;
	private SpriteBatch UIElements;
	private Sprite img;
	private Sprite backgroundPicture;
	private Sprite UiBorder;
	private OrthographicCamera camera;
	private Player character;
	private FitViewport viewport;
	//TEMP DELETEME
	private Slime slime;

	ArrayList<GameEntity> trees = new ArrayList<GameEntity>(); // Create an ArrayList object
	ArrayList<Enemy> enemies = new ArrayList<Enemy>(); // Create an ArrayList object

	final int GAME_WORLD_WIDTH = 1240;
	final int GAME_WORLD_HEIGHT = 1240;
	
	public Level() {
		batch = new SpriteBatch();
		UIElements = new SpriteBatch();
		img = new Sprite(new Texture("character.png"));
		backgroundPicture = new Sprite(new Texture("tempBackground.jpg"));
		backgroundPicture.setSize(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);
		UiBorder = new Sprite(new Texture("GUI/border.png"));
		camera = new OrthographicCamera();
		viewport = new FitViewport(840, 563,camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2,GAME_WORLD_HEIGHT/2,0);

		loadLevel("TestLevel");
		// TODO : this needs to be fixed haha
		//"testlevel" would actually be anything that is parsed into the level constructor.
		//so if "level1" was given to Level.java, then it would attempt to find the txt file containing level details 
		//for level 1.
		//For a test, this is fine.
		
		character = new Player((int)GAME_WORLD_WIDTH/2-80,(int)GAME_WORLD_HEIGHT/2-80,64,64,100,img,(float)1.5);//probably temp, just getting used to libgdx

	}

	public void loadLevel(String levelName){
		//unload the previous level if needed... TODO : Not done...
		//would just be an emptying of the arraylists, with the appropriate dispose()?
		//need to understand dispose() better...
		try{
			//load file into levelInfo
			Scanner levelInfo = new Scanner(new File(Gdx.files.internal("Levels/"+levelName+"/level.txt")+""));
			//Scanner allows us to go line by line in the file with.nextLine()
			String line = levelInfo.nextLine();
			//make sure its not end of file
			while (levelInfo.hasNextLine()) {
				//line = line.replace("    ","");
				if(line.equals("DEFAULT PLAYER POSITIONS:")){
					// TODO : 
				}else if(line.equals("GAME ENTITY:")){
					//list of arguments needed to make the GameEntity
					ArrayList<String> args = new ArrayList<String>();
					for(int i=0;i<6;i++){
						//populate the arguments arraylist.
						String s = levelInfo.nextLine();
						args.add(s.substring(s.indexOf(":")+2));
					}
					
					//gotta do some mad type changing
					trees.add(new GameEntity(Float.parseFloat(args.get(0)),
												Float.parseFloat(args.get(1)),
												Integer.parseInt(args.get(4)), 
												Integer.parseInt(args.get(5)), 
												new Sprite(new Texture(args.get(2))), 
												Integer.parseInt(args.get(3))));

				}else if(line.equals("SLIME:")){
					// TODO : enemy might not be final. 
					ArrayList<String> args = new ArrayList<String>();
					for(int i=0;i<7;i++){
						//populate the arguments arraylist.
						String s = levelInfo.nextLine();
						args.add(s.substring(s.indexOf(":")+2));
					}

					enemies.add(new Slime(Float.parseFloat(args.get(0)),
											Float.parseFloat(args.get(1)),
											Integer.parseInt(args.get(5)),
											Integer.parseInt(args.get(6)),
											Integer.parseInt(args.get(2)),
											new Sprite(new Texture(args.get(3))),
											Float.parseFloat(args.get(4))));
					//TEMP DELETEME
					slime = (Slime) enemies.get(0);
				
				}
				line = levelInfo.nextLine();
			}
		} catch(FileNotFoundException fileNotFoundException){
			System.out.println("file "+Gdx.files.internal("Levels/"+levelName+"/level.txt")+ " not found!");
		}
	}
	
	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}

	public boolean checkForCollision(char axis, float coordinate){
		//check for map boundaries
		//game world is square
		if(coordinate < GAME_WORLD_WIDTH-character.width && coordinate > 0){
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

		for(int i=0;i<trees.size();i++){
			batch.draw(trees.get(i).getSprite(),trees.get(i).getX(),trees.get(i).getY());
		}

		for(int i=0;i<enemies.size();i++){
			batch.draw(enemies.get(i).getSprite(),enemies.get(i).getX(),enemies.get(i).getY());
		}

		batch.draw(character.getSprite(), character.x, character.y);

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

		character.update();
		
		slime.update();

		//If person enters slimes territory
		if(slime instanceof Enemy){
			if(((Enemy) slime).getAlertArea().contains(character.getHitbox())){
				//Slime should charge the mf
				//determine Y
				if(slime.getY()<character.getY()){
					//go down
					slime.setY(slime.getY()+slime.getSpeed());
				}

				if(slime.getY()>character.getY()){
					//go down
					slime.setY(slime.getY()-slime.getSpeed());
				}
			}
		}

		batch.end();

		/**
		 * Drawing UI
		 */

		 UIElements.begin();

		 UiBorder.draw(UIElements);

		 UIElements.end();
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

    /**
	 * Pause is ran the window is minimized and just before dispose() when exiting game
	 */
	@Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }
    
	/**
	 * Resume is when window is no longer minimised.
	 */
	@Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }
}