package com.pj.streetfighter.server.engine;

public class Physics {
	
	private final int MAX_X_VELOCITY = 5;
	private final int MAX_Y_VELOCITY = 5;

	private int xVel;
	private int yVel;
	private double xAccel;
	private double yAccel;
	
	
	public Physics() {
		this.xVel = 0;
		this.yVel = 0;
		this.xAccel = 0;
		this.yAccel = 0;
	}
	
	public Physics(int xVel, int yVel, int xAccel, int yAccel) {
		this.xVel = xVel;
		this.yVel = yVel;
		this.xAccel = xAccel;
		this.yAccel = yAccel;
	}
	
	public void updateVelocities() {
		xVel += xAccel;
		yVel += yAccel;
		
		// cap velocities
		if (xVel > MAX_X_VELOCITY)
		{
			xVel = MAX_X_VELOCITY;
		}
		else if (xVel < -1 * MAX_X_VELOCITY)
		{
			xVel =  -1 * MAX_X_VELOCITY;
		}
		if (yVel > MAX_Y_VELOCITY)
		{
			yVel = MAX_Y_VELOCITY;
		}
		else if (yVel < -1 * MAX_Y_VELOCITY)
		{
			yVel =  -1 * MAX_Y_VELOCITY;
		}
	}
	
	public void updateFriction() {
		xAccel = xAccel + xVel * -0.2;
		yAccel = yAccel + yVel * -0.2;
	}
	
	public void setXAccel(int newAccel) {
		xAccel = newAccel;
	}
	
	public void setYAccel(int newAccel) {
		yAccel = newAccel;
	}
	
	public int getXVel() {
		return xVel;
	}
	
	public int getYVel() {
		return yVel;
	}
}
