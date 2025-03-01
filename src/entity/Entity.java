package entity;

import java.awt.image.BufferedImage;

public class Entity {
		public int x,y;
		public int speed;
		
		/*public int spritesCol,spritesRow;
		public BufferedImage[][] moveSprites;
		
		public int animTime = 0;*/
		public int animIndex;
		public int animSize; //quantas animaçoes tem
		public Animation[] anim = new Animation[animSize];
		
		public int timer;
}


