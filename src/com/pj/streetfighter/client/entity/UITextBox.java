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
	private int lastKey = 0;
	private double lastTime = System.currentTimeMillis();
	
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
		//  checks if any keys are pressed, includes unknown keys
		for (int currKey =  0; currKey < keyboard.NUM_KEYS; currKey++) 
		{
			if (keyboard.isPressed(currKey)) {
				if (lastKey != currKey || System.currentTimeMillis() - lastTime > 100)
				{
					onKeyPress(currKey);
				}
				lastKey = currKey;
				lastTime = System.currentTimeMillis();
				return; // two keys cannot be pressed down at once
			}
		}
	}
	
	public void onKeyPress(int keyCode)
	{
		if (selected)
		{
			if (keyCode == KeyEvent.VK_DELETE || keyCode == KeyEvent.VK_BACK_SPACE)
			{
				if (text.length() == 0)
					return;
				text = text.substring(0, text.length() - 1);
				// TODO move cursor (entity?) left
			}
			else if (text.length() < MAX_LENGTH)
			{
				text = text.concat(KeyEvent.getKeyText(keyCode));
				// TODO move cursor (entity?) right
			} else { // text.length() >= MAX_LENGTH
				return; // TODO remove? ugly? might be necessary for updating sprite/graphic below
			}
			System.out.println("Text: " + text); // TODO rm, used for testing
			// TODO update sprite/graphic
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
		// TODO add the string to the sprite???
		if (selected)
			return selectedSprite;
		else
			return unselectedSprite;
	}

}
