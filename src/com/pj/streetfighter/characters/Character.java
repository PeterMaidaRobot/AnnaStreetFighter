package com.pj.streetfighter.characters;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.server.engine.BoundingBox;

public abstract class Character {
	
	private final Sprite sprite;
	private final BoundingBox[] boundingBoxes;

	public Character(Sprite sprite, BoundingBox[] boundingBoxes)
	{
		this.sprite = sprite;
		this.boundingBoxes = boundingBoxes;
	}

	public Sprite getSprite()
	{
		return sprite;
	}

	public BoundingBox[] getBoundingBoxes()
	{
		return boundingBoxes;
	}
}