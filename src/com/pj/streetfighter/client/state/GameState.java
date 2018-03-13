package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.main.Game;

public interface GameState
{
	
	public void onEnter();
	public void onExit();
	public void update(Game game);
	public void render(Bitmap map);
	
}