package manager;

import java.awt.Graphics;
import java.awt.Rectangle;
import game_object.Batuan;
import user_interface.GameScreen;

public class ManajerBatuan {
	// value by which chance of creating new enemy increasing
		private static final double PERSENTASE_PERTAMBAHAN = 0.0001;
		private static final double PENGURANGAN_JARAK = -0.001;
		private static final int JARAK_MINIMUM = 5000;
		private double jarakAntarBatuan = 10000;
		private double persentaseBatuan = 2;
		
		
		private Batuan batuan;
		
		
		public ManajerBatuan(GameScreen gameScreen) {
			batuan = new Batuan(gameScreen, this);
			
		}
		
		public double getJarakBatuan() {
			return jarakAntarBatuan;
		}

		public double getPersentaseBatuan() {
			return persentaseBatuan;
		}

		

		public void updatePosisi() {
			persentaseBatuan += PERSENTASE_PERTAMBAHAN;
			if(jarakAntarBatuan > JARAK_MINIMUM)
				jarakAntarBatuan += PENGURANGAN_JARAK;
			batuan.updatePosisi();
			if(batuan.tempatKosong()) {
				batuan.createBatuan();
				}
			}
		
		
		public boolean isCollision(Rectangle hitBox) {
			if(batuan.isCollision(hitBox) )
				return true;
			return false;
		}
		
		public void clearBatuan() {
			batuan.clearBatuan();
			
		}
		
		public void draw(Graphics g) {
			batuan.draw(g);
			
		}
		
		public void drawHitbox(Graphics g) {
			batuan.drawHitbox(g);
			
		}
		
	}

