package com.pj.streetfighter.server.entity;

import com.esotericsoftware.kryonet.Connection;
import com.pj.streetfighter.server.engine.Physics;

public class Player
{
	public Connection c;
	public Object mostRecent = null;
	public int x, y;
	public Physics physics;
	// public Character character;
	//boundingboxes?
	
	public Player(Connection c, int x, int y, Physics physics)
	{
		this.c = c;
		this.x = x;
		this.y = y;
		this.physics = physics;
	}
}
