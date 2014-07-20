package com.dovgreenwood.CellularAutomata;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

public class CellWorld extends JPanel implements Runnable {
	
	private Thread runner = new Thread(this);
	
	private int time = 200;
	private boolean paused = true;
	private boolean hasBegun = false;
	
	private int squareSize = 10;
	
	private Point start = new Point(0, 0);
	private Point prevPoint = new Point(0, 0);
	
	private boolean [][] field = new boolean [Entry.SCREEN_HEIGHT][Entry.SCREEN_WIDTH];
	
	
	/*
	 * Constructor: initiates all points on the field to false, 
	 * adds a wheel listener for zooming,
	 * adds a key listener for panning, 
	 * and adds a mouse listener for setting down cells.
	 * */
	public CellWorld() {
		for(int r = 0; r < field.length; r++)
			for(int c = 0; c < field[0].length; c++)
				field[r][c] = false;
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				squareSize += e.getPreciseWheelRotation() < 0 ? 1 : -1;
				squareSize = squareSize < 2? 2 : squareSize;
				repaint();
			}
		});

		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP)
					start.y -= (start.y - 1 >= 0)? 1 : 0;
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
					start.y += (start.y + 1 < Entry.SCREEN_HEIGHT)? 1 : 0;
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
					start.x -= (start.x - 1 > 0)? 1 : 0;
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					start.x += (start.x + 1 < Entry.SCREEN_WIDTH)? 1 : 0;
				repaint();
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(!hasBegun) {
					field[start.x + e.getX() / squareSize][start.y + e.getY() / squareSize] = !field[e.getX() / squareSize][e.getY() / squareSize];
					repaint();
				}
			}
		});
		
	}
	
	/*
	 * getTime
	 * @return time		the amount of time used for the delay
	 * */
	public int getTime() {
		return time;
	}

	/*
	 * setTime
	 * @param time		the amount of time used for the delay
	 * */
	public void setTime(int time) {
		this.time = time;
	}

	/*
	 * isPaused
	 * @return paused	whether or not it is paused
	 * */
	public boolean isPaused() {
		return paused;
	}

	/*
	 * setPaused
	 * @param paused	whether or not the game should be paused
	 * */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	
	/*
	 * begin: begins the game
	 * */
	public void begin() {
		hasBegun = true;
		paused = false;
		runner.start();
	}
	
	/*
	 * reset: resets the game
	 * */
	public void reset() {
		for(int y = 0; y < field.length; y++)
			for(int x = 0; x < field[0].length; x++)
				field[y][x] = false;
		repaint();
		//runner.stop(); ???
		//reset rules
	}

	/*
	 * paintComponent: paints as many cells as possible (alive are green, dead are empty),
	 * based on the zoom, beginning with the top corner being the "start" point.
	 * */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		for(int y = 0; y < (Entry.SCREEN_HEIGHT - 60) / squareSize; y++) //60???
			for(int x = 0; x < Entry.SCREEN_WIDTH / squareSize; x++) {
				if(field[start.x + x][start.y + y] == true)
					g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
			}
		g.setColor(Color.BLACK);
		for(int y = 0; y < (Entry.SCREEN_HEIGHT - 60) / squareSize; y++)
			for(int x = 0; x < Entry.SCREEN_WIDTH / squareSize; x++) {
				g.drawRect(x * squareSize, y * squareSize, squareSize, squareSize);
			}
	}
	
	/*
	 * run: runs the game based one the given rule
	 * */
	public void run() {
		while(!paused) {
			//modify array based on rules
			repaint();
			delay();
		}
	}
	
	/*
	 * delay: delays the program
	 * */
	private void delay() {
		try {
			Thread.sleep(time);
		}
		catch(Exception e) {
		}
	}
	
}
