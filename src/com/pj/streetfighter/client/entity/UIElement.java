package com.pj.streetfighter.client.entity;

import com.pj.streetfighter.client.graphics.Sprite;

public class UIElement
{
	private Sprite sprite;
	private BoundingBox box;
	
	public UIElement(Sprite sprite, int x1, int y1, int x2, int y2)
	{
		this.sprite = sprite;
		this.box = new BoundingBox(x1, y1, x2, y2);
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
