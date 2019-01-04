package com.pj.streetfighter.network;

public class FightPacketDictionary
{	
	public static final short JUMP = 0b01000000;
	public static final short CROUCH = 0b00100000;
	public static final short LEFT = 0b00010000;
	public static final short RIGHT = 0b00001000;
	public static final short BLOCK = 0b00000100;
	public static final short PUNCH = 0b00000010;
	public static final short KICK = 0b000000001;
}
