package com.pj.streetfighter.client.main;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable 
{
	private static final long serialVersionUID = 8728927276642518060L;

	private Thread thread;
	
	// maybe should be replaced with game state logic later
	private boolean running;
	
	public Game()
	{
		thread = new Thread(this, "game");
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
		while (running)
		{
			
		}
	}
	
	public static void main (String args[])
	{
		Game game = new Game();
		game.start();
	}
}
