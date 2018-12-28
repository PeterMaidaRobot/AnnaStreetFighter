package com.pj.streetfigher.stages;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.server.engine.BoundingBox;

public class Meadow extends Stage {
	
	private static SpriteSheet localSheet = new SpriteSheet("/stage_sprites/meadow.png", 640);
	private static Sprite localBackground = new Sprite(640, 378, 0, 0, localSheet);
	private static double gravity = 0.25;
	
	private static BoundingBox[] localPlatforms = {
			new BoundingBox(96, 320, 544, 378),
			new BoundingBox(64, 192, 192, 224),
			new BoundingBox(448, 192, 576, 224)
	};
	
	public Meadow()
	{
		super(localBackground, localPlatforms, gravity);
	}
}
