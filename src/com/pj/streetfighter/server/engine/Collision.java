package com.pj.streetfighter.server.engine;

public class Collision
{
	public boolean up = false;
	public boolean left = false;
	public boolean down = false;
	public boolean right = false;
	
	public boolean anyCollisions()
	{
		return up || left || down || right;
	}
}
