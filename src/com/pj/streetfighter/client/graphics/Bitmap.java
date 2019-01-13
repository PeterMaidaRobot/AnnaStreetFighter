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
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void render()
	{
		// render every pixel with a full alpha channel
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				pixels[x + y * width] = rand.nextInt() | 0xFF000000;
			}
		}
	}
	
	public void drawSprite(Sprite sprite, int x, int y)
	{
		drawSpriteWithOverlay(sprite, x, y, 0x00000000, false);
	}
	
	public void drawSprite(Sprite sprite, int x, int y, boolean flipped)
	{
		drawSpriteWithOverlay(sprite, x, y, 0x00000000, flipped);
	}
	
	public void drawSpriteWithOverlay(Sprite sprite, int x, int y, int overlay, boolean flipped)
	{
		// insert the given sprite into the bitmap's pixels array
		for (int yy = y; yy < y + sprite.getYSIZE(); yy++)
		{
			if (flipped)
			{
				for (int xx = x + sprite.getXSIZE(); xx > x; xx--)
				{
					// do not store an off screen pixel
					if (yy < 0 || yy >= height || xx < 0 || xx >= width)
					{
						continue;
					}
					// do not store this "transparent" pixel color in the array
					if (sprite.pixels[(xx - x) + (yy - y) * sprite.getXSIZE()] == 0xFFFF00FF)
					{
						continue;
					}
					
					pixels[xx + yy * width] = sprite.pixels[(xx - x) + (yy - y) * sprite.getXSIZE()] | overlay;
				}
			}
			else
			{
				for (int xx = x; xx < x + sprite.getXSIZE(); xx++)
				{
					// do not store an off screen pixel
					if (yy < 0 || yy >= height || xx < 0 || xx >= width)
					{
						continue;
					}
					// do not store this "transparent" pixel color in the array
					if (sprite.pixels[(xx - x) + (yy - y) * sprite.getXSIZE()] == 0xFFFF00FF)
					{
						continue;
					}
					
					pixels[xx + yy * width] = sprite.pixels[(xx - x) + (yy - y) * sprite.getXSIZE()] | overlay;
				}
			}
			
		}
	}
	/*
	 * reverse each row
	 * before off screen
	 * 
	 */
}
