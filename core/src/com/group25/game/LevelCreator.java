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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Swing/UI Imports
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Other Imports
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

public class LevelCreator extends JFrame implements Screen{
    

    private SpriteBatch batch;
	private Sprite img;
	private Sprite backgroundPicture;
	private OrthographicCamera camera;
	private FitViewport viewport;

	//button group of the radio buttons
	private ButtonGroup buttonGroup;

	ArrayList<GameEntity> trees = new ArrayList<GameEntity>(); // Create an ArrayList object
	ArrayList<ArrayList<String>> textFileOutput = new ArrayList<ArrayList<String>>(); // Create an ArrayList object

	final int GAME_WORLD_WIDTH = 1240;
	final int GAME_WORLD_HEIGHT = 1240;
	
	public LevelCreator() {

        loadLevel();
        

		batch = new SpriteBatch();
		img = new Sprite(new Texture("GameEntity/character.png"));
		backgroundPicture = new Sprite(new Texture("Backgrounds/tempBackground.jpg"));
		backgroundPicture.setSize(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);
		camera = new OrthographicCamera();
		viewport = new FitViewport(1240, 1240,camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2,GAME_WORLD_HEIGHT/2,0);


		
	}

	public ArrayList<JRadioButton> listFilesForFolder(final File folder) {
		ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				String temp = fileEntry.getName();
				if(!temp.contains(".txt")){
					buttons.add(new JRadioButton(temp,new ImageIcon(new ImageIcon(Gdx.files.internal("GameEntity/"+temp) + "").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT))));
				}
			}
		}
		return buttons;
	}

    public void loadLevel(){
		buttonGroup = new ButtonGroup();
        JFrame f = new JFrame("Enter Level Name");
        //JPopupMenu popupMenu = new JPopupMenu("Enter Level Name1");
        JTextField enterLevelName = new JTextField(10);

		final File folder = new File(Gdx.files.internal("GameEntity/") + "");
		ArrayList<JRadioButton> buttons = listFilesForFolder(folder);
		for (JRadioButton jRadioButton : buttons) {
			f.add(jRadioButton);
			buttonGroup.add(jRadioButton);
		}

        f.setSize(200,500);
		f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //popupMenu.add(enterLevelName);
        f.add(enterLevelName);
        f.setVisible(true);
    }

	public void blah(String levelName){
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

					
				
				}
				line = levelInfo.nextLine();
			}
		} catch(FileNotFoundException fileNotFoundException){
			System.out.println("file "+Gdx.files.internal("Levels/"+levelName+"/level.txt")+ " not found!");
            System.out.println("Attempting to create new file.");

		}
	}
	
	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}

	public ArrayList<String> defaultGameEntityValues(String dir, int x, int y, String sprite){
		try{
			//load file into defaultEntityInfo
			Scanner defaultEntityInfo = new Scanner(new File(Gdx.files.internal(dir)+""));
			//store contents as arraylist, each item being a line to be 
			//written to the level.txt
			ArrayList<String> gameEntity = new ArrayList<String>();
			//Scanner allows us to go line by line in the file with.nextLine()
			String line = defaultEntityInfo.nextLine();
			line = defaultEntityInfo.nextLine();
			gameEntity.add(line);
			//First line just reads "DEFAULT"
			//Next line is the header

			//make sure its not end of file
			while (defaultEntityInfo.hasNextLine()) {
				
				line = defaultEntityInfo.nextLine();
				if(line.contains(" x:")){
					line = "    x: "+x;
				}else if(line.contains(" y:")){
					line = "    y: "+y;
				}else if (line.contains(" sprite:")){
					line = "    sprite: "+sprite.split(".txt")[0];
				}

				gameEntity.add(line);
			}
			System.out.println(gameEntity);
			return gameEntity;
		} catch(FileNotFoundException fileNotFoundException){
			System.out.println("file "+Gdx.files.internal(dir)+ " not found!");
			System.out.println("running default game entity info");
			//run this function again but with gameEntityDefaults.txt
			return readDefaultValues("GameEntity/GameEntity.txt", x, y);
			//remember i need to change the sprite....
		}
	}
	
	/**
	 * This function loads in the default values, if there is the
	 * corrosponding .txt file to load the defaults from.
	 * This makes it easy to make multiple slimes for example
	 * 
	 * @param dir Directory of the text file
	 * @param x The x coordinate of the entity
	 * @param y The y coordinate of the entity
	 * @return Each item in the list is a line to be outputted in level.txt
	 */
	public ArrayList<String> readDefaultValues (String dir, int x, int y){
		try{
			//load file into defaultEntityInfo
			Scanner defaultEntityInfo = new Scanner(new File(Gdx.files.internal(dir)+""));
			//store contents as arraylist, each item being a line to be 
			//written to the level.txt
			ArrayList<String> gameEntity = new ArrayList<String>();
			//Scanner allows us to go line by line in the file with.nextLine()
			String line = defaultEntityInfo.nextLine();
			line = defaultEntityInfo.nextLine();
			gameEntity.add(line);
			//First line just reads "DEFAULT"
			//Next line is the header

			//make sure its not end of file
			while (defaultEntityInfo.hasNextLine()) {
				
				line = defaultEntityInfo.nextLine();
				if(line.contains(" x:")){
					line = "    x: "+x;
				}else if(line.contains(" y:")){
					line = "    y: "+y;
				}

				gameEntity.add(line);
			}
			System.out.println(gameEntity);
			return gameEntity;
		} catch(FileNotFoundException fileNotFoundException){
			System.out.println("file "+Gdx.files.internal(dir)+ " not found!");
			System.out.println("running default game entity info");
			//run this function again but with gameEntityDefaults.txt
			return defaultGameEntityValues("GameEntity/GameEntity.txt", x, y,dir);
			//remember i need to change the sprite....
		}
	}
	
	@Override
	public void render (float delta) {
		ScreenUtils.clear(1, 0, 0, 1);//red background

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundPicture.draw(batch);

        if(Gdx.input.justTouched()){
            // TODO : see Sam Notes
			System.out.println("X Coordinate: " + Gdx.input.getX());
            System.out.println("Y Coordinate: " + Gdx.input.getY());
			Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
			camera.unproject(mousePos); // mousePos is now in world coordinates
			System.out.println(mousePos);
			System.out.println(buttonGroup.getSelection());
			Enumeration elements = buttonGroup.getElements();
			while (elements.hasMoreElements()){
				AbstractButton button = (AbstractButton)elements.nextElement();
				if (button.isSelected()) {
					trees.add(new GameEntity((int)mousePos.x,(int)mousePos.y,10,10,new Sprite(new Texture("GameEntity/"+button.getText())),0));
					textFileOutput.add(readDefaultValues("GameEntity/"+button.getText()+".txt",(int)mousePos.x,(int)mousePos.y));
				}
			}
            //then call the function that puts the object on screen.
		}

		for(int i=0;i<trees.size();i++){
			batch.draw(trees.get(i).getSprite(),trees.get(i).getX(),trees.get(i).getY());
		}

		//batch.draw(character.getSprite(), character.x, character.y);

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
