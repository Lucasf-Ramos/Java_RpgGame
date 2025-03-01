package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean Pressed;
	public int hor, ver;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			Pressed = true;
			ver = -1;
			hor = 0;
		}else if(code == KeyEvent.VK_S) {
			Pressed = true;
			ver = 1;
			hor = 0;
		}else if(code == KeyEvent.VK_D) {
			Pressed = true;
			hor = 1;
			ver = 0;
		}else if(code == KeyEvent.VK_A) {
			Pressed = true;
			hor = -1;
			ver = 0;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W) {
			Pressed = false;
			//ver = 0;
		}else if(code == KeyEvent.VK_S) {
			Pressed = false;
			//ver = 0;
		}if(code == KeyEvent.VK_D) {
			Pressed = false;
			//hor = 0;
		}else if(code == KeyEvent.VK_A) {
			Pressed = false;
			//hor = 0;
		}
	}

}
