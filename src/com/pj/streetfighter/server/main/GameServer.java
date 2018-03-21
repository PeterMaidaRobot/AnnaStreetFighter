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
	
	public static void main (String[] args) throws IOException
	{
		server = new Server();
		server.getKryo().register(MenuPacket.class);
		server.bind(TCP_PORT, UDP_PORT);
		server.addListener(new GameServer());
		server.start();
	}
	
	public void connected(Connection c)
	{
		System.out.println("Connected!");
	}
	
	public void disconnected(Connection c)
	{
		
	}
	
	public void received(Connection c, Object packet)
	{
		
	}
}
