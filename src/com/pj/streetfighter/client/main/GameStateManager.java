package com.pj.streetfighter.client.main;

import java.util.ArrayDeque;
import java.util.Deque;

import com.pj.streetfighter.client.graphics.Bitmap;
import com.pj.streetfighter.client.input.Keyboard;
import com.pj.streetfighter.client.input.Mouse;
import com.pj.streetfighter.client.state.GameState;

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
	
	public void update(Mouse mouse, Keyboard keyboard)
	{
		if(!stack.isEmpty())
			stack.peek().update(mouse, keyboard);
	}

	public void render(Bitmap bitmap) {
		if(!stack.isEmpty())
			stack.peek().render(bitmap);
		
	}
}
