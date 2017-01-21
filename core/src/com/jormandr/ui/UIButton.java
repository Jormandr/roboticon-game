package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.jormandr.gameworld.GameWorld;
import com.jormandr.helpers.AssetLoader;

public abstract class UIButton {

	protected Vector2 coords;
	private float[] verts = new float[8];
	protected float button_width;
	protected float button_height;
	protected GameWorld myWorld;
	

	protected int type2buttonIn;
	protected int type2buttonOut;
	private ButtonType button_type;

	protected boolean isPressed = false;


	public UIButton(float x, float y, ButtonType type,GameWorld world) {
		
		myWorld = world;

		coords = new Vector2(x, y);
		button_type = type;

		type2buttonIn = button_type.ordinal() * 2;
		type2buttonOut = type2buttonIn + 1;

		// refactor with variables for type.ordinal()
		button_width = AssetLoader.button_size[type2buttonIn];
		button_height = AssetLoader.button_size[type2buttonOut];

		coords = new Vector2(x, y);

		this.verts[0] = coords.x;
		this.verts[1] = coords.y;
		this.verts[2] = coords.x;
		this.verts[3] = coords.y + button_height*4;
		this.verts[4] = coords.x + button_width*4;
		this.verts[5] = coords.y + button_height*4;
		this.verts[6] = coords.x + button_width*4;
		this.verts[7] = coords.y;

	}

	public boolean isMouseOver(int screenX, int screenY) {
		
		
		return (Intersector.isPointInPolygon(verts, 0, 8, screenX, screenY));
	}

	public void draw(SpriteBatch batcher) {
		//Gdx.app.log("Drawing: ", String.valueOf(isPressed));
		if (isPressed) {
			batcher.draw(AssetLoader.button_textures[type2buttonIn], coords.x, coords.y, 0, 0, button_width, button_height, 4, 4,
					0);
		} else {
			batcher.draw(AssetLoader.button_textures[type2buttonOut], coords.x, coords.y, 0, 0, button_width, button_height, 4, 4,
					0);
		}
	}

	public boolean isTouchDown() {
		//run logic for the button being pressed
		isPressed = true;
			return true;
	}

	public boolean isTouchUp() {
		//run logic for the button unpressed
		isPressed = false;
		return true;
	}

}
