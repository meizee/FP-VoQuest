package game_object;

import static user_interface.GameWindow.SCREEN_HEIGHT;
import static user_interface.GameWindow.SCREEN_WIDTH;
import static util.Resource.getImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import user_interface.GameScreen;

public class Tanah {
	private GameScreen gameScreen;
	private double x = 0;
	private int y;
	private int tanahWidth;
	private int tanahHeight;
	private BufferedImage tanah;
	
	public Tanah(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		tanah = getImage("resources/2.png");
		y = SCREEN_HEIGHT - tanah.getHeight() - 4;
		tanahWidth = tanah.getWidth();
		tanahHeight = tanah.getHeight();
	}
	
	public void updatePosition() {
		// + SPEED_X to 2 decimal points
		x += Math.round(gameScreen.getSpeedX() * 100d) / 100d;
	}
	
	public void resetTanah() {
		x = 0;
	}
	
	public void draw(Graphics g) {
		g.drawImage(tanah, (int)x, y, tanahWidth, tanahHeight, null);
		
		if(tanahWidth - SCREEN_WIDTH <= (int)Math.abs(x))
			g.drawImage(tanah, (int)(tanahWidth + x), y, tanahWidth, tanahHeight, null);
		
		if(tanahWidth <= (int)Math.abs(x))
			x = 0;
	}
}
