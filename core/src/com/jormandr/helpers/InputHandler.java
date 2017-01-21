package com.jormandr.helpers;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.ui.ButtonType;
import com.jormandr.ui.UIButton;
import com.jormandr.ui.UIButtonClose;

public class InputHandler implements InputProcessor {

	private static ArrayList<UIButton> menuButtons;
	private GameWorld myWorld;

	private static UIButton closeButton;
	private UIButton buyPlotButton;

	public InputHandler(GameWorld myWorld) {
		this.myWorld = myWorld;

		menuButtons = new ArrayList<UIButton>();
		UIButton closeButton = new UIButtonClose(300, 300, ButtonType.CLOSE, myWorld);
		UIButton buyPlotButton = new UIButton(200, 200, ButtonType.RBIG);

		Gdx.app.log("InputHandler: ", "On");
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			Gdx.app.log("InputHandler: ", "Left Click");
			if (myWorld.isRunning()) {
				if (CollisionHandler.tileMouseOver()) {
					// setup the plot menu
					myWorld.toMenuPlot();
					return true;
				}
				//else if (/*click market button condition */){}
				
			} else if (myWorld.isMenu()) {
				Gdx.app.log("InputHandler: ", "In Menu");
				for (int i = 0; i < menuButtons.size(); i += 1) {
					if (menuButtons.get(i).isMouseOver(screenX,screenY)) {
						Gdx.app.log("InputHandler: ", "Button Clicked");
						menuButtons.get(i).isTouchDown();
						return true;
					}
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
	
	public static void LoadPlotPlotMenu(){
		menuButtons.add(closeButton);
	}
	
	public static void LoadPlotBuyMenu(){
		menuButtons.add(closeButton);
	}
	
	public static void LoadPlotPlaceMenu(){
		menuButtons.add(closeButton);
	}

}
