package com.pj.streetfigher.stages;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.server.engine.BoundingBox;

public abstract class Stage {
	
	private final Sprite background;
	private final BoundingBox[] platforms;

	public Stage(Sprite background, BoundingBox[] platforms)
	{
		this.background = background;
		this.platforms = platforms;
	}

	public Sprite getBackground()
	{
		return background;
	}

	public BoundingBox[] getPlatforms()
	{
		return platforms;
	}
}
