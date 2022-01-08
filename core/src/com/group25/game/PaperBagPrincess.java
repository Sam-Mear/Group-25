package com.group25.game;

import com.badlogic.gdx.Game;

public class PaperBagPrincess extends Game {
	@Override
	public void create(){
		setScreen(new MenuScreen());
	}
}