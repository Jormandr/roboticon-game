package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;
import com.jormandr.helpers.InputHandler;
import com.jormandr.misctypes.Pair;
import com.jormandr.players.Player;
import com.jormandr.ui.text.UIButtonTextRSmall;


/**
 * Abstract class for buttons that upgrade roboticons
 *
 */
public class UIButtonUpgradeRobo extends UIButtonTextRSmall {
	
	final float BUFFVAL = 2.0f;
	ResourceType resource;
	/**
	 * Initialises the UIBUttonUpgradeRobo button
	 * @param x
	 * @param y
	 * @param type
	 * @param world
	 */
	public UIButtonUpgradeRobo(float x, float y, GameWorld world, ResourceType sauce) {
		super(x, y, world,"","");
		
		resource = sauce;
	}

	@Override
	public void draw(SpriteBatch batcher) {
		
		// DOn't autoformat this; it looks nice rn
		String resStr = resource == ResourceType.FOOD ? "Food"
				      : resource == ResourceType.ORE ? "Ore"
				      : resource == ResourceType.ENERGY ? "Energy"
				      : "UNDEFINED";
		
		
		Plot plot = ((Plot) InputHandler.getTile());
		Pair<Integer, Integer> mousePos = GameWorld.getMousePos();
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}

		if (isMouseOver(mousePos.x, mousePos.y)) {
			AssetLoader.fontX.draw(batcher, "Cost: " + plot.getCost(), initX+18, initY+18);
		} else if (((Plot) InputHandler.getTile()).hasRoboticon() == true) {
			AssetLoader.fontX.draw(batcher, "Upgrade "+resStr, initX+18, initY+18);
			AssetLoader.fontX.draw(batcher, "production", initX+18, initY+28);
		} else {
			AssetLoader.fontX.draw(batcher, "Place Roboticon", initX+18, initY+18);
			AssetLoader.fontX.draw(batcher, "to Upgrade "+resStr, initX+28, initY+28);
		}
	}
	
	
	
	protected void setResourceUpgrade(float buff) {
		Plot plot = ((Plot) InputHandler.getTile());
		switch (resource) {
		case FOOD:
			plot.changeOreBuff(buff);
			break;
		case ORE:
			plot.changeOreBuff(buff);
			break;
		case ENERGY:
			plot.changeOreBuff(buff);
			break;
		default:
			break;
		}
	}
	

	@Override
	public boolean isTouchDown() {
		Player player = GameWorld.getPlayer(myWorld.getGameState());
		Plot plot = ((Plot) InputHandler.getTile());
		if (plot.hasRoboticon() == true && plot.getOwned() == player) {
			if (player.getChangeMoney(-plot.getCost()) >= 0) {
				setResourceUpgrade(BUFFVAL); //upgrade resource
				player.changeMoney(-(plot.getCost()));
				isPressed = true;
				plot.setCost(); //increase cost of upgrading
				return true;
			}
		}

		return false;
	}

}
