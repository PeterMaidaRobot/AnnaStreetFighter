package com.pj.streetfighter.client.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{

	private boolean[] keys = new boolean[120];
	private final int jumpMap, crouchMap, leftMap, rightMap, blockMap, punchMap, kickMap;
	public boolean jump, crouch, left, right, block, punch, kick;
	
	public Keyboard()
	{
		// sets to the default key bindings
		jumpMap = KeyEvent.VK_UP;
		crouchMap = KeyEvent.VK_DOWN;
		leftMap = KeyEvent.VK_LEFT;
		rightMap = KeyEvent.VK_RIGHT;
		blockMap = KeyEvent.VK_Z;
		punchMap = KeyEvent.VK_X;
		kickMap = KeyEvent.VK_C;
	}
	
	public Keyboard(int upMap, int downMap, int leftMap, int rightMap, int zMap, int xMap, int cMap)
	{
		// sets to custom key bindings
		this.jumpMap = upMap;
		this.crouchMap = downMap;
		this.leftMap = leftMap;
		this.rightMap = rightMap;
		this.blockMap = zMap;
		this.punchMap = xMap;
		this.kickMap = cMap;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}
	
	public void update()
	{
		jump = keys[jumpMap];
		crouch = keys[crouchMap];
		left = keys[leftMap];
		right = keys[rightMap];
		block = keys[blockMap];
		punch = keys[punchMap];
		kick = keys[kickMap];
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e){}
}
