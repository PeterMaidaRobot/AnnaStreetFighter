package com.pj.streetfighter.server.engine;

import com.pj.streetfighter.characters.Character;

public class Player
{	
	private Character character;
	private State state = new State();
	private int animationIndex = 0;
	private int x, y;
	
	public static final double MAX_X_VEL = 3;
	public static final double MAX_Y_VEL = 9;
	
	private double xVel, yVel, xAccel;

	public Player(Character p1Character, int x, int y, boolean facingRight)
	{
		this.character = p1Character;
		this.x = x;
		this.y = y;
		setXVel(0);
		setYVel(0);
		setXAccel(0);
		setFacingRight(facingRight);
	}
	
	public Character getCharacter()
	{
		return character;
	}
	
	public short getState()
	{
		return state.value;
	}
	
	public void setState(State state)
	{
		this.state = state;
	}
	
	public int getAnimationIndex()
	{
		return animationIndex;
	}

	public void setAnimationIndex(int animationIndex)
	{
		this.animationIndex = animationIndex;
	}	
	
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void incrementX(int i)
	{
		this.x += i;
	}
	
	public double getXVel()
	{
		return xVel;
	}

	public void setXVel(double xVel)
	{
		this.xVel = xVel;
	}

	public double getYVel()
	{
		return yVel;
	}

	public void setYVel(double yVel)
	{
		this.yVel = yVel;
	}
	
	public double getXAccel()
	{
		return xAccel;
	}

	public void setXAccel(double xAccel)
	{
		this.xAccel = xAccel;
	}
	
	public BoundingBox[] getBoundingBoxes()
	{
		BoundingBox[] originalBoxes = character.getAnimation(animationIndex).getBoundingBoxes();
		BoundingBox[] boundingBoxes = new BoundingBox[originalBoxes.length];
	
		for (int i = 0; i < boundingBoxes.length; i++)
		{
			boundingBoxes[i] = new BoundingBox(originalBoxes[i]);
			boundingBoxes[i].addXOffset(x);
			boundingBoxes[i].addYOffset(y);
		}
		return boundingBoxes;
	}
	
	private class State 
	{
		public static final byte IDLE = 0b000;
		public static final byte RUNNING = 0b001;
		//public static final byte JUMPING = 0b010;
		public static final byte PUNCHING = 0b011;
		public static final byte KICKING = 0b100;
		public static final byte BLOCKING = 0b101;
		public static final byte GOT_HIT = 0b110;
		public static final byte KNOCKED_OUT = 0b111;
		
		public static final short FACE_RIGHT = 	0b00000001;
		public static final short JUMPING =		0b00000010;
		public static final short FALLING = 	0b00000100;
		
		
		private short value = 0b00000000;	
		
		public State()
		{
			value = (short) (State.IDLE | State.FALLING);
		}
	}

	public boolean isFacingRight()
	{
		return ((this.state.value & State.FACE_RIGHT) == State.FACE_RIGHT);
	}
	
	public void setFacingRight(boolean facingRight)
	{
		if (facingRight)
		{
			this.state.value |= State.FACE_RIGHT;
		}
		else
		{
			this.state.value &= ~State.FACE_RIGHT;
		}
	}
	
	public boolean isAirborne()
	{
		return ((this.state.value & State.JUMPING) == State.JUMPING) || 
				((this.state.value & State.FALLING) == State.FALLING);
	}
	
	
	public boolean isGrounded()
	{
		return !(isJumping() || isFalling());
	}
	
	public void setGrounded() 
	{
		if (!isGrounded())
		{
			if (yVel != 0) {
				yVel = 0;
			}
			
			this.state.value &= ~State.JUMPING;
			this.state.value &= ~State.FALLING;
		}
	}
	
	public boolean isJumping()
	{
		return ((this.state.value & State.JUMPING) == State.JUMPING);
	}
	
	public void setJumping()
	{
		if (!isJumping())
		{
			if (yVel == 0) {
				setYVel(-1 * Player.MAX_Y_VEL);
			}
			
			this.state.value |= State.JUMPING;
			this.state.value &= ~State.FALLING;
		}
	}
	
	public boolean isFalling()
	{
		return ((this.state.value & State.FALLING) == State.FALLING);
	}
	
	public void setFalling()
	{
		if (!isFalling())
		{
			if (yVel < 0) {
				setYVel(0);
			}
			
			this.state.value |= State.FALLING;
			this.state.value &= ~State.JUMPING;
		}
	}
}
