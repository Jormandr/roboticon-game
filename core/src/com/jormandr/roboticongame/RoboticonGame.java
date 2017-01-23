/**
 * Roboticon Colony of York
 * By Jormandr Games
 * 
 * Executable JARs and assessment milestones can be found at https://github.com/Jormandr/roboticon-game/releases
 */

package com.jormandr.roboticongame;

import com.badlogic.gdx.Game;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.screens.GameScreen;

/**
 * This is the main body of the game
 * <p>
 * From here, the screen is chosen,
 *
 * choice of screen determines the actual game logic and rendering
 *
 */
public class RoboticonGame extends Game {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Game#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
