package com.pj.streetfighter.client.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
	public final int NUM_KEYS = 130; // highest key is delete at 126?
	private boolean[] keys = new boolean[NUM_KEYS];
	private final int JUMP_MAP, CROUCH_MAP, LEFT_MAP, RIGHT_MAP, BLOCK_MAP, PUNCH_MAP, KICK_MAP;
	public boolean jump, crouch, left, right, block, punch, kick;
	
	public Keyboard()
	{
		// sets to the default key bindings
		JUMP_MAP = KeyEvent.VK_UP;
		CROUCH_MAP = KeyEvent.VK_DOWN;
		LEFT_MAP = KeyEvent.VK_LEFT;
		RIGHT_MAP = KeyEvent.VK_RIGHT;
		BLOCK_MAP = KeyEvent.VK_Z;
		PUNCH_MAP = KeyEvent.VK_X;
		KICK_MAP = KeyEvent.VK_C;
	}
	
	public Keyboard(int upMap, int downMap, int leftMap, int rightMap, int blockMap, int punchMap, int kickMap)
	{
		// sets to custom key bindings
		this.JUMP_MAP = upMap;
		this.CROUCH_MAP = downMap;
		this.LEFT_MAP = leftMap;
		this.RIGHT_MAP = rightMap;
		this.BLOCK_MAP = blockMap;
		this.PUNCH_MAP = punchMap;
		this.KICK_MAP = kickMap;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() < NUM_KEYS)
			keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() < NUM_KEYS)
			keys[e.getKeyCode()] = false;
	}
	
	public void update()
	{
		jump = keys[JUMP_MAP];
		crouch = keys[CROUCH_MAP];
		left = keys[LEFT_MAP];
		right = keys[RIGHT_MAP];
		block = keys[BLOCK_MAP];
		punch = keys[PUNCH_MAP];
		kick = keys[KICK_MAP];
	}
	
	public boolean isPressed(int keyCode)
	{
		return keys[keyCode];
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e){}
}
