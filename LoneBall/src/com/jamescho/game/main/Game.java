package com.jamescho.game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.jamescho.framework.util.InputHandler;
import com.jamescho.game.state.LoadState;
import com.jamescho.game.state.State;

@SuppressWarnings("serial")
 /*************************************************************************
 * Game
 * ----------------------------------------------------------------------
 * This class is JPanel sub-class and is called within GameMain.java.
 *  *** Overview of JPanel and JFrame ***
 * JFrame: The main container, which acts as the main window. 
 *         Contains the JPanel.
 * JPanel: A container that holds other GUI components, 
 *         such as buttons, textfields, ect. 
 * 
 * This class 'implements Runnable' which allows a Game object to be used 
 * as a Runnable object
 * ----------------------------------------------------------------------
 ************************************************************************/
public class Game extends JPanel implements Runnable {
	private int gameWidth;
	private int gameHeight;
	private Image gameImage;

	private Thread gameThread;
	private volatile boolean running;
	private volatile State currentState;

	private InputHandler inputHandler;

	public Game(int gameWidth, int gameHeight) {
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		setPreferredSize(new Dimension(gameWidth, gameHeight));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus();
	}

	public void setCurrentState(State newState) {
		System.gc();
		newState.init();
		currentState = newState;
		inputHandler.setCurrentState(currentState);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		initInput();
		setCurrentState(new LoadState());
		initGame();
	}

	private void initGame() {
		running = true;
		gameThread = new Thread(this, "Game Thread");
		gameThread.start();
	}

	@Override
	public void run() {
		while (running) {
                    // update methods will vary depending on what type of current state
			currentState.update();
			prepareGameImage();
			currentState.render(gameImage.getGraphics());
			repaint();
			try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// End game immediately when running becomes false.
		System.exit(0);
	}

	private void prepareGameImage() {
		if (gameImage == null) {
			gameImage = createImage(gameWidth, gameHeight);
		}
		Graphics g = gameImage.getGraphics();
		g.clearRect(0, 0, gameWidth, gameHeight);
	}

	public void exit() {
		running = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (gameImage == null) {
			return;
		}
		g.drawImage(gameImage, 0, 0, null);
	}

	private void initInput() {
		inputHandler = new InputHandler();
		addKeyListener(inputHandler);
		addMouseListener(inputHandler);
	}
}