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
import com.badlogic.gdx.math.Rectangle;
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
	private Sprite hearts_8;
	private Sprite hearts_7;
	private Sprite hearts_6;
	private Sprite hearts_5;
	private Sprite hearts_4;
	private Sprite hearts_3;
	private Sprite hearts_2;
	private Sprite hearts_1;
	private Sprite hearts_0;

	private Sprite mana_10;
	private Sprite mana_9;	
	private Sprite mana_8;
	private Sprite mana_7;
	private Sprite mana_6;
	private Sprite mana_5;
	private Sprite mana_4;
	private Sprite mana_3;
	private Sprite mana_2;
	private Sprite mana_1;
	private Sprite mana_0;
	

	private Drop coin;

	//To be deleated? 
	private EnviromentAnimated camp;
	private EnemySpawner slimeSpawner;

	ArrayList<Enviroment> trees = new ArrayList<Enviroment>(); // Create an ArrayList object
	ArrayList<EnviromentAnimated> animatedEnviroment = new ArrayList<EnviromentAnimated>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>(); // Create an ArrayList object
	ArrayList<Rectangle> enviromentHitboxes = new ArrayList<Rectangle>();// list of enviroment hitboxes, read from level.txt
	ArrayList<Teleport> teleports = new ArrayList<Teleport>();
	ArrayList<Creature> targets = new ArrayList<>(); // arrayList where all possible targets are stored (including enemies and main character and/or object if there will be any)
	ArrayList<RangeAttack> projectiles;



	int GAME_WORLD_WIDTH = 1778;
	int GAME_WORLD_HEIGHT = 1334;
	
	public Level(String levelName) {
		batch = new SpriteBatch();
		UIElements = new SpriteBatch();
		img = new Sprite(new Texture("Main_character_sprite.png"));
		hearts_8 = new Sprite(new Texture("health_sprites/hearts-8.png"));
		hearts_7 = new Sprite( new Texture("health_sprites/hearts-7.png"));
		hearts_5 = new Sprite( new Texture("health_sprites/hearts-5.png"));
		hearts_4 = new Sprite( new Texture("health_sprites/hearts-4.png"));
		hearts_3 = new Sprite( new Texture("health_sprites/hearts-3.png"));
		hearts_2 = new Sprite( new Texture("health_sprites/hearts-2.png"));
		hearts_1 = new Sprite( new Texture("health_sprites/hearts-1.png"));
		hearts_6 = new Sprite(new Texture("health_sprites/hearts-6.png"));

		
		mana_10 = new Sprite(new Texture("mana_sprites/mana-10.png"));
		mana_9 = new Sprite( new Texture("mana_sprites/mana-9.png"));
		mana_8 = new Sprite(new Texture("mana_sprites/mana-8.png"));
		mana_7 = new Sprite( new Texture("mana_sprites/mana-7.png"));
		mana_6 = new Sprite(new Texture("mana_sprites/mana-6.png"));
		mana_5 = new Sprite( new Texture("mana_sprites/mana-5.png"));
		mana_4 = new Sprite( new Texture("mana_sprites/mana-4.png"));
		mana_3 = new Sprite( new Texture("mana_sprites/mana-3.png"));
		mana_2 = new Sprite( new Texture("mana_sprites/mana-2.png"));
		mana_1 = new Sprite( new Texture("mana_sprites/mana-1.png"));
		mana_0 = new Sprite( new Texture("mana_sprites/mana.png"));
		

		EnemyFactory slimeCamp = new SlimeFactory();
		camp = new EnviromentAnimated(300, 300, 130, 43, new Sprite(new Texture("GameEntity/camp-fire.png")), 4, 7);
		slimeSpawner = new EnemySpawner(300,300,130,43,camp.getSprite(),slimeCamp,10);


		UiBorder = new Sprite(new Texture("GUI/border.png"));
		UiBorder.setX(0);
		UiBorder.setY(0);

		
		heartTest = new EnviromentAnimated(500, 1003, 22, 24, new Sprite(new Texture("GameEntity/heart_animated.png")), 10, 3);
		coin = new Drop(140, 120, 16, 16, new Sprite(new Texture("GameEntity/coin_animated.png")), 5, 5,1,DropType.COIN);
		
		camera = new OrthographicCamera();
		viewport = new FitViewport(840, 563,camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2,GAME_WORLD_HEIGHT/2,0);

		character = new Player(this, (int)GAME_WORLD_WIDTH/2-80,(int)GAME_WORLD_HEIGHT/2-80,42,42,100,img,5);//probably temp, just getting used to libgdx
		character.setSpeed(1);
		targets.add(character);

		loadLevel(levelName);
		// TODO : this needs to be fixed haha
		//"testlevel" would actually be anything that is parsed into the level constructor.
		//so if "level1" was given to Level.java, then it would attempt to find the txt file containing level details 
		//for level 1.
		//For a test, this is fine.

		allertArea = new Sprite(new Texture(("Slime_Test_Area.png")));

		//System.out.println("Speeddddd: "+character.getSpeed());
	}

	public void loadLevel(String levelName){
		//unload the previous level if needed...
		for(int i = trees.size(); i>0;i--){
			trees.get(i-1).dispose();
			trees.remove(i-1);
		}
		for(int i = animatedEnviroment.size(); i>0;i--){
			animatedEnviroment.get(i-1).dispose();
			animatedEnviroment.remove(i-1);
		}
		for(int i = enviromentHitboxes.size(); i>0;i--){
			enviromentHitboxes.remove(i-1);
		}
		for(int i = enemies.size(); i>0;i--){
			enemies.get(i-1).dispose();
			enemies.remove(i-1);
		}
		for(int i = teleports.size(); i>0;i--){
			teleports.remove(i-1);
		}
		try{
			//load file into levelInfo
			Scanner levelInfo = new Scanner(new File(Gdx.files.internal("Levels/"+levelName+".txt")+""));
			//Scanner allows us to go line by line in the file with.nextLine()
			String line;
			//make sure its not end of file
			while (levelInfo.hasNextLine()) {
				line = levelInfo.nextLine();
				//line = line.replace("    ","");
				if(line.equals("MAP SIZE:")){
					line = levelInfo.nextLine();
					if(line.contains("width: ")){
						GAME_WORLD_WIDTH = Integer.parseInt(line.split(": ")[1]);
					}
					line = levelInfo.nextLine();
					if(line.contains("height: ")){
						GAME_WORLD_HEIGHT = Integer.parseInt(line.split(": ")[1]);
					}
				}else if(line.contains("DEFAULT PLAYER POSITIONS")){
					line = levelInfo.nextLine();
					int x = Integer.parseInt(line.split(": ")[1]);
					line = levelInfo.nextLine();
					int y = Integer.parseInt(line.split(": ")[1]);
					character.setX(x);
					character.setY(y);
					camera.position.set(x,y,0);
				}else if(line.contains("BACKGROUND:")){
					backgroundPicture = new Sprite(new Texture("Backgrounds/"+line.split(": ")[1]));
					backgroundPicture.setSize(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);
				}else if(line.equals("HITBOX:")){
					ArrayList<String> args = new ArrayList<String>();
					for(int i=0;i<4;i++){
						//populate the arguments arraylist.
						String s = levelInfo.nextLine();
						args.add(s.substring(s.indexOf(":")+2));
					}
					enviromentHitboxes.add(new Rectangle(Integer.parseInt(args.get(0)),
																							Integer.parseInt(args.get(1)),
																							Integer.parseInt(args.get(2)),
																							Integer.parseInt(args.get(3))));
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
					slimeCamp.getNewMonster(this, 50,50,100,100,50,slime.getSprite(),1);

				}else if(line.equals("GAME ENTITY ANIMATED:")){
					//list of arguments needed to make the GameEntity
					ArrayList<String> args = new ArrayList<String>();
					for(int i=0;i<7;i++){
						//populate the arguments arraylist.
						String s = levelInfo.nextLine();
						args.add(s.substring(s.indexOf(":")+2));
					}
					//gotta do some mad type changing
					animatedEnviroment.add(new EnviromentAnimated(Float.parseFloat(args.get(0)),
												Float.parseFloat(args.get(1)),
												Integer.parseInt(args.get(2)), 
												Integer.parseInt(args.get(3)), 
												new Sprite(new Texture(args.get(4))),
												Integer.parseInt(args.get(5)), 
												Integer.parseInt(args.get(6))));


				}else if(line.equals("HITBOX SPECIAL:")){
					ArrayList<String> args = new ArrayList<String>();
					for(int i=0;i<5;i++){
						//populate the arguments arraylist.
						String s = levelInfo.nextLine();
						args.add(s.substring(s.indexOf(":")+2));
					}
					teleports.add(new Teleport(Integer.parseInt(args.get(0)),
																			Integer.parseInt(args.get(1)),
																			Integer.parseInt(args.get(2)),
																			Integer.parseInt(args.get(3)),
																			args.get(4)));
				}
				
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

	public String checkForTeleport(){
		for(int i = 0;i<teleports.size();i++){
			//bottom left
			if(teleports.get(i).contains(character.getX(), character.getY())){
				return teleports.get(i).getLevelName();
			}
			//top right
			if(teleports.get(i).contains(character.getX()+character.getWidth(), character.getY()+(character.getHeight()/2))){
				return teleports.get(i).getLevelName();
			}
			//top left
			if(teleports.get(i).contains(character.getX(), character.getY()+(character.getHeight()/2))){
				return teleports.get(i).getLevelName();
			}
			//bottom right
			if(teleports.get(i).contains(character.getX()+character.getWidth(), character.getY())){
				return teleports.get(i).getLevelName();
			}
		}
			

		return "";
	}

	public boolean checkForCollision(char axis, float coordinate){
		if(checkForTeleport() != ""){
			loadLevel(checkForTeleport());
		}
		//check for map boundaries
		//game world is square
		if(axis == 'x'){
			if(coordinate < GAME_WORLD_WIDTH-character.width && coordinate > 0){
				for(int i = 0;i<enviromentHitboxes.size();i++){
					//bottom left
					if(enviromentHitboxes.get(i).contains(coordinate, character.getY())){
						return false;
					}
					//top right
					if(enviromentHitboxes.get(i).contains(coordinate+character.getWidth(), character.getY()+(character.getHeight()/2))){
						return false;
					}
					//top left
					if(enviromentHitboxes.get(i).contains(coordinate, character.getY()+(character.getHeight()/2))){
						return false;
					}
					//bottom right
					if(enviromentHitboxes.get(i).contains(coordinate+character.getWidth(), character.getY())){
						return false;
					}
				}
				return true;
			}
		}else if(axis == 'y'){
			if(coordinate < GAME_WORLD_HEIGHT-character.height && coordinate > 0){
				for(int i = 0;i<enviromentHitboxes.size();i++){
					//bottom left
					if(enviromentHitboxes.get(i).contains(character.getX(), coordinate)){
						return false;
					}
					//top right
					if(enviromentHitboxes.get(i).contains(character.getX()+character.getWidth(), coordinate+(character.getHeight()/2))){
						return false;
					}
					//top left
					if(enviromentHitboxes.get(i).contains(character.getX(), coordinate+(character.getHeight()/2))){
						return false;
					}
					//bottom right
					if(enviromentHitboxes.get(i).contains(character.getX()+character.getWidth(), coordinate)){
						return false;
					}
				}
				return true;
			}
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
	
		batch.draw(allertArea,slime.getX()-(aWidth-slime.getWidth())/2,slime.getY()-(aHeight-slime.getHeight())/2);
		//batch.draw(slimeSpawner.getSprite(),slimeSpawner.getX(),slimeSpawner.getY());
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

/*		health x: 116	y: 83
*/
		
	
	//	batch.draw(character.getTexture(), character.getX(), character.getY());
		//batch.draw(slimeSpawner.getSprite().getTexture(),slimeSpawner.getX(),slimeSpawner.getY());

//		spawner.spawn();

		for(int i=0;i<animatedEnviroment.size();i++){
			batch.draw(animatedEnviroment.get(i).getTexture(),animatedEnviroment.get(i).getX(),animatedEnviroment.get(i).getY());
			animatedEnviroment.get(i).update();
		}
		
		batch.draw(heartTest.getTexture(),heartTest.getX(),heartTest.getY());
		coin.update();
		heartTest.update();

		batch.draw(camp.getTexture(),camp.getX(),camp.getY());
		camp.update();
		//slimeSpawner.spawnNewMonster(level, enemies, positionX, positionY, width, height, health, img, speed);

		// EnemyFactory slimeCamp = new SlimeFactory();
		// camp = new EnviromentAnimated(117, 697, 130, 43, new Sprite(new Texture("GameEntity/camp-fire.png")), 4, 3);
		// slimeSpawner = new EnemySpawner(50,50,100,100,camp.getSprite(),slimeCamp,10);


		//EnemyFactory slimeCamp = new SlimeFactory();
		//camp = new EnviromentAnimated(117, 697, 130, 43, new Sprite(new Texture("GameEntity/camp-fire.png")), 4, 3);
		//float positionX, float positionY, int width, int height, Sprite img,EnemyFactory factory, int spawnTime
		//slimeSpawner = new EnemySpawner((float)50,(float)50,100,100,5,camp.getSprite(),slimeCamp,10);
		
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
		slimeSpawner.spawnNewMonster(this, enemies,(int)slimeSpawner.getX(),(int)slimeSpawner.getY(),100,100,50,slime.getSprite(),1);


		// if(healthProcentage>90){
		// 	batch.draw(heart_8, 120, 83);
		// }

		//Would be changed into an array of all the coins
		//Coins removed would not be checked this is for testing purposes
		if(!coin.isPickedUp()){
			character.pickUp(coin);
		}
		//FOLLOW PLAYER CODE
		for(Enemy e:enemies){
			e.explore(character);
		}
		//slime.explore(character);
		//If person enters slimes territory
		//If the entire person has entered the slime territory

		batch.end();

		/**
		 * Drawing UI
		 */

		UIElements.begin();

		UiBorder.draw(UIElements);

		// UI StatusBar, 

		Sprite heart;
		Sprite mana;

		int manaProcentage = character.getMana() * 100 / character.getHealthLimit();
		int healthProcentage = character.getHealth() * 100 / character.getHealthLimit();

		if(manaProcentage == 100){
			mana = mana_10;			
		}
		else if(manaProcentage > 90){
			mana = mana_9;			
		}
		else if(manaProcentage > 80){
			mana = mana_8;
		}
		else if(manaProcentage > 70){
			mana = mana_7;		
		}	
		else if(manaProcentage > 60){
			mana = mana_6;		
		}	
		else if(manaProcentage > 50){
			mana = mana_5;		
		}	
		else if(manaProcentage > 40){
			mana = mana_4;		
		}	
		else if(manaProcentage > 30){
			mana = mana_3;		
		}	
		else if(manaProcentage >  20){
			mana = mana_2;		
		}	
		else if(manaProcentage >  10){
			mana = mana_1;		
		}else{
			mana = mana_0;
		}	
		
		UIElements.draw(mana, 20, 30);
		


		
		if(healthProcentage > 90){
			heart = hearts_8;
		}	
		else if(healthProcentage > 80){
			heart = hearts_7;
		}	
		else if(healthProcentage > 70){
			heart = hearts_6;
		}	
		else if(healthProcentage > 60){
			heart = hearts_5;
		}	
		else if(healthProcentage > 45){
			heart = hearts_4;
		}	
		else if(healthProcentage > 30){
			heart = hearts_3;
		}	
		else if(healthProcentage >  15){
			heart = hearts_2;
		}	
		else if(healthProcentage > 0){
			heart = hearts_1;
		}	
		else{
			heart = null;
		}

		if(heart!=null)
			UIElements.draw(heart, 110, 50);

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