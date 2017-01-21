package com.jormandr.roboticongame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jormandr.roboticongame.RoboticonGame;

public class DesktopLauncher {
	/**
	 * Main for desktop version of game
	 * @param arg
	 */
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Joramndr";
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		new LwjglApplication(new RoboticonGame(), config);

	}
}
