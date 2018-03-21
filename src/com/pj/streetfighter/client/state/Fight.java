package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.client.main.Game;

public class Fight extends GameState{

	SpriteSheet fightSheet = new SpriteSheet("/character_sprites/fightsheet.png", 640);
	Sprite fightBackground = new Sprite(640, 378, 0, 0, fightSheet);
	
	SpriteSheet player1Sheet = new SpriteSheet("/character_sprites/prometheus.png", 64);
	Sprite player1 = new Sprite(64, 64, 0, 0, player1Sheet);
	
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
		player1.setX(player1.getX() + 1);
	}

	@Override
	public void render(Bitmap map)
	{
		map.drawSprite(fightBackground, 0, 0);
		map.drawSprite(player1, player1.getX(), 243);
	}

}
