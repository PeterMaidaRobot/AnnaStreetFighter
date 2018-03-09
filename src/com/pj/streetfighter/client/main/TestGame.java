package com.pj.streetfighter.client.main;

import java.awt.Dimension;
import javax.swing.JFrame;

public class TestGame {

	public TestGame() {
		
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(800, 600));
		frame.setVisible(true);
		
	}

	public static void main(String args[])
	{
		TestGame game = new TestGame();
	}
	
}
