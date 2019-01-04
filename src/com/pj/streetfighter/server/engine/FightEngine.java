package com.pj.streetfighter.server.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
		p1 = new Player(new Prometheus(), 50, 50);
		p2 = new Player(new Prometheus(), 500, 50);
		this.stage = stage;
	}
	
	public ServerFightPacket updateFight(Object p1Packet, Object p2Packet)
	{
		// get input
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
		
		p1Collision.setCollisions(p1PlatformCollisions);
		p2Collision.setCollisions(p2PlatformCollisions);
		
		snapToEdge(p1, p1Collision);
		snapToEdge(p2, p2Collision);
		
		
		// UPDATE STATE OBJECT
		updateState(p1, p1Collision);
		updateState(p2, p2Collision);
		
		p1.setY((int) Math.ceil(p1.getY() + p1.getYVel()));
		p2.setY((int) Math.ceil(p2.getY() + p2.getYVel()));
		
		// perform input based on collisions
		if (p1IsFightPacket)
		{
			applyPlayerInput(p1, p1Collision, p1Input);
		}
		
		if (p2IsFightPacket)
		{
			applyPlayerInput(p2, p2Collision, p2Input);
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

	public void snapToEdge(Player p, Collision pCollision)
	{
		if (!pCollision.anyCollisions())
		{
			return;
		}
		
		if (pCollision.up)
		{
			p.setY(p.getY() + (pCollision.farthestUp));
		}
		if (pCollision.down)
		{
			p.setY(p.getY() - (pCollision.farthestDown));
		}
		if (pCollision.left)
		{
			p.setX(p.getX() + (pCollision.farthestLeft));
		}
		if (pCollision.right)
		{
			p.setX(p.getX() - (pCollision.farthestRight));
		}
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
	
	private void applyGravity(Player p)
	{
		// bound it within the MAX_Y_VEL in both positive and negative direction
		double newYVel = Math.min(Player.MAX_Y_VEL, p.getYVel() + stage.getGravity());
		newYVel = Math.max(-1 * Player.MAX_Y_VEL, newYVel);
		
		if (newYVel >= 0 && p.isJumping()) {
			p.setFalling();
		}
		
		p.setYVel(newYVel);
	}
	
	private void updateState(Player p, Collision pCollision)
	{
		if (pCollision.down && p.isFalling()) 
		{
			p.setGrounded();
		}
		
		// if the player is in the sky and they did not jump
		// (the engine hasn't recognized it yet)
		if (!pCollision.down && !p.isAirborne())
		{
			p.setFalling();
		}
		
		if (p.isAirborne()) {
			applyGravity(p);
		}
		
		// jumping is going upwards (vel < 0), falling is going downwards (vel > 0)
		if (pCollision.up && p.isJumping()) 
		{
			p.setFalling();
		}
	}
	
	private void applyPlayerInput(Player p, Collision pCollision, short pInput)
	{
		if ((pInput & FightPacketDictionary.LEFT) != 0 && !pCollision.left)
		{
			p.incrementX(p.getCharacter().getBaseSpeed() * -1);
		} 
		if ((pInput & FightPacketDictionary.RIGHT) != 0 && !pCollision.right)
		{
			p.incrementX(p.getCharacter().getBaseSpeed());
		}
		if ((pInput & FightPacketDictionary.JUMP) != 0 && pCollision.down)
		{
			p.setJumping();
		}
	}
}
