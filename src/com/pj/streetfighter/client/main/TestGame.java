package com.pj.streetfighter.client.main;

import java.awt.Dimension;
import javax.swing.JFrame;

public class TestGame {

	public TestGame() {
		
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(600, 1000));
		frame.setVisible(true);
		
	}

	public static void main(String args[])
	{
		TestGame game = new TestGame();
	}
	
}
