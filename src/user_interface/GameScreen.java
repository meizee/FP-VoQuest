package user_interface;

import static user_interface.GameWindow.SCREEN_HEIGHT;
import static user_interface.GameWindow.SCREEN_WIDTH;
import static util.Resource.getImage;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game_object.Karakter;
import game_object.Tanah;
import game_object.Score;
import manager.ManajerKontrol;
import manager.ManajerBatuan;
import manager.ManajerSound;
import misc.Kontrol;
import misc.KarakterState;
import misc.GameState;

@SuppressWarnings(value = { "serial" })
public class GameScreen extends JPanel implements Runnable {

	private Thread thread;
	
	private static final int STARTING_SPEED_X = -5;
	private static final double DIFFICULTY_INC = -0.0002;
	
	public static final double GRAVITY = 0.4;
	public static final int GROUND_Y = 500;
	public static final double SPEED_Y = -12;
	
	private final int FPS = 100;
	private final int NS_PER_FRAME = 1_000_000_000 / FPS;
	
	private double speedX = STARTING_SPEED_X;
	private GameState gameState = GameState.GAME_STATE_START;
	private int introCountdown = 1000;
	private boolean introJump = true;

	private boolean collisions = true;
	
	private Kontrol kontrol;
	private Score score;
	private Karakter karakter;
	private Tanah tanah;
	private ManajerBatuan eManager;
	private ManajerSound gameOverSound;
	private ManajerKontrol cManager;;
	
	public GameScreen(JFrame jframe) throws Exception {
		thread = new Thread(this);
		kontrol = new Kontrol(jframe, this);
		super.add(kontrol.pressUp);

		cManager = new ManajerKontrol(kontrol, this);
		score = new Score(this);
		karakter = new Karakter(kontrol);
		tanah = new Tanah(this);

		eManager = new ManajerBatuan(this);
		gameOverSound = new ManajerSound("resources/dead.wav");
		gameOverSound.startThread();
		setLayout(null);
		kontrol.button.setBounds(SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3, 240, 60);
		kontrol.button3.setBounds(SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3 + 80, 240, 60);
		
		
		add(kontrol.button);
		add(kontrol.button3);
		addLabel(kontrol.levelLabel, 5, 5, 200, 50);
		
	}
	
	public void startThread() {
		thread.start();
	}
	
	@Override
	public void run() {
		long prevFrameTime = System.nanoTime();
		int waitingTime = 0;
		int count = 0;
		while(true) {
			if(cManager.update()) {
				count++;
			}
			else {
				count = 0;
			}
			if(count>=50) {
				kontrol.setNilai(false);
			}
			updateFrame();
			repaint();
			waitingTime = (int)((NS_PER_FRAME - (System.nanoTime() - prevFrameTime)) / 1_000_000);
			if(waitingTime < 0)
				waitingTime = 1;
			ManajerSound.WAIT_TIME = waitingTime;
			// pause
			if(gameState == GameState.GAME_STATE_OVER) {
				kontrol.button.setBounds(SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3, 240, 60);
				kontrol.setButton(kontrol.button, "Mulai Lagi");
				add(kontrol.button3);
//				kontrol.setButton(kontrol.button2, "Main Menu");
				remove(kontrol.button2);
				remove(kontrol.textIng);
				waitingTime = 1000;
			}
				
			try {
				Thread.sleep(waitingTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			prevFrameTime = System.nanoTime();
		}
	}
	
	public double getSpeedX() {
		return speedX;
	}

	public GameState getGameState() {
		return gameState;
	}
	
	public void setGameState(GameState state) {
		this.gameState = state;
	}

	// update posisi objek
	private void updateFrame() {
		switch (gameState) {
		case GAME_STATE_INTRO:
			karakter.updatePosition();
			if(!introJump && karakter.getKarakterState() == KarakterState.KARAKTER_LARI)
				tanah.updatePosition();
			
			introCountdown += speedX;
			if(introCountdown <= 0)
				gameState = GameState.GAME_STATE_IN_PROGRESS;
			if(introJump) {
				karakter.jump();
				karakter.setKarakterState(KarakterState.KARAKTER_LOMPAT);
				introJump = false;
			}
			break;
			
		case GAME_STATE_IN_PROGRESS:
			speedX += DIFFICULTY_INC;
			karakter.updatePosition();
			tanah.updatePosition();
			eManager.updatePosisi();
			if(collisions && eManager.isCollision(karakter.getHitbox())) {
				gameState = GameState.GAME_STATE_OVER;
				karakter.karakterGameOver();
				score.writeScore();
				gameOverSound.play();
			}
			score.scoreUp();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(246, 246, 246));
		g.fillRect(0, 0, getWidth(), getHeight());
		switch (gameState) {
		case GAME_STATE_START:
			startScreen(g);
			break;
		case GAME_STATE_INTRO:
			introScreen(g);
			break;
		case GAME_STATE_IN_PROGRESS:
			inProgressScreen(g);
			break;
		case GAME_STATE_OVER:
			gameOverScreen(g);
			break;
		case GAME_STATE_PAUSED:
			pausedScreen(g);
			break;
		default:
			break;
		}
	}
	

	
	public void startScreen(Graphics g) {
		tanah.draw(g);
		karakter.draw(g);
		BufferedImage introImage = getImage("resources/intro-text.png");
		Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, introCountdown / 1000f));		
		g2d.drawImage(introImage, SCREEN_WIDTH / 2 - introImage.getWidth() / 2, SCREEN_HEIGHT / 4 - introImage.getHeight(), null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	private void introScreen(Graphics g) {
		startScreen(g);
	}
	
	private void inProgressScreen(Graphics g) {
		tanah.draw(g);
		eManager.draw(g);
		karakter.draw(g);
		score.draw(g);
	}
	
	private void gameOverScreen(Graphics g) {
		inProgressScreen(g);
		BufferedImage gameOverImage = getImage("resources/game-over.png");
		g.drawImage(gameOverImage, SCREEN_WIDTH / 2 - gameOverImage.getWidth() / 2, SCREEN_HEIGHT / 2 - gameOverImage.getHeight() * 5/2, null);
		
	}
	
	private void pausedScreen(Graphics g) {
		inProgressScreen(g);
		BufferedImage pausedImage = getImage("resources/paused.png");
		g.drawImage(pausedImage, SCREEN_WIDTH / 2 - pausedImage.getWidth() / 2, SCREEN_HEIGHT / 2 - pausedImage.getHeight(), null);
	}
	
	public void pressUpAction() {
		if(gameState == GameState.GAME_STATE_IN_PROGRESS) {
			karakter.jump();
			karakter.setKarakterState(KarakterState.KARAKTER_LOMPAT);
		}
	}
	
	public void releaseUpAction() {
		if(gameState == GameState.GAME_STATE_START)
			gameState = GameState.GAME_STATE_INTRO;
		if(gameState == GameState.GAME_STATE_OVER) {
			speedX = STARTING_SPEED_X;
			score.scoreReset();
			eManager.clearBatuan();
			karakter.resetKarakter();
			tanah.resetTanah();
			gameState = GameState.GAME_STATE_IN_PROGRESS;
		}
	}
	
	public void addButton(JButton button, int x, int y, int width, int height) {
		button.setBounds(x, y, width, height);
		add(button);
	}
	
	public void addLabel(JLabel label, int x, int y, int width, int height) {
		label.setBounds(x, y, width, height);
		label.setBackground(Color.GRAY);
		add(label);
	}
	
	public void rButton(JButton button) {
		remove(button);
	}
	
}