package com.pj.streetfighter.client.graphics;

import java.util.Random;

public class Bitmap
{
	private int width;
	private int height;
	public int[] pixels;
	private Random rand = new Random();
	
	public Bitmap(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		pixels = new int[this.width * this.height];
	}
	
	public void render()
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				pixels[x + y * width] = rand.nextInt() | 0xFF000000;
			}
		}
	}
	
	// need a method for drawing a sprite at a given x, y location
	public void drawSprite(Sprite sprite, int x, int y)
	{
		drawSpriteWithOverlay(sprite, x, y, 0x00000000);
	}
	
	public void drawSpriteWithOverlay(Sprite sprite, int x, int y, int overlay)
	{
		for(int yy = y; yy < y + sprite.getYSIZE(); yy++)
		{
			for(int xx = x; xx < x + sprite.getXSIZE(); xx++)
			{
				if (yy < 0 || yy > height || xx < 0 || xx > width)
				{
					continue;
				}
				pixels[xx + yy * width] = sprite.pixels[(xx - x) + (yy - y) * sprite.getXSIZE()] | overlay;
			}
		}
	}
}
