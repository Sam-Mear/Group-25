/**
 * TODO : 
 *  * actually enter a level name and save as file.
 *    DONE
 * 
 *  * Able to change background
 *    NOT DONE
 * 
 *  * Click on an already placed entity to change its x and y position
 *    DONE
 * 
 *  * Delete entities
 *    DONE
 * 
 *  * Adjust constructor values by clicking on a placed entity, including collision.
 *    NOT DONE
 * 
 *  * Grid system
 *    NOT DONE
 * 
 *  * Change map size values
 *    NOT DONE
 * 
 */

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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * pre gameEntity info first(like background and default player position)
 * then GameEntity info
 * both seperate arraylists.
 */
public class LevelCreator extends JFrame implements Screen{
    

    private SpriteBatch batch;
	private Sprite img;
	private Sprite backgroundPicture;
	private OrthographicCamera camera;
	private FitViewport viewport;

	//button group of the radio buttons
	private ButtonGroup buttonGroup;
	//movetool
	private JCheckBox moveTool;
	private JCheckBox deleteTool;
	//the below is -1 for not selected.
	//after moving an etity, it goes back to -1.
	private int selectedEntity = -1;

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

	public void writeNewFile(){
		//fuck saving it with custom name
		try{
			File newFile = new File(Gdx.files.internal("Levels/NewLevel.txt")+"");
			if(newFile.createNewFile()){
				System.out.println("New file is created!");
			}else{
				System.out.println("File already exists...");
			}

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
			for(int i=0;i<textFileOutput.size();i++){
				for (int j=0;j<textFileOutput.get(i).size();j++){
					bw.write(textFileOutput.get(i).get(j));
					bw.newLine();
				}
			}
			bw.close();


		} catch(IOException e){
			System.out.println("ERROR writing file.");
		}
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

    public void changeBackground() {
		//popup, run listFilesForFolder but for Backgrounds/
		//below code to change background
		//setup the pre gameentity part of txt file so we
		//can save the text file.
		System.out.println("CHANGING BACKGROUND!!");
		//this mess cause swing in different thread?
		Gdx.app.postRunnable(new Runnable()  {
			@Override
			public void run() {
			backgroundPicture.setRegion(new Texture(Gdx.files.internal("Backgrounds/unknown.png")));
			}
		});
		
	}
	
	public void loadLevel(){
		buttonGroup = new ButtonGroup();
        JFrame f = new JFrame("Enter Level Name");
		JButton saveLevelButton = new JButton("Save Level");
		//JButton loadLevelButton = new JButton("Load Level");
		saveLevelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				writeNewFile();
			}

		});
		f.add(saveLevelButton);
		JButton changeBackgroundButton = new JButton("Change Background");
		changeBackgroundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				changeBackground();
			}

		});
		f.add(changeBackgroundButton);
		//f.add(loadLevelButton);
        //JPopupMenu popupMenu = new JPopupMenu("Enter Level Name1");
        //JTextField enterLevelName = new JTextField(10);

		final File folder = new File(Gdx.files.internal("GameEntity/") + "");
		ArrayList<JRadioButton> buttons = listFilesForFolder(folder);
		for (JRadioButton jRadioButton : buttons) {
			f.add(jRadioButton);
			buttonGroup.add(jRadioButton);
		}

		moveTool = new JCheckBox("Move Tool");
		f.add(moveTool);
		deleteTool = new JCheckBox("Delete Tool");
		f.add(deleteTool);

        f.setSize(200,500);
		f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //popupMenu.add(enterLevelName);
        //f.add(enterLevelName);
        f.setVisible(true);
    }

	public void addEntityToGameWindow(ArrayList<String> entityValues){
		Float x,y;
		x = (float) 0;
		y = (float) 0;
		Integer width,height;
		width = 50;
		height = 50;
		String sprite;
		sprite = "GameEntity/tree.png";
		for(int i=0;i<entityValues.size();i++){
			if(entityValues.get(i).contains("x:")){
				x = Float.parseFloat(entityValues.get(i).split(": ")[1]);
			}else if(entityValues.get(i).contains("y:")){
				y = Float.parseFloat(entityValues.get(i).split(": ")[1]);
			}else if(entityValues.get(i).contains("sprite:")){
				sprite = entityValues.get(i).split(": ")[1];
			}else if(entityValues.get(i).contains("width:")){
				width = Integer.parseInt(entityValues.get(i).split(": ")[1]);
			}else if(entityValues.get(i).contains("height:")){
				height = Integer.parseInt(entityValues.get(i).split(": ")[1]);
			}
		}
			
		//gotta do some mad type changing
		trees.add(new GameEntity(x,
								y,
								width,
								height, 
								new Sprite(new Texture(sprite)), 
								Integer.parseInt("0")));
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
	
	public void moveEntity(int x, int y){
		trees.get(selectedEntity).setX(x);
		trees.get(selectedEntity).setY(y);
		trees.get(selectedEntity).updateHitbox();

		//now gotta change the txt file output 
		ArrayList<String> temp = textFileOutput.get(selectedEntity);
		for(int i=0;i<temp.size();i++){
			System.out.println(temp.get(i));
			if(temp.get(i).contains("x:")){
				temp.set(i,("    x: "+ x));
			}else if(temp.get(i).contains("y:")){
				temp.set(i,("    y: "+y));
			}
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
			System.out.println("X Coordinate: " + Gdx.input.getX());
			System.out.println("Y Coordinate: " + Gdx.input.getY());
			Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
			camera.unproject(mousePos); // mousePos is now in world coordinates
			System.out.println(mousePos);
			if(moveTool.isSelected()){
				if(selectedEntity == -1){
					//no entity is selected, the user must be trying to select
					for(int i=0; i<trees.size();i++){
						Rectangle temp = trees.get(i).getHitbox();
						if(temp.contains((int)mousePos.x,(int)mousePos.y)){
							selectedEntity = i;
						}
						System.out.println(temp);
						
					}
				}else{
					//an entity is selected, the user must be trying to move their selected
					moveEntity((int)mousePos.x,(int)mousePos.y);
					selectedEntity = -1;
				}
			}else if(deleteTool.isSelected()){
				for(int i=0; i<trees.size();i++){
					Rectangle temp = trees.get(i).getHitbox();
					if(temp.contains((int)mousePos.x,(int)mousePos.y)){
						trees.remove(i);
						textFileOutput.remove(i);
						// NOTE : might need to .dispose() the gameEntity.
					}
					System.out.println(temp);
				}
			}else{
				// TODO : see Sam Notes
				Enumeration elements = buttonGroup.getElements();
				while (elements.hasMoreElements()){
					AbstractButton button = (AbstractButton)elements.nextElement();
					if (button.isSelected()) {
						//trees.add(new GameEntity((int)mousePos.x,(int)mousePos.y,10,10,new Sprite(new Texture("GameEntity/"+button.getText())),0));
						ArrayList <String> entityValues = readDefaultValues("GameEntity/"+button.getText()+".txt",(int)mousePos.x,(int)mousePos.y);
						addEntityToGameWindow(entityValues);
						textFileOutput.add(entityValues);
					}
				}
				//then call the function that puts the object on screen.
			}
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
		System.out.println(trees);
		System.out.println(textFileOutput);
        
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
