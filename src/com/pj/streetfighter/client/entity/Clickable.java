package com.pj.streetfighter.client.entity;

public interface Clickable
{
	public void update(int mouseX, int mouseY);
	public boolean isPressed();
	public void onPressed();
}
