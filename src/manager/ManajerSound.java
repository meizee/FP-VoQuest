package manager;

import static util.Resource.getSound;

public class ManajerSound implements Runnable {

	public static int WAIT_TIME = 0;
	
	Thread thread;
	
	private boolean playSound = false;
	private String path;

	public ManajerSound(String path) {
		thread = new Thread(this);
		this.path = path;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(playSound) {
//				System.out.println("play " + path);
				getSound(path).start();
				playSound = false;
			}
		}
	}

	public void startThread() {
		thread.start();
	}
	
	public void play() {
		playSound = true;
	}
	
}
