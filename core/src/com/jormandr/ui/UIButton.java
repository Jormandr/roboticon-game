package com.jormandr.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.jormandr.helpers.AssetLoader;

public class UIButton {
	
	private Vector2 coords;
	private float[] verts = new float[8];
	private float button_width,button_height;
	private TextureRegion[] button_textures = new TextureRegion[10];
	private int type2buttonIn, type2buttonOut;
	private ButtonType button_type;
	
	private boolean isPressed = false;
	
	private final static float[] button_size = new float[10];
			
	public UIButton (float x, float y, ButtonType type){
		
		coords = new Vector2(x,y);
		
		button_textures[0] = AssetLoader.uiCloseOn;
		button_textures[1] = AssetLoader.uiCloseOn;
		button_textures[2] = AssetLoader.uiButtonRSmallIn;
		button_textures[3] = AssetLoader.uiButtonRSmallOut;
		button_textures[4] = AssetLoader.uiButtonSSmallIn;
		button_textures[5] = AssetLoader.uiButtonSSmallOut;
		button_textures[6] = AssetLoader.uiButtonRBigIn;
		button_textures[7] = AssetLoader.uiButtonRBigOut;
		button_textures[8] = AssetLoader.uiButtonSBigIn;
		button_textures[9] = AssetLoader.uiButtonSBigOut;
		
		
		//Close button width and height
	    button_size[0] = 10.0f;
	    button_size[1] = 10.0f;
		//Small Rectangle button width and height	    
	    button_size[2] = 30.0f;
	    button_size[3] = 12.0f;
		//Small Square button width and height	    
	    button_size[4] = 13.0f;
	    button_size[5] = 12.0f;
		//Big Rectangle button width and height	    
	    button_size[6] = 41.0f;
	    button_size[7] = 18.0f;
		//Big Square button width and height    
	    button_size[8] = 18.0f;
	    button_size[9] = 18.0f;
	    
	    button_type = type;
	    
	    type2buttonIn = button_type.ordinal()*2;
	    type2buttonOut = type2buttonIn +1;
	    
	    
	   //refactor with variables for type.ordinal()
	   button_width = button_size[type2buttonIn];
	   button_height = button_size[type2buttonOut];
		
		coords = new Vector2(x,y);
		
		this.verts[0] = coords.x;
		this.verts[1] = coords.y;
		this.verts[2] = coords.x;
		this.verts[3] = coords.y + button_height;
		this.verts[4] = coords.x + button_width;
		this.verts[5] = coords.y + button_height;
		this.verts[6] = coords.x + button_width;
		this.verts[7] = coords.y;
		
	}
	
	
	 public void draw(SpriteBatch batcher) {
	        if (isPressed) {
	            batcher.draw(button_textures[type2buttonIn], coords.x, coords.y, 0, 0, button_width, button_height, 4, 4, 0);
	        } else {
	            batcher.draw(button_textures[type2buttonOut], coords.x, coords.y, 0, 0, button_width, button_height, 4, 4, 0);
	        }
	    }

	 
	 
	 
}
