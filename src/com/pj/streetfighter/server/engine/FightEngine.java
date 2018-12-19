package com.pj.streetfighter.server.engine;

import java.util.ArrayList;
import java.util.HashMap;

import com.pj.streetfigher.stages.Stage;
import com.pj.streetfighter.characters.Prometheus;
import com.pj.streetfighter.network.ClientFightPacket;
import com.pj.streetfighter.network.FightPacketDictionary;
import com.pj.streetfighter.network.ServerFightPacket;

public class FightEngine
{
	private Player p1;
	private Player p2;
	private Stage stage;
	
	public FightEngine(Stage stage)
	{
		p1 = new Player(new Prometheus(), 50, 243);
		p2 = new Player(new Prometheus(), 550, 243);
		this.stage = stage;
	}
	
	public ServerFightPacket updateFight(Object p1Packet, Object p2Packet)
	{
		boolean p1IsFightPacket = p1Packet instanceof ClientFightPacket;
		boolean p2IsFightPacket = p2Packet instanceof ClientFightPacket;
		short p1Input = 0, p2Input = 0;
		if (p1IsFightPacket)
		{
			p1Input = ((ClientFightPacket) p1Packet).keyboardInput;
		}
		if (p2IsFightPacket)
		{
			p2Input = ((ClientFightPacket) p2Packet).keyboardInput;
		}
		
		// check collisions against platforms
		HashMap<BoundingBox, ArrayList<BoundingBox>> p1PlatformCollisions = getPlayerCollisions(p1, stage.getPlatforms());
		HashMap<BoundingBox, ArrayList<BoundingBox>> p2PlatformCollisions = getPlayerCollisions(p2, stage.getPlatforms());
		
		Collision p1Collision = new Collision();
		Collision p2Collision = new Collision();
		
		for (int i = 0; i < p1PlatformCollisions.size(); i++)
		{
			
		}
		
		// get input
		
		// perform input based on collisions
		
		
		// check if both players are touching bounding box on bottom (list of bounding boxes)
		// move them down to a bounding box (don't jump past it)
		// move them left/right *LEFT RIGHT MOVEMENT COULD STILL BE BOUNDING BOX!*
		
		
		
		// if it will be in the box, move it until it is touching the edge
		
		
		
		if (p1IsFightPacket)
		{
			if ((p1Input & FightPacketDictionary.LEFT) != 0)
			{
				p1.incrementX(-1);
			} 
			if ((p1Input & FightPacketDictionary.RIGHT) != 0)
			{
				p1.incrementX(1);
			}
		}
		
		if (p2IsFightPacket)
		{
			if ((p2Input & FightPacketDictionary.LEFT) != 0)
			{
				p2.incrementX(-1);
			} 
			if ((p2Input & FightPacketDictionary.RIGHT) != 0)
			{
				p2.incrementX(1);
			}
		}
		
		ServerFightPacket packet = new ServerFightPacket();
		packet.p1x = (short) p1.getX();
		packet.p1y = (short) p1.getY();
		packet.p2x = (short) p2.getX();
		packet.p2y = (short) p2.getY();
		packet.gameStatus = (byte) 1;
		packet.p1p2health = (byte) 1;
		packet.p1Sprite = (byte) 0;
		packet.p2Sprite = (byte) 0;

		return packet;
	}

	private HashMap<BoundingBox, ArrayList<BoundingBox>> getPlayerCollisions(Player p, BoundingBox[] boundingBoxes)
	{
		HashMap<BoundingBox, ArrayList<BoundingBox>> collisions = new HashMap<BoundingBox, ArrayList<BoundingBox>>();	
		BoundingBox[] pBoundingBoxes = p.getBoundingBoxes();
		
		for (int i = 0; i < pBoundingBoxes.length; i++)
		{
			for (int j = 0; j < boundingBoxes.length; j++)
			{
				if (pBoundingBoxes[i].hasCollided(boundingBoxes[j])) 
				{
					if (!collisions.containsKey(boundingBoxes[j]))
					{
						collisions.put(boundingBoxes[j], new ArrayList<BoundingBox>());
					}
					collisions.get(boundingBoxes[j]).add(pBoundingBoxes[i]);
				}
			}
		}
		
		return collisions;
	}
}
