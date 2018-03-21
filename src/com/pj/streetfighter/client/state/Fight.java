package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.client.main.Game;

public class Fight extends GameState{

	SpriteSheet fightSheet = new SpriteSheet("/character_sprites/menusheet.png", 640);
	Sprite fightBackground = new Sprite(640, 378, 0, 0, fightSheet);
	
	public Fight(int width, int height)
	{
		super(width, height);
	}

	@Override
	public void onEnter(Game game)
	{
		
	}

	@Override
	public void onExit()
	{
		
	}

	@Override
	public void update(Game game)
	{
		
	}

	@Override
	public void render(Bitmap map)
	{
		map.drawSprite(fightBackground, 0, 0);
	}

}
