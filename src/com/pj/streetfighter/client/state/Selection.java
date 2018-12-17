package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.main.Game;
import com.pj.streetfighter.network.StatePacket;

public class Selection extends GameState{

	public Selection(int width, int height)
	{
		super(width, height);
	}

	@Override
	public void onEnter(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Game game) {
		// TODO Auto-generated method stub
		
		Object packet = game.connectionManager.mostRecentPacket;
		if (packet instanceof StatePacket)
		{
			game.manager.receiveUpdate(game, (StatePacket) packet);
		}
	}

	@Override
	public void render(Bitmap map) {
		// TODO Auto-generated method stub
		
	}

}
