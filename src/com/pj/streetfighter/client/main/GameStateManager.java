package com.pj.streetfighter.client.main;

import java.util.ArrayDeque;
import java.util.Deque;

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
	
	public void update()
	{
		if(!stack.isEmpty())
			stack.peek().update();
	}
}
