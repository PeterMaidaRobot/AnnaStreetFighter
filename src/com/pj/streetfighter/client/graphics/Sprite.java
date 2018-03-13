package com.pj.streetfighter.client.graphics;

public class Sprite {

	private final int X_SIZE, Y_SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		this(size, size, x, y, sheet);
	}
	
	public Sprite(int xSize, int ySize, int x, int y, SpriteSheet sheet)
	{
		this.X_SIZE = xSize;
		this.Y_SIZE = ySize;
		this.x = x * X_SIZE;
		this.y = y * Y_SIZE;
		this.pixels = new int[X_SIZE * Y_SIZE];
		this.sheet = sheet;
		load();
	}
	
	private void load()
	{
		for (int y = 0; y < Y_SIZE; y++)
		{
			for (int x = 0; x < X_SIZE; x++)
			{
				pixels[x + y * X_SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getXSIZE()
	{
		return X_SIZE;
	}
	
	public int getYSIZE()
	{
		return Y_SIZE;
	}
}
