package com.jormandr.helpers;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jormandr.gameobjects.MapTile;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;
import com.jormandr.ui.UIButtonBuyPlot;
import com.jormandr.ui.UIButtonClose;
import com.jormandr.ui.UIButtonEndTurn;

public class InputHandler implements InputProcessor {

	private static ArrayList<UIButton> menuButtons;
	private GameWorld myWorld;

	private static UIButton closeButton;
	private static UIButton buyPlotButton;
	private static UIButton endTurnButton;
	private static MapTile selectedTile;

	public InputHandler(GameWorld myWorld) {
		this.myWorld = myWorld;

		menuButtons = new ArrayList<UIButton>();
		closeButton = new UIButtonClose(904, 224, ButtonType.CLOSE, myWorld);
		buyPlotButton = new UIButtonBuyPlot(560, 464, ButtonType.RBIG, myWorld);
		endTurnButton = new UIButtonEndTurn(189*4,24,ButtonType.SSMALL,myWorld);
		
		menuButtons.add(endTurnButton);

		Gdx.app.log("InputHandler: ", "On");
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			Gdx.app.log("InputHandler: ", "Left Click");
			if (myWorld.isRunning()) {
				if (CollisionHandler.tileMouseOver()) {
					selectedTile = CollisionHandler.getNearestMapTile();
					// setup the plot menu
					myWorld.toMenuPlot();
					return true;
				}
			}
				
				for (int i = 0; i < menuButtons.size(); i += 1) {
					if (menuButtons.get(i).isMouseOver(screenX,screenY)) {
						Gdx.app.log("InputHandler: ", "Button Clicked");
						menuButtons.get(i).isTouchDown();
						return true;
				}
			}

		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			Gdx.app.log("InputHandler: ", "Left Up");
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

	public static ArrayList<UIButton> getMenuButtons() {
		return menuButtons;
	}

	public static void clearMenuButtons() {
		menuButtons.clear();
	}
	
	public static void LoadGameMenu(){
		menuButtons.add(endTurnButton);
	}
	
	public static void LoadPlotPlotMenu(){
		Gdx.app.log("InputHandler: ", "PlotPlotMenu");
		menuButtons.add(closeButton);
		menuButtons.add(buyPlotButton);
		menuButtons.add(endTurnButton);
	}
	
	public static void LoadPlotBuyMenu(){
		menuButtons.add(endTurnButton);
		menuButtons.add(closeButton);
	}
	
	public static void LoadPlotPlaceMenu(){
		menuButtons.add(endTurnButton);
		menuButtons.add(closeButton);
	}
	
	public static MapTile getTile(){
		return selectedTile;
	}

}
