package com.pj.streetfighter.client.main;

import java.util.ArrayDeque;
import java.util.Deque;

import com.pj.streetfighter.client.graphics.Bitmap;

public class GameStateManager
{
	Deque<GameState> stack;
	
	public GameStateManager()
	{
		stack = new ArrayDeque<GameState>();
	}
	
	public void push(GameState state)
	{
		state.onEnter();
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
}
