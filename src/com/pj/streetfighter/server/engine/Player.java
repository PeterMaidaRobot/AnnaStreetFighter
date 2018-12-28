package com.pj.streetfighter.server.engine;

import com.pj.streetfighter.characters.Character;

public class Player
{	
	private Character character;
	private byte state;
	private int animationIndex = 0;
	private int x, y;
	
	public static final double MAX_X_VEL = 3;
	public static final double MAX_Y_VEL = 7;
	
	private double xVel, yVel, xAccel;

	public Player(Character p1Character, int x, int y)
	{
		this.character = p1Character;
		this.x = x;
		this.y = y;
		setXVel(0);
		setYVel(0);
		setXAccel(0);
	}
	
	public Character getCharacter()
	{
		return character;
	}
	
	public byte getState()
	{
		return state;
	}
	
	public void setState(byte state)
	{
		this.state = state;
	}
	
	public int getAnimationIndex()
	{
		return animationIndex;
	}

	public void setAnimationIndex(int animationIndex)
	{
		this.animationIndex = animationIndex;
	}	
	
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void incrementX(int i)
	{
		this.x += i;
	}
	
	public double getXVel()
	{
		return xVel;
	}

	public void setXVel(double xVel)
	{
		this.xVel = xVel;
	}

	public double getYVel()
	{
		return yVel;
	}

	public void setYVel(double yVel)
	{
		this.yVel = yVel;
	}
	
	public double getXAccel()
	{
		return xAccel;
	}

	public void setXAccel(double xAccel)
	{
		this.xAccel = xAccel;
	}
	
	public BoundingBox[] getBoundingBoxes()
	{
		BoundingBox[] originalBoxes = character.getAnimation(animationIndex).getBoundingBoxes();
		BoundingBox[] boundingBoxes = new BoundingBox[originalBoxes.length];
	
		for (int i = 0; i < boundingBoxes.length; i++)
		{
			boundingBoxes[i] = new BoundingBox(originalBoxes[i]);
			boundingBoxes[i].addXOffset(x);
			boundingBoxes[i].addYOffset(y);
		}
		return boundingBoxes;
	}
}
