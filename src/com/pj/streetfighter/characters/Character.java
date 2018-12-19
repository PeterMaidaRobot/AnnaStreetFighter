package com.pj.streetfighter.characters;

import com.pj.streetfighter.characters.Animation;

public abstract class Character {
	
	private final Animation[] animations;

	public Character(Animation[] animations)
	{
		this.animations = animations;
	}

	public Animation getAnimation(int i)
	{
		return animations[i];
	}
}