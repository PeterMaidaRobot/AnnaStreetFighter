package com.pj.streetfighter.server.engine;

import java.util.ArrayList;
import java.util.HashMap;

public class Collision
{
	public static final int TOP = 1;
	public static final int LEFT = 2;
	public static final int BOTTOM = 3;
	public static final int RIGHT = 4;
	
	public boolean up = false;
	public int farthestUp = -1;
	public boolean left = false;
	public int farthestLeft = -1;
	public boolean down = false;
	public int farthestDown = -1;
	public boolean right = false;
	public int farthestRight = -1;
	
	public boolean anyCollisions()
	{
		return up || left || down || right;
	}
	
	public void addCollision(int side, int value)
	{
		if (side == TOP && (value > farthestUp || up == false))
		{
			up = true;
			farthestUp = value;
		} 
		else if (side == BOTTOM && (value > farthestDown || down == false))
		{
			down = true;
			farthestDown = value;
		}
		else if (side == LEFT && (value > farthestLeft || left == false))
		{
			left = true;
			farthestLeft = value;
		}
		else if (side == RIGHT && (value > farthestRight || right == false))
		{
			right = true;
			farthestRight = value;
		}
	}
	
	public void setCollisions(HashMap<BoundingBox, ArrayList<BoundingBox>> pCollisions)
	{
		for (BoundingBox platform : pCollisions.keySet())
		{
			ArrayList<BoundingBox> collidedBoxes = pCollisions.get(platform);
			for (BoundingBox playerBox : collidedBoxes)
			{
				int topCollision = playerBox.getTop() - platform.getBottom();	
				int bottomCollision = platform.getTop() - playerBox.getBottom();
				int leftCollision = playerBox.getLeft() - platform.getRight();
				int rightCollision = platform.getLeft() - playerBox.getRight();
				
				// finds the minimum of all collision values
				int deepest = Math.min(bottomCollision, Math.min(topCollision, Math.min(leftCollision, rightCollision)));
				
				
				if (topCollision == deepest)                        
				{
					this.addCollision(Collision.TOP, Math.abs(topCollision));
				}
				else if (bottomCollision == deepest)
				{                           
					this.addCollision(Collision.BOTTOM, Math.abs(bottomCollision));
				}
				else if (leftCollision == deepest)
				{
					this.addCollision(Collision.LEFT, Math.abs(leftCollision));
				}
				else if (rightCollision == deepest)
				{
					this.addCollision(Collision.RIGHT, Math.abs(rightCollision));
				}
			}
		}
	}
}
