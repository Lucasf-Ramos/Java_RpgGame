package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[13];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		
		loadMap();
	}

	public void getTileImage() { 
		try {
			for (int i = 0; i < tile.length; i++) {
				
				tile[i] = new Tile();
				tile[i].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/tiles__" + (i+1) + ".png"));
				

			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map_1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			System.out.println("starting");
			int col = 0;
			int row = 0;
			while(col <gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				
				while(col <gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				
				}
				System.out.println("loading map");
			}
			br.close();
			System.out.println("map loaded");
		}
		catch(Exception e) {
			
		}
	}

	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col <gp.maxScreenCol && row < gp.maxScreenRow) {
			g2.drawImage(tile[mapTileNum[col][row]].sprite, x,y, gp.tileSize, gp.tileSize, null);
			
			col++;
			x+= gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row ++;
				y += gp.tileSize;
			}
		}
	}
}
