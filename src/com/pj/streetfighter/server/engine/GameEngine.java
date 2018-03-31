package com.pj.streetfighter.server.engine;

import com.pj.streetfighter.network.ClientFightPacket;
import com.pj.streetfighter.network.FightPacketDictionary;
import com.pj.streetfighter.server.entity.Player;

public class GameEngine {

	private Player p1;
	private Player p2;
	// combatEngine
	
	public GameEngine(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void updatePlayer(Player p) {
		short input = ((ClientFightPacket) p.mostRecent).keyboardInput;
		if ((input & FightPacketDictionary.LEFT) != 0)
		{
			p.physics.setXAccel(-4);
		} else {
			p.physics.setXAccel(0);
		}
		if ((input & FightPacketDictionary.RIGHT) != 0)
		{
			p.physics.setXAccel(4);
		} else {
			p.physics.setXAccel(0);
		}
	}
	
	public void updateEnvironment() {
		// update friction
		p1.physics.updateFriction();
		p2.physics.updateFriction();
		
		// update velocities from acceleration
		p1.physics.updateVelocities();
		p2.physics.updateVelocities();
		
		// update positions from velocities
		p1.x += p1.physics.getXVel();
		p1.x += p1.physics.getYVel();
		p2.x += p1.physics.getXVel();
		p2.x += p1.physics.getYVel();
	}
	
}
