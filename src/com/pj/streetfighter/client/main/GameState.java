package com.pj.streetfighter.client.main;

import com.pj.streetfighter.client.graphics.Bitmap;

public interface GameState
{
	
	public void onEnter();
	public void onExit();
	public void update(Game game);
	public void render(Bitmap map);
	
}
