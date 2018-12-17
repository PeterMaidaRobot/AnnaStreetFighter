package com.pj.streetfighter.client.state;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.client.input.Keyboard;
import com.pj.streetfighter.client.main.Game;
import com.pj.streetfighter.network.ClientFightPacket;
import com.pj.streetfighter.network.FightPacketDictionary;
import com.pj.streetfighter.network.ServerFightPacket;
import com.pj.streetfighter.server.engine.Meadow;
import com.pj.streetfighter.server.engine.Stage;

public class Fight extends GameState{

	Stage stage = new Meadow();
	
	SpriteSheet player1Sheet = new SpriteSheet("/character_sprites/prometheus.png", 64);
	Sprite player1 = new Sprite(64, 64, 0, 0, player1Sheet);
	Sprite player2 = new Sprite(64, 64, 0, 0, player1Sheet);
	
	ServerFightPacket packet = null;
	
	public Fight(int width, int height)
	{
		super(width, height);
	}

	@Override
	public void onEnter(Game game)
	{
		
	}

	@Override
	public void onExit()
	{
		
	}

	@Override
	public void update(Game game)
	{	
		// create the client fight packet from the keyboard presses
		Keyboard keyboard = game.keyboard;
		ClientFightPacket sendingPacket = new ClientFightPacket();
		short keysPressed = 0;
		if (keyboard.jump)
			keysPressed = (short) (keysPressed | FightPacketDictionary.JUMP);
		if (keyboard.crouch)
			keysPressed = (short) (keysPressed | FightPacketDictionary.CROUCH);
		if (keyboard.left)
			keysPressed = (short) (keysPressed | FightPacketDictionary.LEFT);
		if (keyboard.right)
			keysPressed = (short) (keysPressed | FightPacketDictionary.RIGHT);
		if (keyboard.block)
			keysPressed = (short) (keysPressed | FightPacketDictionary.BLOCK);
		if (keyboard.punch)
			keysPressed = (short) (keysPressed | FightPacketDictionary.PUNCH);
		if (keyboard.kick)
			keysPressed = (short) (keysPressed | FightPacketDictionary.KICK);
		
		// set the client fight packet and send it
		sendingPacket.keyboardInput = keysPressed;
		game.connectionManager.sendFightPacket(sendingPacket);
		
		// updates the players if a packet has been received
		if (game.connectionManager.mostRecentPacket instanceof ServerFightPacket)
		{
			packet = (ServerFightPacket) game.connectionManager.mostRecentPacket;
			player1.setX(packet.p1x);
			player1.setY(packet.p1y);
			player2.setX(packet.p2x);
			player2.setY(packet.p2y);
		}
		
	}

	@Override
	public void render(Bitmap map)
	{
		map.drawSprite(stage.getBackground(), 0, 0);
		map.drawSprite(player1, player1.getX(), player1.getY());
		map.drawSprite(player2, player2.getX(), player2.getY());
		
	}

}
