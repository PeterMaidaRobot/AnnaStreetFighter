package com.pj.streetfighter.client.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet
{
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public SpriteSheet(String path, int size)
	{
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	private void load()
	{
		try
		{
			// loads the image from the initialized path
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

}