package com.dovgreenwood.CellularAutomata;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Entry extends JFrame {

	public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public Entry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cellular Automata");
		
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		
		CellWorld c = new CellWorld();
		
		add(c, BorderLayout.CENTER);
		add(new Buttons(c), BorderLayout.SOUTH);
	}

	public static void main(String [] args) {
		Entry e = new Entry();
		e.setVisible(true);
	}

}
