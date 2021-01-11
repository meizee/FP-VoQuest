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
	// here i made tanah 2x times bigger
	private int tanahWidthScaled;
	private int tanahHeightScaled;
	private BufferedImage tanah;
	
	public Tanah(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		tanah = getImage("resources/3.png");
		y = SCREEN_HEIGHT - tanah.getHeight() - 5;
		tanahWidthScaled = tanah.getWidth();
		tanahHeightScaled = tanah.getHeight();
	}
	
	public void updatePosition() {
		// + SPEED_X to 2 decimal points
		x += Math.round(gameScreen.getSpeedX() * 100d) / 100d;
	}
	
	public void resetTanah() {
		x = 0;
	}
	
	public void draw(Graphics g) {
		g.drawImage(tanah, (int)x, y, tanahWidthScaled, tanahHeightScaled, null);
		// drawing another tanah if image is ending
		if(tanahWidthScaled - SCREEN_WIDTH <= (int)Math.abs(x))
			g.drawImage(tanah, (int)(tanahWidthScaled + x), y, tanahWidthScaled, tanahHeightScaled, null);
		// if tanah out of screen set it to 0
		if(tanahWidthScaled <= (int)Math.abs(x))
			x = 0;
	}
}
