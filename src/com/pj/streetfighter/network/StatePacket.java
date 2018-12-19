package com.pj.streetfighter.network;

public class StatePacket
{
	public static final byte SELECTION = 1;
	public static final byte FIGHT = 2;
	public static final byte MENU = 3;
	
	public byte state;
	
	public StatePacket() {}
	
	public StatePacket(byte state)
	{
		this.state = state;
	}
}
