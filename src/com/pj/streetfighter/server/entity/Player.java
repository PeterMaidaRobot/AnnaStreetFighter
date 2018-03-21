package com.pj.streetfighter.server.entity;

import com.esotericsoftware.kryonet.Connection;

public class Player
{
	public Connection c;
	// public Character character;
	
	public Player(Connection c)
	{
		this.c = c;
	}
}
