package com.pj.streetfighter.client.entity;

import java.awt.event.KeyEvent;
import java.util.List;

import com.pj.streetfighter.client.graphics.Sprite;
import com.pj.streetfighter.client.graphics.SpriteSheet;
import com.pj.streetfighter.client.input.Keyboard;

public abstract class UITextBox extends Entity
{
	private SpriteSheet textSheet = new SpriteSheet("/character_sprites/textsheet.png", 200);
	private Sprite[] numberSprites = new Sprite[10];
	private Sprite periodSprite = new Sprite(3, 9, 60, 0, textSheet);
	private boolean selected = false;
	private Sprite unselectedSprite, selectedSprite;
	private List<BoundingBox> boxes;
	private String text = "";
	private final int MAX_LENGTH;
	private final int HORIZONTAL_PADDING;
	private final int VERTICAL_PADDING;
	private int lastKey = 0;
	private double lastTime = System.currentTimeMillis();
	
	public UITextBox(int x, int y, Sprite unselectedSprite, Sprite selectedSprite, List<BoundingBox> boxes,
						int maxLength, int horizontalPadding, int verticalPadding)
	{
		super(x - unselectedSprite.getXSIZE()/2, y - unselectedSprite.getYSIZE()/2);
		this.unselectedSprite = unselectedSprite;
		this.selectedSprite = selectedSprite;
		this.boxes = boxes;
		this.MAX_LENGTH = maxLength;
		this.HORIZONTAL_PADDING = horizontalPadding;
		this.VERTICAL_PADDING = verticalPadding;
		for (int i = 0; i < numberSprites.length; i++) {
			numberSprites[i] = new Sprite (6, 9, 6 * i, 0, textSheet);
		}
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
		Sprite textbox;
		if (selected)
			textbox = new Sprite(selectedSprite);
		else
			textbox = new Sprite(unselectedSprite);
		
		for (int i = 0; i < text.length(); i++)
		{
			char letter = text.charAt(i);
			if (letter >=  '0' && letter <=  '9')
			{
				// 6 wide, 9 tall
				textbox.addSprite(numberSprites[letter - '0'], 6 * i + HORIZONTAL_PADDING, VERTICAL_PADDING);
			}
			else if (text.charAt(i) == '.')
			{
				// period
				// 3 wide, 9 tall
				textbox.addSprite(periodSprite, 6 * i + HORIZONTAL_PADDING, VERTICAL_PADDING);
			}
		}
		return textbox;
		
	}

}
