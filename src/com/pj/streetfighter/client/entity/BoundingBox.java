package com.pj.streetfighter.client.entity;

public class BoundingBox {

	private int x1, y1, x2, y2;
	
	public BoundingBox(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public boolean contains(int x, int y)
	{
		return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
	}
}
