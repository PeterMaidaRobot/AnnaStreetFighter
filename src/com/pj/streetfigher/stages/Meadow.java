package com.pj.streetfigher.stages;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.server.engine.BoundingBox;

public class Meadow extends Stage {
	
	private static SpriteSheet localSheet = new SpriteSheet("/stage_sprites/meadow.png", 640);
	private static Sprite localBackground = new Sprite(640, 378, 0, 0, localSheet);
	
	private static BoundingBox[] localPlatforms = {
			new BoundingBox(0, 328, 640, 378),
			new BoundingBox(300, 0, 400, 378)
	};
	
	public Meadow()
	{
		super(localBackground, localPlatforms);
	}
}
