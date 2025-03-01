package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler key;
	
	public Player(GamePanel gp, KeyHandler key) {
		this.gp = gp;
		this.key = key;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		x=100;
		y=200;
		speed=2;
		
		setAnimSettings();
		
		animIndex = 0;
	}
	public void setAnimSettings() {
		//configure o individual de cada anim
		animSize = 4;
		anim = new Animation[animSize];
		
		for(int i = 0; i<animSize; i++) {
			anim[i] = new Animation();
		}
		
		
		anim[0].spritesSize = 3;
		anim[1].spritesSize = 3;
		anim[2].spritesSize = 3;
		anim[3].spritesSize = 3;
	}
	public void getPlayerImage() {
		
		//spritesRow = 3; //x
		//spritesCol = 4; //y
		//moveSprites = new BufferedImage[spritesCol][spritesRow];
		
		
		String spriteName = "kyoma_";
		
		
		int currentSprite = 1;
		try {
			
			for(int x=0; x<animSize; x++) {
				
				anim[x].sprites = new BufferedImage[anim[x].spritesSize];
				for(int y=0; y< anim[x].spritesSize; y++) {
					anim[x].sprites[y] = ImageIO.read(getClass().getResourceAsStream("/player/"+spriteName+currentSprite+".png"));
					currentSprite++;
				}
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	 
	
	public void update() {
		
		/*
		 * 0 - down
		 * 1 - up
		 * 2 - right
		 * 3 - left
		 * */
		
		animIndex = key.ver <0?1:key.ver >0?0: key.hor>0? 2:  key.hor<0? 3:0; // checa a direçao e arranja no array das direções
		
		if(key.Pressed) {
			y += key.ver * speed;
			x += key.hor * speed;
			
			timer++;
			if(timer>10) {
				
				anim[animIndex].index = anim[animIndex].index< anim[animIndex].spritesSize-1? anim[animIndex].index+1:0;
				
				timer=0;
			}
			
		}
		
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		if(!key.Pressed)
			image = anim[animIndex].sprites[1];
		else {
			image = anim[animIndex].sprites[anim[animIndex].index];
		}
		
		g2.drawImage(image, x,y, gp.tileSize, gp.tileSize,null);
	}
}
