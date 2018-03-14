package com.pj.streetfighter.client.entity;

import com.pj.streetfighter.client.graphics.Sprite;

public class UIElement
{
	private Sprite sprite;
	private BoundingBox box;
	
	public UIElement(Sprite sprite, int bx1, int by1, int bx2, int by2)
	{
		//super(x, y);
		this.sprite = sprite;
		this.box = new BoundingBox(bx1, by1, bx2, by2);
	}

	public boolean containsCursor(int x, int y)
	{
		return box.contains(x, y);
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
}
