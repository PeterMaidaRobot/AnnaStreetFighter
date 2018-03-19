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
	
	public void update(Keyboard keyboard)
	{
		//  46 for .,48 for 0, 57 for 9, 65 for A, 90 for Z. Will print = and ;
		for (int currKey = KeyEvent.VK_PERIOD; currKey < KeyEvent.VK_Z && currKey < keyboard.NUM_KEYS; currKey++) 
		{
			if (keyboard.isPressed(currKey)) //TODO record if has been pushed
				onKeyPress(currKey);
		}
	}
	
	public void onKeyPress(int keyCode)
	{
		if (selected)
		{
			if (keyCode == KeyEvent.VK_DELETE || keyCode == KeyEvent.VK_BACK_SPACE)
			{
				System.out.println("Delete pressed");
				if (text.length() == 0)
					return;
				text = text.substring(0, text.length() - 1);
				// TODO move cursor (entity?) left
				return;
			}
			else if (text.length() < MAX_LENGTH)
			{
				System.out.println(KeyEvent.getKeyText(keyCode));
				//text = text.concat(KeyEvent.getKeyText(keyCode)); //TODO, uncomment, just too fast
				// TODO move cursor (entity?) right
			}
			// TODO update sprite!?
		}
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
