package manager;

//import game_object.Birds;
import game_object.Cactuses;
import misc.EnemyType;
import user_interface.GameScreen;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyManager {
	
	// value by which chance of creating new enemy increasing
	private static final double PERCENTAGE_INC = 0.0001;
	private static final double DISTANCE_DEC = -0.001;
	private static final int MINIMUM_DISTANCE = 5000;
	private double distanceBetweenEnemies = 10000;
	private double cactusesPercentage = 2;
	
	
	private Cactuses cactuses;
	
	
	public EnemyManager(GameScreen gameScreen) {
		cactuses = new Cactuses(gameScreen, this);
		//birds = new Birds(gameScreen, this);
	}
	
	public double getDistanceBetweenEnemies() {
		return distanceBetweenEnemies;
	}

	public double getCactusesPercentage() {
		return cactusesPercentage;
	}

	

	public void updatePosition() {
		cactusesPercentage += PERCENTAGE_INC;
		if(distanceBetweenEnemies > MINIMUM_DISTANCE)
			distanceBetweenEnemies += DISTANCE_DEC;
		cactuses.updatePosition();
		if(cactuses.spaceAvailable()) {
			// "randomly" choosing new enemy type 
			switch (EnemyType.values()[(int)(Math.random() * EnemyType.values().length)]) {
			case CACTUS:
				if(cactuses.createCactuses())
					break;
			
			default:
				cactuses.createCactuses();
				break;
			}
		}
	}
	
	public boolean isCollision(Rectangle hitBox) {
		if(cactuses.isCollision(hitBox) )
			return true;
		return false;
	}
	
	public void clearEnemy() {
		cactuses.clearCactuses();
		
	}
	
	public void draw(Graphics g) {
		cactuses.draw(g);
		
	}
	
	public void drawHitbox(Graphics g) {
		cactuses.drawHitbox(g);
		
	}
	
}
