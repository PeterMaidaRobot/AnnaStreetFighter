package com.pj.streetfighter.client.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener
{
	private int x, y;
	private boolean pressed;
	
	public Mouse()
	{
		x = 0;
		y = 0;
		pressed = false;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		pressed = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public boolean isPressed()
	{
		return pressed;
	}
	
	
	
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mouseClicked(MouseEvent e){}
}
