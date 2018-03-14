package com.pj.streetfighter.client.entity;

public interface Clickable
{
	public boolean isPressed(int mouseX, int mouseY);
	public void onPressed();
}
