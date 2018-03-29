package com.pj.streetfighter.network;

public class FightPacketDictionary
{
	public static final byte IDLE = 0b000;
	public static final byte RUNNING = 0b001;
	public static final byte JUMPING = 0b010;
	public static final byte PUNCHING = 0b011;
	public static final byte KICKING = 0b100;
	public static final byte BLOCKING = 0b101;
	public static final byte GOT_HIT = 0b110;
	public static final byte KNOCKED_OUT = 0b111;
	public static final byte FACE_RIGHT = 0b1000;
	
	public static final short JUMP = 0b01000000;
	public static final short CROUCH = 0b00100000;
	public static final short LEFT = 0b00010000;
	public static final short RIGHT = 0b00001000;
	public static final short BLOCK = 0b00000100;
	public static final short PUNCH = 0b00000010;
	public static final short KICK = 0b000000001;
}
