package game_object;

import static user_interface.GameScreen.GRAVITY;
import static user_interface.GameScreen.GROUND_Y;
import static user_interface.GameScreen.SPEED_Y;
import static util.Resource.getImage;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import manager.ManajerSound;
import misc.Animasi;
import misc.Kontrol;
import misc.KarakterState;

public class Karakter {
	
		private static final int[] HITBOX_LARI = {12, 26, -32, -42};

		
		public static final double X = 120;
		
		Kontrol kontrol;
		
		private double maxY;
		private double highLompatMaxY;
		private double lowLompatMaxY;
		
		private double y = 0;
		private double speedY = 0;
		
		private KarakterState karakterState;
		private BufferedImage karakterLompat;
		private BufferedImage karakterMati;
		private Animasi karakterLari;
		private ManajerSound jumpSound;
		
		public Karakter(Kontrol kontrol) {
			this.kontrol = kontrol;
			karakterLari = new Animasi(150);
			karakterLari.addPotonganGambar(getImage("resources/kanan 0.png"));
			karakterLari.addPotonganGambar(getImage("resources/kanan 1.png"));
			karakterLari.addPotonganGambar(getImage("resources/kanan 2.png"));
			karakterLari.addPotonganGambar(getImage("resources/kanan 3.png"));
			karakterLari.addPotonganGambar(getImage("resources/kanan 4.png"));
			karakterLari.addPotonganGambar(getImage("resources/kiri 0.png"));
			karakterLari.addPotonganGambar(getImage("resources/kiri 0.png"));
			karakterLari.addPotonganGambar(getImage("resources/kiri 1.png"));
			karakterLari.addPotonganGambar(getImage("resources/kiri 2.png"));
			karakterLari.addPotonganGambar(getImage("resources/kiri 3.png"));
			karakterLari.addPotonganGambar(getImage("resources/kiri 4.png"));
			karakterLompat = getImage("resources/kanan 2.png");
			karakterMati = getImage("resources/kiri 2.png");
			jumpSound = new ManajerSound("resources/jump.wav");
			jumpSound.startThread();
			y = GROUND_Y - karakterLompat.getHeight();
			maxY = y;
			highLompatMaxY = setLompatMaxY(GRAVITY);
			lowLompatMaxY = setLompatMaxY(GRAVITY + GRAVITY / 2);
			karakterState = KarakterState.KARAKTER_LOMPAT;
		}
		
		public KarakterState getKarakterState() {
			return karakterState;
		}

		public void setKarakterState(KarakterState karakterState) {
			this.karakterState = karakterState;
		}
		
		public double setLompatMaxY(double gravity) {
			speedY = SPEED_Y;
			y += speedY;
			double jumpMaxY = y;
			while(true) {
				speedY += gravity;
				y += speedY;
				if(y < jumpMaxY)
					jumpMaxY = y;
				if(y + speedY >= GROUND_Y - karakterLari.getPotonganGambar().getHeight()) {
					speedY = 0;
					y = GROUND_Y - karakterLari.getPotonganGambar().getHeight();
					break;
				}
			}
			return jumpMaxY;
		}

		public Rectangle getHitbox() {
			switch (karakterState) {
			case KARAKTER_LARI:
			case KARAKTER_LOMPAT:
			case KARAKTER_MATI:
				return new Rectangle((int)X + HITBOX_LARI[0], (int)y + HITBOX_LARI[1], 
						karakterMati.getWidth() + HITBOX_LARI[2], karakterMati.getHeight() + HITBOX_LARI[3]);
			}
			return null;
		}
		
		public void updatePosition() {
			if(y < maxY)
				maxY = y;
			karakterLari.updatePotonganGambar();
			switch (karakterState) {
			case KARAKTER_LARI:
				y = GROUND_Y - karakterLari.getPotonganGambar().getHeight();
				maxY = y;
				break;
			case KARAKTER_LOMPAT:
				if(y + speedY >= GROUND_Y - karakterLari.getPotonganGambar().getHeight()) {
					speedY = 0;
					y = GROUND_Y - karakterLari.getPotonganGambar().getHeight();
					karakterState = KarakterState.KARAKTER_LARI;
				} else if(kontrol.getNilai()) {
					speedY += GRAVITY;
					y += speedY;
				} else {
					if(maxY <= lowLompatMaxY - (lowLompatMaxY - highLompatMaxY) / 2)
						speedY += GRAVITY;
					else
						speedY += GRAVITY + GRAVITY / 2;
					if(kontrol.isPressedDown())
						speedY += GRAVITY;
					y += speedY;
				}
				break;
			default:
				break;
			}
			
		}
		
		public void jump() {
			if(y == GROUND_Y - karakterLari.getPotonganGambar().getHeight()) {
				jumpSound.play();
				speedY = SPEED_Y;
				y += speedY;
			}
		}
		
		public void resetKarakter() {
			y = GROUND_Y - karakterLompat.getHeight();
			karakterState = KarakterState.KARAKTER_LARI;
		}
		
		public void karakterGameOver() {
			if(y > GROUND_Y - karakterMati.getHeight())
				y = GROUND_Y - karakterMati.getHeight();
			karakterState = KarakterState.KARAKTER_MATI;
		}
		
		public void draw(Graphics g) {
			switch (karakterState) {
			case KARAKTER_LARI:
				g.drawImage(karakterLari.getPotonganGambar(), (int)X, (int)y, null);
				break;
			case KARAKTER_LOMPAT:
				g.drawImage(karakterLompat, (int)X, (int)y, null);
				break;
			case KARAKTER_MATI:
				g.drawImage(karakterMati, (int)X, (int)y, null);
				break;
			default:
				break;
			}
		}
		
//		public void drawHitbox(Graphics g) {
//			g.setColor(Color.GREEN);
//			g.drawRect(getHitbox().x, getHitbox().y, getHitbox().width, getHitbox().height);
//		}
		
}

