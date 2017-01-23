package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;

/**
 * Abstract class for all user interface buttons
 *
 */
public abstract class UIButton {

	protected Vector2 coords;
	private float[] verts = new float[8];
	protected float buttonWidth;
	protected float buttonHeight;
	protected GameWorld myWorld;

	protected int type2buttonIn;
	protected int type2buttonOut;
	private ButtonType button_type;

	protected boolean isPressed = false;

	/**
	 * Initialises user interface button
	 * 
	 * @param x
	 * @param y
	 * @param type
	 * @param world
	 */
	public UIButton(float x, float y, ButtonType type, GameWorld world) {

		myWorld = world;

		coords = new Vector2(x, y);
		button_type = type;

		type2buttonIn = button_type.ordinal() * 2;
		type2buttonOut = type2buttonIn + 1;

		// refactor with variables for type.ordinal()
		buttonWidth = AssetLoader.button_size[type2buttonIn];
		buttonHeight = AssetLoader.button_size[type2buttonOut];

		coords = new Vector2(x, y);

		this.verts[0] = coords.x;
		this.verts[1] = coords.y;
		this.verts[2] = coords.x;
		this.verts[3] = coords.y + buttonHeight * 4;
		this.verts[4] = coords.x + buttonWidth * 4;
		this.verts[5] = coords.y + buttonHeight * 4;
		this.verts[6] = coords.x + buttonWidth * 4;
		this.verts[7] = coords.y;

	}

	/**
	 * returns whether the button is intersecting with the mouse
	 * 
	 * @param screenX
	 * @param screenY
	 * @return whether the button is intersecting with mouse
	 */
	public boolean isMouseOver(int screenX, int screenY) {
		return (Intersector.isPointInPolygon(verts, 0, 8, screenX, screenY));
	}

	/**
	 * draw method to be called in GameRenderer
	 * 
	 * @param batcher
	 */
	public void draw(SpriteBatch batcher) {
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, buttonWidth,
					buttonHeight, 4, 4, 0);
		}
	}

	/**
	 * what logic to do in game on touchDown
	 * 
	 * @return boolean for InputProcessor logic
	 */
	public boolean isTouchDown() {
		// run logic for the button being pressed
		isPressed = true;
		return true;
	}

	/**
	 * what logic to do in game on touchUp
	 * 
	 * @return boolean for InputProcessor logic
	 */
	public boolean isTouchUp() {
		// run logic for the button un-pressed
		isPressed = false;
		return true;
	}

}
