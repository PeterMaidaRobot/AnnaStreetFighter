package com.pj.streetfighter.client.entity;

public interface Clickable
{
	public void update(boolean pressed, int mouseX, int mouseY);
	public boolean isHovered();
	public void onPressed();
}
