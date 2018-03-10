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
				pixels[x + y * width] = rand.nextInt();
			}
		}
	}
}
