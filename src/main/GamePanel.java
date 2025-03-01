package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	//screen settings
	final int originalTileSize = 32; //tiles 16x16
	final int scale = 2;
	public final int tileSize = originalTileSize * scale; //40*40
	//screen 4:3
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60; 
	
	KeyHandler key = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, key);
	TileManager tileMap = new TileManager(this);
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() +drawInterval;
		while(gameThread != null) {
	
			//long currentTime = System.nanoTime();
			update();
			repaint();
		
			try {
				double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000;
				
				
				if(remainingTime< 0)
					remainingTime = 0;
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tileMap.draw(g2);
		player.draw(g2);
		
		
		g2.dispose();
		
	}
	
}
