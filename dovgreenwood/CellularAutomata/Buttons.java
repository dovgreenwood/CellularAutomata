package com.dovgreenwood.CellularAutomata;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Buttons extends JPanel {

	CellWorld world;
	
	JButton pause = new JButton("PAUSE/PLAY");
	JButton saveRule = new JButton("SAVE RULE");
	JButton saveSeed = new JButton("SAVE SEED");
	JSlider speed;
	JButton newGame = new JButton("NEW GAME");
	JButton begin = new JButton("BEGIN");
	
	public Buttons(CellWorld c) {
		world = c;
		speed = new JSlider(1, 1000, c.getTime());
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		add(pause);
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.requestFocusInWindow();
				world.setPaused(!world.isPaused());
			}
		});
		
		add(saveRule);
		saveRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.requestFocusInWindow();
				//save rule
			}
		});
		
		add(saveSeed);
		saveSeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.requestFocusInWindow();
				//save seed
			}
		});
		
		speed.setPaintTicks(true);
		speed.setMajorTickSpacing(100);
		add(speed);
		speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				world.requestFocusInWindow();
				world.setTime(speed.getValue());
			}
		});
		
		add(newGame);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set rules
				world.reset();
				world.requestFocusInWindow();
			}
		});
		
		add(begin);
		begin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.requestFocusInWindow();
				world.begin();
			}
		});
	}
	
}
