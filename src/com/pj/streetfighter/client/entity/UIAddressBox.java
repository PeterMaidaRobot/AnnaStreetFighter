package com.pj.streetfighter.client.entity;

import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;

public class UIAddressBox extends UITextBox
{

	public UIAddressBox(int x, int y, Sprite unselectedSprite, Sprite selectedSprite, List<BoundingBox> boxes, int maxLength) {
		super(x, y, unselectedSprite, selectedSprite, boxes, maxLength);
	}

	// TODO UIAddressBox calls super.update only if it is not a wrong key press
	// will check for "." before calling super, if is there don't call super , otherwise call super, only numbers?
}
