package com.pj.streetfighter.server.engine;

public class Player
{
	private int x, y;

	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void incrementX(int i) {
		this.x += i;
	}
}
