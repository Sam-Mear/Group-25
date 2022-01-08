package com.group25.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.group25.game.PaperBagPrincess;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		// Limits FPS to the refresh rate of the currently active monitor.
		config.useVsync(true);
		config.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        // If you remove the above line and set Vsync to false, you may get unlimited FPS, which can be
        // useful for testing performance, but can also be very stressful to some hardware.
        // You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
		
		//set the window title and size.
		config.setTitle("Paper Bag Princess");
		config.setWindowedMode(960,720);
		//launch the game
		new Lwjgl3Application(new PaperBagPrincess(), config);
	}
}
