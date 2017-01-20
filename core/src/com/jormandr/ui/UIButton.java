package com.jormandr.ui;

import com.badlogic.gdx.math.Vector2;

public class UIButton {
	
	private Vector2 coords;
	private float[] verts = new float[8];
	
	private final static float[] button_size = new float[10];
			
	public UIButton (float x, float y, ButtonType type){
		
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
	    
	   
	   //refactor with variables for type.ordinal()
	   float button_width = button_size[type.ordinal()*2];
	   float button_height = button_size[(type.ordinal()*2) +1];
		
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
	
	

}
