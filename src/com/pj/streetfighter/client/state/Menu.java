package com.pj.streetfighter.client.state;

import java.util.ArrayList;
import java.util.List;

import com.pj.streetfighter.client.entity.BoundingBox;
import com.pj.streetfighter.client.entity.UIButton;
import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.client.input.Keyboard;
import com.pj.streetfighter.client.input.Mouse;

public class Menu extends GameState
{
	SpriteSheet menuSheet;
	Sprite menuBackground;
	UIButton connect;
	
	public Menu(int width, int height)
	{
		super(width, height);
		menuSheet = new SpriteSheet("/character_sprites/menusheet.png", 640);
		menuBackground = new Sprite(640, 378, 0, 0, menuSheet);
		
		// create connect button
		Sprite connectUnselected = new Sprite(64, 16, 0, 378, menuSheet);
		Sprite connectSelected = new Sprite(64, 16, 64, 378, menuSheet);
		List<BoundingBox> connectBoxes = new ArrayList<BoundingBox>();
		connectBoxes.add(new BoundingBox(1, 0, 56, 4));
		connectBoxes.add(new BoundingBox(3, 4, 60, 10));
		connectBoxes.add(new BoundingBox(6, 11, 62, 15));
		connect = new UIButton(width/2, height/2, connectUnselected, connectSelected, connectBoxes);
	}
	
	@Override
	public void onEnter()
	{
		
	}

	@Override
	public void onExit()
	{
		
	}

	@Override
	public void update(Mouse mouse, Keyboard keyboard)
	{
		// need to call this code block for every Clickable UI element
		connect.update(mouse.getX(), mouse.getY());	
		if (connect.isPressed() && mouse.isPressed())
		{
			// TODO luv me
		}
	}

	@Override
	public void render(Bitmap map)
	{
		// draw background
		map.drawSprite(menuBackground, 0, 0);
		
		// draw all entities
		map.drawSprite(connect.getSprite(), connect.getX(), connect.getY() + connect.yOffs);
	}

		
}
