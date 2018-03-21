package com.pj.streetfighter.client.entity;

import java.awt.event.KeyEvent;
import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;

public class UIAddressBox extends UITextBox
{

	public UIAddressBox(int x, int y, Sprite unselectedSprite, Sprite selectedSprite, List<BoundingBox> boxes,
			int horizontalPadding, int verticalPadding)
	{
		super(x, y, unselectedSprite, selectedSprite, boxes, 15, horizontalPadding, verticalPadding); // an IP Address is 15 character long, including periods
	}
	
	public void onKeyPress(int keyCode)
	{
		// check if period, delete/backspace, or number has been entered
		if (keyCode == KeyEvent.VK_PERIOD || keyCode == KeyEvent.VK_DELETE || keyCode == KeyEvent.VK_BACK_SPACE
				|| (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9))
		{
			super.onKeyPress(keyCode);
		}
	}
}
