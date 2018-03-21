package com.pj.streetfighter.server.main;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import com.pj.streetfighter.client.network.MenuPacket;
import com.pj.streetfighter.server.entity.Player;

public class GameServer extends Listener
{
	private static Server server;
	private static final int TCP_PORT = 60001;
	private static final int UDP_PORT = 60002;
	
	private Player p1 = null;
	private Player p2 = null;
	
	public static void main (String[] args) throws IOException
	{
		// create and start server
		server = new Server();
		server.getKryo().register(MenuPacket.class);
		server.bind(TCP_PORT, UDP_PORT);
		server.addListener(new GameServer());
		server.start();
		
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 30.0;
		double delta = 0;
		
		while (true)
		{
			long currTime = System.nanoTime();
			delta += (currTime - lastTime) / ns;
			lastTime = currTime;
			
			// updates capped at 30 times per second
			while (delta >= 1)
			{
				if (server.getConnections().length == 1)
					update();
				delta--;
			}
		}
	}

	public void connected(Connection c)
	{
		if (p1 == null)
		{
			p1 = new Player(c);
		}
		else if (p2 == null)
		{
			p2 = new Player(c);
		}
	}
	
	public void disconnected(Connection c)
	{
		if (p1 != null && p1.c.equals(c))
		{
			p1 = null;
		}
		else if (p2 != null && p2.c.equals(c))
		{
			p2 = null;
		}
	}
	
	public void received(Connection c, Object packet)
	{
		
	}
	
	private static void update()
	{
		MenuPacket packet = new MenuPacket();
		server.sendToAllUDP(packet);
	}
	
	private static void sendFightPacket()
	{
		
	}
}
