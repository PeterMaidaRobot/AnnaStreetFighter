package com.pj.streetfighter.client.entity;

import java.awt.event.KeyEvent;
import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.input.Keyboard;

public abstract class UITextBox extends Entity
{
	private boolean selected = false;
	private Sprite unselectedSprite, selectedSprite;
	private List<BoundingBox> boxes;
	private String text = "";
	private final int MAX_LENGTH;
	
	public UITextBox(int x, int y, Sprite unselectedSprite, Sprite selectedSprite, List<BoundingBox> boxes, int maxLength)
	{
		super(x - unselectedSprite.getXSIZE()/2, y - unselectedSprite.getYSIZE()/2);
		this.unselectedSprite = unselectedSprite;
		this.selectedSprite = selectedSprite;
		this.boxes = boxes;
		this.MAX_LENGTH = maxLength;
	}
	
	public void onKeyPress(Keyboard keyboard)
	{
		if (!selected)
			return;
		
		if (keyboard.getLastPressed() == KeyEvent.VK_DELETE && keyboard.isPressed(KeyEvent.VK_DELETE))
		{
			if (text.length() == 0)
				return;
			// remove end character
			// move cursor left
			System.out.println("Delete pressed");
		} else
		{
			if (text.length() == MAX_LENGTH)
				return;
			// add character (adds weird ones???)
			// move cursor right
			System.out.println("Other pressed");
		}

		//update sprite, move cursor (which is entity?), update string
	}
	
	public void onMousePress(int mouseX, int mouseY)
	{	
		// if the button has been pressed outside of the button, the text box is now unselected
		selected = false;
		
		// updates booleans if the mouse is within the bound boxes
		for (int i = 0; i < boxes.size(); i++)
		{
			if (boxes.get(i).contains(mouseX/2 - getX(), mouseY/2 - getY()))
			{
				// if the button has been pressed inside of the button, the text box is now selected
				selected = true;
			}
		}
	}
	
	public String getText()
	{
		return text;
	}
	
	@Override
	public Sprite getSprite()
	{
		// add the string to the sprite???
		if (selected)
			return selectedSprite;
		else
			return unselectedSprite;
	}

}
