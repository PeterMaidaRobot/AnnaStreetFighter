package com.pj.streetfighter.client.main;

import java.util.ArrayDeque;
import java.util.Deque;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.state.Fight;
import com.pj.streetfighter.client.state.GameState;
import com.pj.streetfighter.client.state.Menu;
import com.pj.streetfighter.client.state.Selection;
import com.pj.streetfighter.network.StatePacket;

public class GameStateManager
{
	Deque<GameState> stack;
	
	public GameStateManager()
	{
		stack = new ArrayDeque<GameState>();
	}
	
	public void push(Game game, GameState state)
	{
		state.onEnter(game);
		stack.push(state);
	}
	
	public void pop()
	{
		if (!stack.isEmpty())
		{
			stack.peek().onExit();
			stack.pop();
		}
	}
	
	public void update(Game game)
	{
		if(!stack.isEmpty())
			stack.peek().update(game);
	}

	public void render(Bitmap bitmap) {
		if(!stack.isEmpty())
			stack.peek().render(bitmap);
		
	}
	
	public void receiveUpdate(Game game, StatePacket p)
	{
		if (stack.peek() instanceof Menu)
		{
			if (p.state == StatePacket.SELECTION)
			{
				this.push(game, new Selection(game.WIDTH, game.HEIGHT));
			} else if (p.state == StatePacket.FIGHT)
			{
				this.push(game, new Fight(game.WIDTH, game.HEIGHT));
			}
		}
		else if (stack.peek() instanceof Selection)
		{
			if (p.state == StatePacket.MENU)
			{
				this.pop();
			}
			else if (p.state == StatePacket.FIGHT)
			{
				this.push(game, new Fight(game.WIDTH, game.HEIGHT));
			}
		} else if (stack.peek() instanceof Fight)
		{
			if (p.state == StatePacket.SELECTION)
			{
				this.pop();
			}
			else if (p.state == StatePacket.MENU)
			{
				this.pop();
				this.pop();
			}
		}
	}
}
