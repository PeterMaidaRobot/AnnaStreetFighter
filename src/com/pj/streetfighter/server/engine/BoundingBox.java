package com.pj.streetfighter.server.engine;

public class BoundingBox {

	private int x1, y1, x2, y2;
	
	public BoundingBox(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public BoundingBox(BoundingBox boundingBox)
	{
		this.y1 = boundingBox.getTop();
		this.y2 = boundingBox.getBottom();			
		this.x1 = boundingBox.getLeft();
		this.x2 = boundingBox.getRight();
	}

	public int getTop()
	{
		return y1;
	}
	
	public int getBottom()
	{
		return y2;
	}
	
	public int getLeft()
	{
		return x1;
	}
	
	public int getRight() 
	{
		return x2;
	}
	
	public boolean contains(int x, int y)
	{
		// check whether point passed is within bounds
		return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
	}
	
	public boolean hasCollided(BoundingBox box)
	{
		return (box.contains(x1, y1) || box.contains(x1, y2) || box.contains(x2, y1) || box.contains(x2, y2));
	}
	
	public void addXOffset(int x)
	{
		x1 += x;
		x2 += x;
	}
	
	public void addYOffset(int y)
	{
		y1 += y;
		y2 += y;
	}
}
