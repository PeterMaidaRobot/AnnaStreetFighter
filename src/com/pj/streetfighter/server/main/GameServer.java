package com.pj.streetfighter.server.main;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.pj.streetfigher.stages.Meadow;
import com.pj.streetfighter.network.ClientFightPacket;
import com.pj.streetfighter.network.ServerFightPacket;
import com.pj.streetfighter.network.StatePacket;
import com.pj.streetfighter.server.engine.FightEngine;
import com.pj.streetfighter.server.entity.PlayerConnection;
import com.pj.streetfighter.server.state.ServerState;

public class GameServer extends Listener
{
	private static Server server;
	private static final int TCP_PORT = 60001;
	private static final int UDP_PORT = 60002;
	
	private static ServerState status = ServerState.WAITING_FOR_CONNECTION;
	private static PlayerConnection p1 = new PlayerConnection(null);
	private static PlayerConnection p2 = new PlayerConnection(null);
	
	private static FightEngine engine = null;
	
	public static void main (String[] args) throws IOException
	{
		// create and start server
		server = new Server();
		server.getKryo().register(StatePacket.class);
		server.getKryo().register(ServerFightPacket.class);
		server.getKryo().register(ClientFightPacket.class);
		server.bind(TCP_PORT, UDP_PORT);
		server.addListener(new GameServer());
		server.start();
		
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		
		// main server loop
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

	// --------------------------------------------------------------------- //
	
	private static void idle()
	{
		try
		{
			Thread.sleep(500);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	// ===================================================================== //
	//					       CONNECTION METHODS						   //
	// ===================================================================== //
	
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
	
	// --------------------------------------------------------------------- //
	
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
		
		StatePacket goToMenu = new StatePacket(StatePacket.MENU);
		server.sendToAllTCP(goToMenu);
	}
	
	// --------------------------------------------------------------------- //
	
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
	
	// --------------------------------------------------------------------- //
	
	private static boolean playersConnected()
	{
		return p1.c != null && p2.c != null;
	}
	
	// ===================================================================== //
	//					          UPDATE METHODS			 				   //
	// ===================================================================== //
		
	private static void update()
	{
		switch (status)
		{
			case WAITING_FOR_CONNECTION:
				updateMenu();
				break;
			case WAITING_IN_SELECTION:
				updateSelection();
				break;
			case FIGHT:
				updateFight();
				break;
		}
	}
	
	// --------------------------------------------------------------------- //

	private static void updateMenu()
	{
		if (playersConnected())
		{
			status = ServerState.WAITING_IN_SELECTION;
			
			StatePacket goToSelection = new StatePacket(StatePacket.SELECTION);
			server.sendToAllTCP(goToSelection);
			
			idle();
		}
	}
	
	// --------------------------------------------------------------------- //
	
	private static void updateSelection()
	{
		if (playersConnected())
		{
			status = ServerState.FIGHT;
			engine = new FightEngine(new Meadow());
			
			StatePacket goToFight = new StatePacket(StatePacket.FIGHT);
			server.sendToAllTCP(goToFight);
			
			idle();
		}
	}
	
	// --------------------------------------------------------------------- //
	
	private static void updateFight()
	{
		Object p1Packet = p1.mostRecent;
		Object p2Packet = p2.mostRecent;
		
		ServerFightPacket packet = engine.updateFight(p1Packet, p2Packet);
		server.sendToAllTCP(packet);
	}
}
