package com.pj.streetfighter.client.entity;

import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;

public class UIButton extends Entity implements Clickable
{
	private boolean isSelected;
	private Sprite unselected, selected;
	private List<BoundingBox> boxes;
	
	public UIButton(int x, int y, Sprite unselected, Sprite selected, List<BoundingBox> boxes)
	{
		super(x - unselected.getXSIZE()/2, y - unselected.getYSIZE()/2);
		this.unselected = unselected;
		this.selected = selected;
		this.boxes = boxes;
		this.isSelected = false;
	}
	
	public void update(int mouseX, int mouseY)
	{
		isSelected = false;
		for (int i = 0; i < boxes.size(); i++)
		{
			if (boxes.get(i).contains(mouseX/2 - getX(), mouseY/2 - getY()))
			{
				isSelected = true;
			}
		}
	}
	
	@Override
	public boolean isPressed()
	{
		return isSelected;
	}
	
	@Override
	public void onPressed()
	{
		
	}
	
	@Override
	public Sprite getSprite()
	{
		if (isSelected)
			return selected;
		else
			return unselected;
	}

}
