package com.pj.streetfighter.client.entity;

import java.awt.event.KeyEvent;
import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.input.Keyboard;

public class UIAddressBox extends UITextBox
{

	public UIAddressBox(int x, int y, Sprite unselectedSprite, Sprite selectedSprite, List<BoundingBox> boxes, int maxLength)
	{
		super(x, y, unselectedSprite, selectedSprite, boxes, maxLength);
	}

	public void update(Keyboard keyboard) {
		super.update(keyboard); // TODO need to call this key press not the parents!
	}
	
	public void onKeyPress(int keyCode)
	{
		if (keyCode == KeyEvent.VK_PERIOD)
		{
			// TODO does stuff here
			super.onKeyPress(keyCode);
		}
		else if (keyCode == KeyEvent.VK_DELETE || keyCode == KeyEvent.VK_BACK_SPACE)
		{
			super.onKeyPress(keyCode);
		}
		
		// check if the given keyCode is a number
		for (int currKey = KeyEvent.VK_0; currKey < KeyEvent.VK_9; currKey++)
		{
			if (currKey == keyCode)
			{
				super.onKeyPress(keyCode);
				return;
			}
		}
		
		super.onKeyPress(keyCode); // TODO rm, used for testing******
	}
}
