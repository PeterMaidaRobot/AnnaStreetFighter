package com.pj.streetfighter.server.entity;

import com.esotericsoftware.kryonet.Connection;

public class PlayerConnection
{
	public Connection c;
	public Object mostRecent = null;
	
	public PlayerConnection(Connection c)
	{
		this.c = c;
	}
}
