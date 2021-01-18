package game_object;

import static user_interface.GameScreen.GROUND_Y;
import static user_interface.GameWindow.SCREEN_WIDTH;
import static util.Resource.getImage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import manager.ManajerBatuan;
import user_interface.GameScreen;

public class Batuan {
	
	private class Batu {
		
		private BufferedImage batuImage;
		private double x;
		private int y;
		
		private Batu(BufferedImage batuImage, double x, int y) {
			this.batuImage = batuImage;
			this.x = x;
			this.y = y;
		}
		
	}
	
	// hitbox batuan
	private static final double HITBOX_X = 2.7;
	private static final int HITBOX_Y = 25;
	// banyak gambar batu
	private static final int N_BATUAN = 2;
	
	private ManajerBatuan eManager;
	private GameScreen gameScreen;
	private List<Batu> batuan;
	
	public Batuan(GameScreen gameScreen, ManajerBatuan eManager) {
		this.eManager = eManager;
		this.gameScreen = gameScreen;
		batuan = new ArrayList<Batu>();
	}
	
	public void updatePosisi() {
		for(Iterator<Batu> i = batuan.iterator(); i.hasNext();) {
			Batu batu = i.next();
			batu.x += Math.round(gameScreen.getSpeedX() * 100d) / 100d;
			if((int)batu.x + batu.batuImage.getWidth() < 0) {
				i.remove();
			}
		}
	}
	
	public boolean tempatKosong() {
		for(Iterator<Batu> i = batuan.iterator(); i.hasNext();) {
			Batu batu = i.next();
			if(SCREEN_WIDTH - (batu.x + batu.batuImage.getWidth()) < eManager.getJarakBatuan()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean createBatuan() {
		if(Math.random() * 100 < eManager.getPersentaseBatuan()) {
			
			for(int i = 0, nBatuan = (int)(Math.random() + 1); i < nBatuan; i++) {
				BufferedImage batuImage = getImage("resources/batu-" + (int)(Math.random() * N_BATUAN + 1) + ".png");
				int x = SCREEN_WIDTH;
				int y = GROUND_Y - batuImage.getHeight();
				if(i > 0)
					x = (int)batuan.get(batuan.size() - 1).x + batuan.get(batuan.size() - 1).batuImage.getWidth();
				batuan.add(new Batu(batuImage, x, y));
			}
			return true;
		}
		return false;
	}
	
	public boolean isCollision(Rectangle karakterHitBox) {
		for(Iterator<Batu> i = batuan.iterator(); i.hasNext();) {
			Batu batu = i.next();
			Rectangle batuHitBox = getHitbox(batu);
			if(batuHitBox.intersects(karakterHitBox))
				return true;
		}
		return false;
	}
	
	private Rectangle getHitbox(Batu batu) {
		return new Rectangle((int)batu.x + (int)(batu.batuImage.getWidth() / HITBOX_X), 
				batu.y + batu.batuImage.getHeight() / HITBOX_Y, 
				batu.batuImage.getWidth() - (int)(batu.batuImage.getWidth() / HITBOX_X) * 2, 
				batu.batuImage.getHeight() - batu.batuImage.getHeight() / HITBOX_Y);
	}
	
	public void clearBatuan() {
		batuan.clear();
	}
	
	public void draw(Graphics g) {
		for(Iterator<Batu> i = batuan.iterator(); i.hasNext();) {
			Batu batu = i.next();
			g.drawImage(batu.batuImage, (int)(batu.x), batu.y, null);
		}
	}
	
//	public void drawHitbox(Graphics g) {
//		g.setColor(Color.RED);
//		for(Iterator<Batu> i = batuan.iterator(); i.hasNext();) {
//			Batu batu = i.next();
//			Rectangle batuHitBox = getHitbox(batu);
//			g.drawRect(batuHitBox.x, batuHitBox.y, (int)batuHitBox.getWidth(), (int)batuHitBox.getHeight());
//		}
//	}
	
}


