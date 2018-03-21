package com.pj.streetfighter.server.main;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import com.pj.streetfighter.client.network.MenuPacket;

public class GameServer extends Listener
{
	private static Server server;
	private static final int TCP_PORT = 60001;
	private static final int UDP_PORT = 60002;
	private static int counter = 0;
	
	public static void main (String[] args) throws IOException
	{
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
			
			// updates capped at 60 times per second
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
		System.out.println("Connected!");
	}
	
	public void disconnected(Connection c)
	{
		System.out.println("Disconnected!");
	}
	
	public void received(Connection c, Object packet)
	{
		
	}
	
	private static void update()
	{
		MenuPacket packet = new MenuPacket();
		packet.offset = counter;
		server.sendToAllUDP(packet);
		counter++;
	}
}
