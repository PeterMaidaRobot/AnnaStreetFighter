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
		this.x = x;
		this.y = y;
		this.pixels = new int[X_SIZE * Y_SIZE];
		this.sheet = sheet;
		load();
	}
	
	public Sprite (Sprite sprite)
	{
		this.X_SIZE = sprite.X_SIZE;
		this.Y_SIZE = sprite.Y_SIZE;
		this.x = sprite.x;
		this.y = sprite.y;
		this.pixels = new int[X_SIZE * Y_SIZE];
		System.arraycopy(sprite.pixels, 0, this.pixels, 0, sprite.pixels.length);
		this.sheet = sprite.sheet;
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
	
	public void addSprite(Sprite sprite, int x, int y)
	{
		// insert the given sprite into the bitmap's pixels array
		for (int yy = y; yy < y + sprite.getYSIZE(); yy++)
		{
			for (int xx = x; xx < x + sprite.getXSIZE(); xx++)
			{
				// do not store an off screen pixel
				if (yy < 0 || yy > Y_SIZE || xx < 0 || xx > X_SIZE)
				{
					continue;
				}
				// do not store this "transparent" pixel color in the array
				if (sprite.pixels[(xx - x) + (yy - y) * sprite.getXSIZE()] == 0xFFFF00FF)
				{
					continue;
				}
				
				pixels[xx + yy * X_SIZE] = sprite.pixels[(xx - x) + (yy - y) * sprite.getXSIZE()];
			}
		}
		
	}
}
