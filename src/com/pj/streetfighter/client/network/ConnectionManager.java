package com.pj.streetfighter.client.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ConnectionManager extends Listener implements Runnable 
{
	
	private Client client;
	public ConnectionStatus status = ConnectionStatus.NOT_CONNECTED;
	public static final int TCP_PORT = 60001;
	public static final int UDP_PORT = 60002;
	public String serverIP = null;
	
	public ConnectionManager()
	{
		client  = new Client();
		client.getKryo().register(MenuPacket.class);
		client.addListener(this);
		client.start();
	}
	
	public void received(Connection c, Object p)
	{
		
	}

	@Override
	public void run()
	{
		try
		{
			status = ConnectionStatus.CONNECTING;
			client.connect(10000, "130.215.8.130", TCP_PORT, UDP_PORT);
			status = ConnectionStatus.CONNECTED;
		}
		catch (Exception e)
		{
			status = ConnectionStatus.FAILED;
		}		
	}
	
	public boolean canConnect()
	{
		// want a nice boolean for if the connection manager will allow connections
		// but can't happen if buttons stays selected when not hovered because
		// game will automatically try to reconnect after failing
		return !(status == ConnectionStatus.CONNECTING || status == ConnectionStatus.CONNECTED);
	}
}
