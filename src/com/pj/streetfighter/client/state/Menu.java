package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.client.main.Game;

public class Menu implements GameState
{
	SpriteSheet menuSheet;
	Sprite menuBackground;
	
	@Override
	public void onEnter()
	{
		menuSheet = new SpriteSheet("/character_sprites/menusheet.png", 640);
		menuBackground = new Sprite(640, 378, 0, 0, menuSheet);
	}

	@Override
	public void onExit()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Game game)
	{

	}

	@Override
	public void render(Bitmap map)
	{
		map.drawSprite(menuBackground, 0, 0);
	}

		
}
