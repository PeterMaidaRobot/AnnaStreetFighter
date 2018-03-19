package com.pj.streetfighter.client.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.input.Keyboard;
import com.pj.streetfighter.client.input.Mouse;
import com.pj.streetfighter.client.network.ConnectionManager;
import com.pj.streetfighter.client.state.Menu;

public class Game extends Canvas implements Runnable 
{
	private static final long serialVersionUID = 8728927276642518060L;

	private final int SCALE = 2;
	public final int WIDTH = 1280 / SCALE;
	public final int HEIGHT = 756 / SCALE;
	
	private JFrame frame;
	private Thread thread;
	private GameStateManager manager;
	
	public Mouse mouse;
	public Keyboard keyboard;
	public ConnectionManager connectionManager = new ConnectionManager();
	
	private Bitmap bitmap = new Bitmap(WIDTH, HEIGHT);
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	// TODO maybe should be replaced with game state logic later
	private boolean running;
	
	public Game()
	{
		thread = new Thread(this, "game");
		mouse = new Mouse();
		keyboard = new Keyboard();
		manager = new GameStateManager();
		manager.push(new Menu(WIDTH, HEIGHT));
		
		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		// JFrame initialization
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		this.setSize(size);
		frame = new JFrame("Anna's Street Fighter");
		frame.setMinimumSize(size);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setRunning(boolean isRunning)
	{
		running = isRunning;
	}
	
	public synchronized void start()
	{
		/* any code that needs to be executed before game runs */
		running = true;
		thread.start();
	}
	
	public synchronized void stop()
	{
		/* any code that needs to be executed before game closes */
		running = false;
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 120.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while (running)
		{
			long currTime = System.nanoTime();
			delta += (currTime - lastTime) / ns;
			lastTime = currTime;
			
			// updates capped at 60 times per second
			while (delta >= 1)
			{
				// do update() for given game state
				keyboard.update();
				manager.update(this);
				updates++;
				delta--;
			}
			
			// render whenever possible
			render();
			frames++;

			// update ups and fps every second
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				frame.setTitle("Anna's Street Fighter" + "  |  " + updates + " ups, "+ frames + " fps");
				updates = 0;
				frames = 0;
				
			}
		}
		this.stop();
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		// renders sprites from current game state into bitmap
		manager.render(bitmap);
		
		// get the pixels from the bitmap and store them for the game screen
		for (int i = 0; i < pixels.length; i++)
		{
			pixels[i] = bitmap.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		g.dispose();
		bs.show();
	}
	
	public static void main (String args[])
	{
		Game game = new Game();
		game.start();
	}
}
