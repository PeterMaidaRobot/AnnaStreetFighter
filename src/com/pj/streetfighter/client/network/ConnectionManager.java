package com.pj.streetfighter.client.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.pj.streetfighter.network.ClientFightPacket;
import com.pj.streetfighter.network.ServerFightPacket;
import com.pj.streetfighter.network.StatePacket;

public class ConnectionManager extends Listener implements Runnable 
{
	
	private Client client;
	private static final int TCP_PORT = 60001;
	private static final int UDP_PORT = 60002;
	
	public ConnectionStatus status = ConnectionStatus.NOT_CONNECTED;
	public String serverIP = null;
	
	public Object mostRecentPacket = null; 
	
	public ConnectionManager()
	{
		client  = new Client();
		client.getKryo().register(StatePacket.class);
		client.getKryo().register(ServerFightPacket.class);
		client.getKryo().register(ClientFightPacket.class);
		client.addListener(this);
		client.start();
	}
	
	public void received(Connection c, Object p)
	{
		if (isGamePacket(p))
		{
			mostRecentPacket = p;
		}
	}
	
	public void sendFightPacket(ClientFightPacket p)
	{
		client.sendUDP(p);
	}

	@Override
	public void run()
	{
		try
		{
			status = ConnectionStatus.CONNECTING;
			client.connect(10000, serverIP, TCP_PORT, UDP_PORT);
			status = ConnectionStatus.CONNECTED;
		}
		catch (Exception e)
		{
			status = ConnectionStatus.FAILED;
		}	
		System.out.println(status.toString());
	}
	
	public boolean canConnect()
	{
		return !(status == ConnectionStatus.CONNECTING || status == ConnectionStatus.CONNECTED);
	}
	
	public boolean isConnected()
	{
		return status == ConnectionStatus.CONNECTED;
	}
	
	public boolean isGamePacket(Object p)
	{
		return p instanceof StatePacket || p instanceof ServerFightPacket;
	}
}
