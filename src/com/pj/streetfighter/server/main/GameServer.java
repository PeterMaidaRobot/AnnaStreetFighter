package com.pj.streetfighter.server.main;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.pj.streetfighter.network.ClientFightPacket;
import com.pj.streetfighter.network.FightPacketDictionary;
import com.pj.streetfighter.network.ServerFightPacket;
import com.pj.streetfighter.network.StatePacket;
import com.pj.streetfighter.server.entity.Player;
import com.pj.streetfighter.server.state.ServerState;

public class GameServer extends Listener
{
	private static Server server;
	private static final int TCP_PORT = 60001;
	private static final int UDP_PORT = 60002;
	
	private static ServerState status = ServerState.WAITING_FOR_CONNECTION;
	private static Player p1 = new Player(null, 50, 243);
	private static Player p2 = new Player(null, 550, 243);
	
	public static void main (String[] args) throws IOException
	{
		// create and start server
		server = new Server(); //free
		server.getKryo().register(StatePacket.class);
		server.getKryo().register(ServerFightPacket.class);
		server.getKryo().register(ClientFightPacket.class);
		server.bind(TCP_PORT, UDP_PORT);
		server.addListener(new GameServer());
		server.start();
		
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		
		while (true)
		{
			long currTime = System.nanoTime();
			delta += (currTime - lastTime) / ns;
			lastTime = currTime;
			
			// updates capped at 60 times per second
			while (delta >= 1)
			{
				update();
				delta--;
			}
		}
	}

	public void connected(Connection c)
	{
		if (p1.c == null)
		{
			p1.c = c;
			System.out.println("set player 1");
		}
		else if (p2.c == null)
		{
			p2.c = c;
			System.out.println("set player 2");
		}
		
	}
	
	public void disconnected(Connection c)
	{
		System.out.println("disconnecting...");
		if (p1.c != null && p1.c.equals(c))
		{
			p1.c = null;
		}
		else if (p2.c != null && p2.c.equals(c))
		{
			p2.c = null;
		}
		StatePacket goToMenu = new StatePacket();
		goToMenu.state = StatePacket.MENU;
		server.sendToAllTCP(goToMenu);
	}
	
	public void received(Connection c, Object packet)
	{
		if (p1.c != null && p1.c.equals(c))
		{
			p1.mostRecent = packet;
		}
		else if (p2.c != null && p2.c.equals(c))
		{
			p2.mostRecent = packet;
		}
	}
	
	private static void update()
	{		
		switch (status)
		{
			case WAITING_FOR_CONNECTION:
				updateMenu();
			case WAITING_IN_SELECTION:
				updateSelection();
			case FIGHT:
				updateFight();
		}
	}
	
	private static void updateFight()
	{
		Object p1Packet = p1.mostRecent;
		Object p2Packet = p2.mostRecent;
		
		if (p1Packet instanceof ClientFightPacket)
		{
			short input = ((ClientFightPacket) p1.mostRecent).keyboardInput;
			if ((input & FightPacketDictionary.LEFT) != 0)
			{
				p1.x--;
			} 
			if ((input & FightPacketDictionary.RIGHT) != 0)
			{
				p1.x++;
			}
		}
		if (p2Packet instanceof ClientFightPacket)
		{
			short input = ((ClientFightPacket) p2.mostRecent).keyboardInput;
			if ((input & FightPacketDictionary.LEFT) != 0)
			{
				p2.x--;
			} 
			if ((input & FightPacketDictionary.RIGHT) != 0)
			{
				p2.x++;
			}
		}
		
		ServerFightPacket packet = new ServerFightPacket();
		packet.p1x = (short) p1.x;
		packet.p1y = (short) p1.y;
		packet.p2x = (short) p2.x;
		packet.p2y = (short) p2.y;
		packet.gameStatus = (byte) 1;
		packet.p1p2health = (byte) 1;
		packet.P1statusP2status = (short) 1;
		server.sendToAllTCP(packet);
	}

	private static void updateSelection()
	{
		
	}

	private static void updateMenu()
	{
		if (p1.c != null && p2.c != null)
		{
			StatePacket goToSelection = new StatePacket();
			goToSelection.state = StatePacket.FIGHT;
			server.sendToAllTCP(goToSelection);
			status = ServerState.FIGHT;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
