package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.input.Keyboard;
import com.pj.streetfighter.client.input.Mouse;

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
	public void update(Mouse mouse, Keyboard keyboard) {}
	public void render(Bitmap map) {}
}