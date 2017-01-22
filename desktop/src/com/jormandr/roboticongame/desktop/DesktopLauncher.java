package com.jormandr.roboticongame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jormandr.config.GameConfig;
import com.jormandr.roboticongame.RoboticonGame;

public class DesktopLauncher {
	/**
	 * Main for desktop version of game
	 * @param arg
	 */
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Roboticon Colony of York - by Jormandr Games";
		config.width = GameConfig.getWidth();
		config.height = GameConfig.getHeight();
		config.resizable = false;
		config.forceExit = true;
		new LwjglApplication(new RoboticonGame(), config);

	}
}
