package com.pj.streetfighter.server.engine;

import com.pj.streetfighter.characters.Character;

public class Player
{
	private Character character;
	private byte state;
	private int x, y;

	public Player(Character p1Character, int x, int y)
	{
		this.character = p1Character;
		this.x = x;
		this.y = y;
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
}
