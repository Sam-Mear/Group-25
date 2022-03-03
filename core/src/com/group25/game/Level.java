package com.group25.game;

/**
 * LibGDX Imports....
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import org.w3c.dom.ranges.Range;

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
	private Sprite UiStatusBar;
	private Sprite UiInventory;
	private Sprite UiTutorial;
	private OrthographicCamera camera;
	private Player character;
	private FitViewport viewport;
	//TEMP DELETEME
	private Slime slime;
	private Sprite allertArea;
	private EnviromentAnimated coinTest;
	private EnviromentAnimated heartTest;
	private EnviromentAnimated waterfallTest3;

	private Drop coin;
	private Sprite coinSprite;

	//To be deleted
	private EnemySpawner spawner;

	ArrayList<Enviroment> trees = new ArrayList<Enviroment>(); // Create an ArrayList object
	ArrayList<Enemy> enemies = new ArrayList<Enemy>(); // Create an ArrayList object
	ArrayList<Creature> targets = new ArrayList<>(); // arrayList where all possible targets are stored (including enemies and main character and/or object if there will be any)
	ArrayList<RangeAttack> projectiles;


	final int GAME_WORLD_WIDTH = 1778;
	final int GAME_WORLD_HEIGHT = 1334;
	
	public Level() {
		batch = new SpriteBatch();
		UIElements = new SpriteBatch();
		img = new Sprite(new Texture("Main_character_sprite.png"));

		coinSprite = new Sprite((new Texture("Coin.png")));

		UiBorder = new Sprite(new Texture("GUI/border.png"));
		UiBorder.setX(0);
		UiBorder.setY(0);
		UiStatusBar = new Sprite(new Texture("GUI/status-bar-temp.png"));
		UiInventory = new Sprite(new Texture("GUI/inventory.png"));
		UiStatusBar.setX(20);
		UiStatusBar.setY(20);

		UiInventory.setX(20);
		UiInventory.setY(150);

		heartTest = new EnviromentAnimated(500, 1003, 22, 24, new Sprite(new Texture("GameEntity/heart_animated.png")), 10, 3);
		waterfallTest3 = new EnviromentAnimated(835, 225, 16, 46, new Sprite(new Texture("GameEntity/waterfall_animated.png")), 9, 5);
		
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

		character = new Player(this, (int)GAME_WORLD_WIDTH/2-80,(int)GAME_WORLD_HEIGHT/2-80,42,28,100,img,5);//probably temp, just getting used to libgdx
		character.setSpeed(1);
		targets.add(character);

		allertArea = new Sprite(new Texture(("Slime_Test_Area.png")));

		//System.out.println("Speeddddd: "+character.getSpeed());
	}

	public void loadLevel(String levelName){

		coin = new Drop(140, 120, 16, 16, new Sprite(new Texture("GameEntity/coin_animated.png")), 5, 5,1,DropType.COIN);
		//unload the previous level if needed... TODO : Not done...
		//would just be an emptying of the arraylists, with the appropriate dispose()?
		//need to understand dispose() better...
		try{
			//load file into levelInfo
			Scanner levelInfo = new Scanner(new File(Gdx.files.internal("Levels/"+levelName+".txt")+""));
			//Scanner allows us to go line by line in the file with.nextLine()
			String line = levelInfo.nextLine();
			//make sure its not end of file
			while (levelInfo.hasNextLine()) {
				//line = line.replace("    ","");
				if(line.contains("BACKGROUND:")){
					System.out.println("aaaaaaaaa");
					backgroundPicture = new Sprite(new Texture("Backgrounds/"+line.split(": ")[1]));
					backgroundPicture.setSize(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);
				}else if(line.equals("GAME ENTITY:")){
					//list of arguments needed to make the GameEntity
					ArrayList<String> args = new ArrayList<String>();
					for(int i=0;i<6;i++){
						//populate the arguments arraylist.
						String s = levelInfo.nextLine();
						args.add(s.substring(s.indexOf(":")+2));
					}
					//gotta do some mad type changing
					trees.add(new Enviroment(Float.parseFloat(args.get(0)),
												Float.parseFloat(args.get(1)),
												Integer.parseInt(args.get(2)), 
												Integer.parseInt(args.get(3)), 
												new Sprite(new Texture(args.get(4)))));
												//new Sprite(new Texture(args.get(4))), 
												//Integer.parseInt(args.get(5))));


				}else if(line.equals("SLIME:")){
					// TODO : enemy might not be final. 
					ArrayList<String> args = new ArrayList<String>();
					for(int i=0;i<7;i++){
						//populate the arguments arraylist.
						String s = levelInfo.nextLine();
						args.add(s.substring(s.indexOf(":")+2));
					}


					// ADDED 3 MORE VALUESl RANGE, DAMAGE AND ATTACK SPEED
					enemies.add(new Slime(	this, 
											Float.parseFloat(args.get(0)),
											Float.parseFloat(args.get(1)),
											Integer.parseInt(args.get(2)),
											Integer.parseInt(args.get(3)),
											Integer.parseInt(args.get(4)),
											new Sprite(new Texture(args.get(5))),
											Float.parseFloat(args.get(6)), 50, 5, 25));
					//TEMP DELETEME
					slime = (Slime) enemies.get(0);
					addEnemy(slime);
					EnemyFactory slimeCamp = new SlimeFactory();
					slimeCamp.getNewMonster(50,50,100,slime.getSprite(),1);

				}
				line = levelInfo.nextLine();
			}
		} catch(FileNotFoundException fileNotFoundException){
			System.out.println("file "+Gdx.files.internal("Levels/"+levelName+"/level.txt")+ " not found!");
		}


		slime.setHealth(100);
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

	public void addEnemy(Enemy enemy){
		System.out.println("enemy added");
		targets.add(enemy);
	}


	public void addProjectile(RangeAttack range){
		projectiles.add(range);
	}

	public Creature getEnemy(int xRange, int yRange, Creature attacker){
		for(int i=0; i<enemies.size(); i++){
		Creature potential;
		if(attacker instanceof Enemy){
			potential = character;
		}else{
			if(enemies.get(i).alive()){
				potential = enemies.get(i);
			}
			else{
				break;
			}
		}
				if(potential != attacker){
					if(attacker.getDirection() == "right"){
						if(potential.getX() - attacker.getX() <= xRange)
							if(potential.getX() - attacker.getX() >= 0)
								if(potential.getY() - attacker.getY() <= yRange/2)
									if(potential.getY() - attacker.getY() >= 0)
										return potential;
							
					}
					if(attacker.getDirection() == "left"){
						if(attacker.getX() - potential.getX() <= xRange)
							if(attacker.getX() - potential.getX() >= 0)
								if(attacker.getY() - potential.getY() <= yRange/2)
									if(attacker.getY() - potential.getY() >= 0)
										return potential;
								
					}
					if(attacker.getDirection() == "down"){
						if(attacker.getY() - potential.getY() <= xRange)
							if(attacker.getY() - potential.getY() >= 0)
								if(attacker.getX() - potential.getX() <= yRange/2)
									if(attacker.getX() - potential.getX() >= 0)
										return potential;
								
					}
					if(attacker.getDirection() == "up"){
						if(potential.getY() - attacker.getY() <= xRange)
							if(potential.getY() - attacker.getY() >= 0)
								if(potential.getX() - attacker.getX() <= yRange/2)
									if(potential.getX() - attacker.getX() >= 0)
										return potential;
								
					}
				}
			
		}
		return null;
	}



	@Override
	public void render (float delta) {
		ScreenUtils.clear(0, 0, 0, 1);//red background

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundPicture.draw(batch);

		projectiles = character.getProjectiles();

		for(int i=0; i<projectiles.size(); i++){
			projectiles.get(i).update();
			batch.draw(projectiles.get(i).getTexture(), projectiles.get(i).getX(), projectiles.get(i).getY());
		}




		int aWidth = 200;
		int aHeight = 200;		
	

		if(!coin.isPickedUp()){
			batch.draw(coin.getTexture(), coin.getX(),coin.getY());
		}

		for(int i=0;i<trees.size();i++){
			batch.draw(trees.get(i).getSprite(),trees.get(i).getX(),trees.get(i).getY());
		}

		for(int i=0;i<targets.size();i++){

			if(targets.get(i).alive()){
				if(targets.get(i) == character){
					batch.draw(character.getTexture(), character.getX(), character.getY());
				}else{
					if(targets.get(i) instanceof Slime){
						targets.get(i).update();
						batch.draw(((Slime) targets.get(i)).getTexture(), targets.get(i).getX(), targets.get(i).getY());
					}else{
						batch.draw(targets.get(i).getSprite(),targets.get(i).getX(),targets.get(i).getY());
					}
					
				}

				Creature currentC = targets.get(i);
				for(int j=0; j<projectiles.size(); j++){
					RangeAttack currentR = projectiles.get(j);

					if(Math.abs(currentR.getSize()/2 + currentR.getY() - currentC.getY()) <= currentC.getSize()/2)
						if(Math.abs(currentR.getSize()/2 + currentR.getX() - currentC.getX()) <= currentC.getSize()/2){
							targets.get(i).setHealth(targets.get(i).getHealth() - 10);
							if(currentC instanceof Slime){
								((Slime)currentC).setAttacked();
							}
							projectiles.get(j).setAlive(false);
						}		
				}
				// System.out.println(targets.get(i).getHealth());
				// batch.draw(allertArea,enemies.get(i).getX()-(aWidth-enemies.get(i).getWidth())/2,enemies.get(i).getY()-(aHeight-enemies.get(i).getHeight())/2);
			}
			
			
		}
		character.checkKeysPressed();

		// batch.draw(spawner.getSprite().getTexture(),spawner.getX(),spawner.getY());

//		spawner.spawn();


		batch.draw(waterfallTest3.getTexture(),352,1003);
		batch.draw(waterfallTest3.getTexture(),352+16,1003);
		batch.draw(waterfallTest3.getTexture(),waterfallTest3.getX(),waterfallTest3.getY());
		batch.draw(waterfallTest3.getTexture(),waterfallTest3.getX()+16,waterfallTest3.getY());
		batch.draw(waterfallTest3.getTexture(),waterfallTest3.getX()+32,waterfallTest3.getY());
		batch.draw(waterfallTest3.getTexture(),waterfallTest3.getX()+48,waterfallTest3.getY());
		batch.draw(waterfallTest3.getTexture(),1635,963);
		batch.draw(waterfallTest3.getTexture(),1635+16,963);
		batch.draw(waterfallTest3.getTexture(),1635+32,963);
		batch.draw(heartTest.getTexture(),heartTest.getX(),heartTest.getY());
		coin.update();
		heartTest.update();
		waterfallTest3.update();

		
		//Testing purposes
		//We want to kill a monster and then respawn them

		
		/**
		 * have camera always follow the player.
		 */

		 if(character.alive()){
			if(character.getX() + 430 < GAME_WORLD_WIDTH && character.getX() - 410 > 0){
				if(camera.position.x-(character.getWidth()/2) > character.getX()){
					camera.translate(-((camera.position.x-(character.getWidth()/2) - character.getX())/25),0);
				}else if(camera.position.x-(character.getWidth()/2) < character.getX()){
					camera.translate(-((camera.position.x-(character.getWidth()/2) - character.getX())/25),0);
				}
			}
			if(character.getY() + 256 < GAME_WORLD_HEIGHT && character.getY() -256 > 0){
				if(camera.position.y-(character.getHeight()/2) > character.getY()){
					camera.translate(0,-((camera.position.y-(character.getHeight()/2) - character.getY())/25));
				}else if(camera.position.y-(character.getHeight()/2) < character.getY()){
					camera.translate(0,-((camera.position.y-(character.getHeight()/2) - character.getY())/25));
				}
			}
		 }
		

		character.update();

		//Would be changed into an array of all the coins
		//Coins removed would not be checked this is for testing purposes
		if(!coin.isPickedUp()){
			character.pickUp(coin);
		}

		slime.explore(character);
		//If person enters slimes territory
		//If the entire person has entered the slime territory

		batch.end();

		/**
		 * Drawing UI
		 */

		UIElements.begin();

		UiBorder.draw(UIElements);
		UiStatusBar.draw(UIElements);
		UiInventory.draw(UIElements);

		// UI StatusBar, 


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