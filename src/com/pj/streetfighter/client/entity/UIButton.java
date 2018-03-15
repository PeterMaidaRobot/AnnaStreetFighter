package com.pj.streetfighter.client.entity;

import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;

public class UIButton extends Entity implements Clickable
{
	private Sprite unselected, selected;
	private List<BoundingBox> boxes;
	
	public UIButton(int x, int y, Sprite unselected, Sprite selected, List<BoundingBox> boxes)
	{
		super(x - unselected.getXSIZE()/2, y - unselected.getYSIZE()/2);
		this.unselected = unselected;
		this.selected = selected;
		this.boxes = boxes;
	}
	
	@Override
	public boolean isPressed(int mouseX, int mouseY)
	{
		for (int i = 0; i < boxes.size(); i++)
		{
			if (boxes.get(i).contains(mouseX, mouseY))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void onPressed()
	{
		
	}
	
	@Override
	public Sprite getSprite()
	{
		return unselected;
	}
	
}
