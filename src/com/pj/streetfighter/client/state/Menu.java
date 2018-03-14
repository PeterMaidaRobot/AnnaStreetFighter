package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.entity.BoundingBox;
import com.pj.streetfighter.client.entity.UIElement;
import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.client.main.Game;

public class Menu implements GameState
{
	SpriteSheet menuSheet;
	Sprite menuBackground;
	UIElement connectButton;
	
	@Override
	public void onEnter()
	{
		menuSheet = new SpriteSheet("/character_sprites/menusheet.png", 640);
		menuBackground = new Sprite(640, 378, 0, 0, menuSheet);
		connectButton = new UIElement(new Sprite(64, 16, 0, 378, menuSheet), 0, 0, 64, 16);
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
		map.drawSprite(connectButton.getSprite(), map.getWidth()/2 - connectButton.getSprite().getXSIZE()/2, map.getHeight()/2 - connectButton.getSprite().getYSIZE()/2);
	}

		
}
