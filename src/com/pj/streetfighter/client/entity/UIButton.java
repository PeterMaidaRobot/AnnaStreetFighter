package com.pj.streetfighter.client.entity;

import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;

public class UIButton extends Entity
{
	private boolean hovered = false, selected = false;
	public short yOffs = 0;
	private Oscillator yOsc = new Oscillator((short) 0, (short) 5, (float) 0.23);
	private Sprite unselectedSprite, selectedSprite;
	private List<BoundingBox> boxes;
	
	public UIButton(int x, int y, Sprite unselectedSprite, Sprite selectedSprite, List<BoundingBox> boxes)
	{
		super(x - unselectedSprite.getXSIZE()/2, y - unselectedSprite.getYSIZE()/2);
		this.unselectedSprite = unselectedSprite;
		this.selectedSprite = selectedSprite;
		this.boxes = boxes;
	}
	
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		// the button is not hovered if the mouse is not over the button
		hovered = false;
		
		// if the button has been pressed outside of the button, the button is now unselected
		if (pressed)
			selected = false;
		
		// updates booleans if the mouse is within the bound boxes
		for (int i = 0; i < boxes.size(); i++)
		{
			if (boxes.get(i).contains(mouseX/2 - getX(), mouseY/2 - getY()))
			{
				hovered = true;
				// if the button has been pressed inside of the button, the button is now selected
				if (pressed)
					selected = true;
			}
		}
		
		// updates the offset of the bouncing button
		if (hovered)
		{
			yOffs = yOsc.getNum();
		}
		else
		{
			yOffs = 0;
			yOsc.reset();
		}
	}
	
	public boolean isHovered()
	{
		return hovered;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
	
	@Override
	public Sprite getSprite()
	{
		if (hovered || selected)
			return selectedSprite;
		else
			return unselectedSprite;
	}

}
