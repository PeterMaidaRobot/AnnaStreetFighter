package com.pj.streetfigher.stages;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.server.engine.BoundingBox;

public abstract class Stage {
	
	private final Sprite background;
	private final BoundingBox[] platforms;
	private final double gravity;

	public Stage(Sprite background, BoundingBox[] platforms, double gravity)
	{
		this.background = background;
		this.platforms = platforms;
		this.gravity = gravity;
	}

	public Sprite getBackground()
	{
		return background;
	}

	public BoundingBox[] getPlatforms()
	{
		return platforms;
	}
	
	public double getGravity()
	{
		return gravity;
	}
}
