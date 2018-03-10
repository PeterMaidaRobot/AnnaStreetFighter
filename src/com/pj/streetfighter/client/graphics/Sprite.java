package com.pj.streetfighter.client.graphics;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {

		private int x;
		private int y;
		private int width;
		private int height;
		private Image image;
		
		public Sprite(String path, int x, int y)
		{
			this.x = x;
			this.y = y;
			ImageIcon ii = new ImageIcon(path);
			this.image = ii.getImage();
			this.width = this.image.getWidth(null);
			this.height = this.image.getHeight(null);
		}
		
		public int getX()
		{
			return x;
		}
		
		public int getY()
		{
			return y;
		}
		
		public Image getImage()
		{
			return image;
		}
}
