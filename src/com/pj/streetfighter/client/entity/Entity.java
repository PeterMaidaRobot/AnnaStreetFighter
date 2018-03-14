package com.pj.streetfighter.client.entity;

import com.pj.streetfighter.client.graphics.Sprite;

public abstract class Entity
{
	private int xPos, yPos;

	public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

	public int getY() {
		return yPos;
	}

	public void setY(int yPos) {
		this.yPos = yPos;
	}
	
	public void move(int xOffs, int yOffs)
	{
		setX(this.getX() + xOffs);
		setY(this.getY() + yOffs);
	}
	
	public Sprite getSprite()
	{
		return null;
	}
	
}
