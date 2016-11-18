package com.jormandr.roboticongame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jormandr.roboticongame.RoboticonGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new RoboticonGame(), config);
		config.title = "Joramndr";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new RoboticonGame(), config);
		
	}
}
