package com.pj.streetfighter.client.state;

import java.util.ArrayList;
import java.util.List;

import com.pj.streetfighter.client.entity.BoundingBox;
import com.pj.streetfighter.client.entity.UIAddressBox;
import com.pj.streetfighter.client.entity.UIButton;
import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.client.main.Game;

public class Menu extends GameState
{
	SpriteSheet menuSheet;
	Sprite menuBackground;
	UIButton connect, quit;
	UIAddressBox address;
	
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
		connect = new UIButton(width/2, 3*(height/4), connectUnselected, connectSelected, connectBoxes);
		
		Sprite quitUnselected = new Sprite(64, 16, 128, 378, menuSheet);
		Sprite quitSelected = new Sprite(64, 16, 192, 378, menuSheet);
		quit = new UIButton(width/2, 3*(height/4) + 30, quitUnselected, quitSelected, connectBoxes);
		
		// create address text box
		Sprite addressUnselected = new Sprite(128, 16, 256, 378, menuSheet);
		Sprite addressSelected = new Sprite(128, 16, 384, 378, menuSheet);
		List<BoundingBox> addressBoxes = new ArrayList<BoundingBox>();
		addressBoxes.add(new BoundingBox(0, 0, 127, 15));
		address = new UIAddressBox(width/2, 3*(height/4) - 30, addressUnselected, addressSelected, addressBoxes, 10);
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
	public void update(Game game)
	{
		int mouseX = game.mouse.getX();
		int mouseY = game.mouse.getY();
		
		// need to call this code block for every UI element
		address.update(game.keyboard);
		connect.update(false, mouseX, mouseY);	
		quit.update(false, mouseX, mouseY);
		if (game.mouse.isPressed())
		{
			connect.update(true, mouseX, mouseY);	
			quit.update(true, mouseX, mouseY);
			address.onMousePress(mouseX, mouseY);
			//game.connectionManager.doConnection = true;
		}
		
		if (quit.isSelected())
		{
			game.setRunning(false);
			game.frame.dispose();
			System.exit(0);
		}
	}

	@Override
	public void render(Bitmap map)
	{
		// draw background
		map.drawSprite(menuBackground, 0, 0);
		
		// draw all entities
		map.drawSprite(connect.getSprite(), connect.getX(), connect.getY() + connect.yOffs);
		map.drawSprite(quit.getSprite(), quit.getX(), quit.getY() + quit.yOffs);
		map.drawSprite(address.getSprite(), address.getX(), address.getY());
	}

		
}
