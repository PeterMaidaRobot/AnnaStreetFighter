package com.pj.streetfighter.client.main;

public interface GameState
{
	
	public void onEnter();
	public void onExit();
	public void update(Game game);
	
}
