package com.pj.streetfighter.characters;

import com.pj.streetfighter.characters.Animation;

public abstract class Character {
	
	private final Animation[] animations;
	private final int baseSpeed;

	public Character(Animation[] animations, int baseSpeed)
	{
		this.animations = animations;
		this.baseSpeed = baseSpeed;
	}

	public Animation getAnimation(int i)
	{
		return animations[i];
	}
	
	public int getBaseSpeed()
	{
		return baseSpeed;
	}
}