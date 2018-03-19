package com.pj.streetfighter.client.network;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ConnectionManager extends Listener implements Runnable 
{
	
	private Client client;
	public boolean doConnection = false;
	private Thread thread = new Thread(this, "ConnectionManager");
	public ConnectionStatus status = ConnectionStatus.NOT_CONNECTED;
	public static final int TCP_PORT = 60001;
	public static final int UDP_PORT = 60002;
	
	public ConnectionManager()
	{
		client  = new Client();
		client.getKryo().register(MenuPacket.class);
		client.addListener(this);
		client.start();
		thread.start();
	}
	
	public void received(Connection c, Object p)
	{
		
	}

	@Override
	public void run()
	{
		while(true)
		{
			if (doConnection)
			{
				
				try {
					System.out.println("Ummmm.... bet.");
					status = ConnectionStatus.CONNECTING;
					client.connect(1000, "130.215.169.203", TCP_PORT, UDP_PORT);
					status = ConnectionStatus.CONNECTED;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					status = ConnectionStatus.FAILED;
				}
				doConnection = false;
			}
		}
	}

}
