package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.main.Game;

public abstract class GameState
{	
	private final int WIDTH;
	private final int HEIGHT;
	
	public GameState(int width, int height)
	{
		WIDTH = width;
		HEIGHT = height;
	}
	
	public void onEnter() {}
	public void onExit() {}
	public void update(Game game) {}
	public void render(Bitmap map) {}
}