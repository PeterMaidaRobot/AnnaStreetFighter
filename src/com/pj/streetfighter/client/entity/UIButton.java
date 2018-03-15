package com.pj.streetfighter.client.entity;

import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;

public class UIButton extends Entity implements Clickable
{
	private boolean isSelected, selectBounce = true;
	public int yOffs = 0;
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
		// updates isSelected if the mouse is within the bound boxes
		isSelected = false;
		for (int i = 0; i < boxes.size(); i++)
		{
			if (boxes.get(i).contains(mouseX/2 - getX(), mouseY/2 - getY()))
			{
				isSelected = true;
			}
		}
		
		// updates the offset of the bouncing button
		if (isSelected)
		{
			if (selectBounce)
			{
				if(yOffs <= 5)
					yOffs++;
				else
					selectBounce = false;
			}
			else
			{
				if(yOffs > 0)
					yOffs--;
				else
					selectBounce = true;
			}
		}
		else
		{
			yOffs = 0;
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
