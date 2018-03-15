package com.pj.streetfighter.client.entity;

public class Oscillator {

	private short start, end;
	private float current, increment;
	private boolean increasing;
	
	public Oscillator(short start, short end, float increment)
	{
		this.start = start;
		this.end = end;
		current = start;
		this.increment = increment;
		increasing = true;
	}
	
	public short getNum()
	{
		if (increasing)
		{
			if (current < end)
			{
				current += increment;
			}
			else
			{
				increasing = false;
			}
		}
		else
		{
			if (current > start)
			{
				current -= increment;
			}
			else
			{
				increasing = true;
			}
		}
		return (short) current;
	}
	
	public void reset()
	{
		current = start;
		increasing = true;
	}
	
}
