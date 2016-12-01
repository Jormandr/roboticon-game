package com.jormandr.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jormandr.gameobjects.TileType;

public class AssetLoader {

	public static Texture tilesTexture, roboticonsTexture, uiTexture;
	public static TextureRegion grassRegion, badlandsRegion, desertRegion, waterRegion, uiTV, uiBottom, uiTopMid,
			uiStateLightOn, uiStateLightOff;
	public static TextureRegion[] textureMap = new TextureRegion[4];

	public static void load() {
		tilesTexture = new Texture(Gdx.files.internal("tilesPage.png"));
		tilesTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		grassRegion = new TextureRegion(tilesTexture, 63, 0, 62, 34);
		grassRegion.flip(false, false);
		badlandsRegion = new TextureRegion(tilesTexture, 0, 34, 62, 34);
		badlandsRegion.flip(false, false);
		desertRegion = new TextureRegion(tilesTexture, 0, 0, 62, 34);
		desertRegion.flip(false, false);
		waterRegion = new TextureRegion(tilesTexture, 63, 34, 62, 34);
		waterRegion.flip(false, false);

		uiTexture = new Texture(Gdx.files.internal("uiPage.png"));
		uiTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		uiBottom = new TextureRegion(uiTexture, 0, 0, 320, 51);
		uiTopMid = new TextureRegion(uiTexture, 1, 53, 80, 81);
		uiTV = new TextureRegion(uiTexture, 83, 53, 123, 109);
		uiStateLightOn = new TextureRegion(uiTexture, 126, 53, 132, 59);
		uiStateLightOff = new TextureRegion(uiTexture, 135, 53, 141, 59);

	}

	public static void dispose() {
		tilesTexture.dispose();
	}

	public AssetLoader() {
		textureMap[TileType.GRASS.ordinal()] = grassRegion;
		textureMap[TileType.DESERT.ordinal()] = desertRegion;
		textureMap[TileType.BADLANDS.ordinal()] = badlandsRegion;
		textureMap[TileType.WATER.ordinal()] = waterRegion;
	}

}
