package com.pj.streetfighter.characters;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.server.engine.BoundingBox;

public class Prometheus extends Character {
	
	private static SpriteSheet localSheet = new SpriteSheet("/character_sprites/prometheus.png", 64);
	private static Sprite localSprite = new Sprite(64, 64, 0, 0, localSheet);
	
	private static BoundingBox[] localBoundingBoxes = {
			new BoundingBox(13, 2, 44, 41),
			new BoundingBox(21, 42, 35, 63),
			new BoundingBox(45, 31, 57, 63)
	};
	
	public Prometheus()
	{
		super(localSprite, localBoundingBoxes);
	}
}
