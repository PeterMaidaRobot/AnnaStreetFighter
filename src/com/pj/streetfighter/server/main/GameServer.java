package com.pj.streetfighter.server.main;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import com.pj.streetfighter.server.entity.Player;
import com.pj.streetfighter.server.packet.StatePacket;
import com.pj.streetfighter.server.state.ServerState;

public class GameServer extends Listener
{
	private static Server server;
	private static final int TCP_PORT = 60001;
	private static final int UDP_PORT = 60002;
	
	private static ServerState status = ServerState.WAITING_FOR_CONNECTION;
	private static Player p1 = new Player(null);
	private static Player p2 = new Player(null);
	
	public static void main (String[] args) throws IOException
	{
		// create and start server
		server = new Server();
		server.getKryo().register(StatePacket.class);
		server.bind(TCP_PORT, UDP_PORT);
		server.addListener(new GameServer());
		server.start();
		
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 1.0;
		double delta = 0;
		
		while (true)
		{
			long currTime = System.nanoTime();
			delta += (currTime - lastTime) / ns;
			lastTime = currTime;
			
			// updates capped at 30 times per second
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
		if (p1 != null && p1.c.equals(c))
		{
			p1.mostRecent = packet;
		}
		else if (p2 != null && p2.c.equals(c))
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
		
	}

	private static void updateSelection()
	{
		
	}

	private static void updateMenu()
	{
		if (p1.c != null && p2.c != null)
		{
			StatePacket goToSelection = new StatePacket();
			goToSelection.state = StatePacket.SELECTION;
			server.sendToAllTCP(goToSelection);
			status = ServerState.WAITING_IN_SELECTION;
		}
	}
}
