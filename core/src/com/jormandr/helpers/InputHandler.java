package com.jormandr.helpers;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameobjects.Market.ResourceType;
import com.jormandr.gameobjects.Plot;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;
import com.jormandr.ui.UIButtonBuyRobo;
import com.jormandr.ui.UIButtonClose;
import com.jormandr.ui.UIButtonEndPhase;
import com.jormandr.ui.UIButtonMarket;
import com.jormandr.ui.text.rbig.UIButtonAuctionEnd;
import com.jormandr.ui.text.rbig.UIButtonBuyPlot;
import com.jormandr.ui.text.rbig.UIButtonEndGame;
import com.jormandr.ui.text.rbig.UIButtonPlaceRobo;
import com.jormandr.ui.trade.UIButtonBuy;
import com.jormandr.ui.trade.UIButtonSell;
import com.jormandr.ui.upgrade.UIButtonUpgradeRoboEnergy;
import com.jormandr.ui.upgrade.UIButtonUpgradeRoboFood;
import com.jormandr.ui.upgrade.UIButtonUpgradeRoboOre;

public class InputHandler implements InputProcessor {

	public enum MenuUI {
		PLOT, MARKET, END;
	}

	private static GameWorld myWorld;
	private static MenuUI currentMenu;
	private static MapTile selectedTile;

	public InputHandler(GameWorld myWorld) {

		currentMenu = MenuUI.PLOT;
		InputHandler.myWorld = myWorld;
		


		Gdx.app.log("InputHandler: ", "On");
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		ArrayList<UIButton> menuButtons = UIHandler.getMenuButtons();
		if (button == Input.Buttons.LEFT) {
			//log for testing report
			//Gdx.app.log("InputHandler: ", "Left Click");
			if (myWorld.isRunning()) {
				if (CollisionHandler.tileMouseOver()) {
					selectedTile = CollisionHandler.getNearestMapTile();
					// setup the plot menu
					currentMenu = MenuUI.PLOT;
					myWorld.toMenuPlot();
					return true;
				}
			}

			for (int i = 0; i < menuButtons.size(); i += 1) {
				if (menuButtons.get(i).isMouseOver(screenX, screenY)) {
					//log for testing report
					//Gdx.app.log("InputHandler: ", "Button Clicked");
					menuButtons.get(i).isTouchDown();
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		ArrayList<UIButton> menuButtons = UIHandler.getMenuButtons();		
		if (button == Input.Buttons.LEFT) {
			//log for testing report
			//Gdx.app.log("InputHandler: ", "Left Up");
			for (int i = 0; i < menuButtons.size(); i += 1) {
				menuButtons.get(i).isTouchUp();
			}
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}



	public static MapTile getTile() {
		return selectedTile;
	}

	public static boolean menuIsPlot() {
		if (currentMenu == MenuUI.PLOT) {
			return true;
		}
		return false;
	}

	public static boolean menuIsMarket() {
		if (currentMenu == MenuUI.MARKET) {
			return true;
		}
		return false;

	}

	public static boolean menuIsEnd() {
		if (currentMenu == MenuUI.END) {
			return true;
		}
		return false;
	}

	public static void setMenu(MenuUI menu) {
		currentMenu = menu;
	}

}
