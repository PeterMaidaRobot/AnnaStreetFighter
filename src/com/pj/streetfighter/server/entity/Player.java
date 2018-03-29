package com.pj.streetfighter.server.entity;

import com.esotericsoftware.kryonet.Connection;

public class Player
{
	public Connection c;
	public Object mostRecent = null;
	public int x, y;
	// public Character character;
	
	public Player(Connection c, int x, int y)
	{
		this.c = c;
		this.x = x;
		this.y = y;
	}
}
