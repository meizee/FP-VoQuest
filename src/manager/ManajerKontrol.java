package manager;

import misc.Kontrol;
import user_interface.GameScreen;

public class ManajerKontrol {
	Kontrol kontrol;
	GameScreen gameScreen;
	
	public ManajerKontrol(Kontrol kontrol, GameScreen gameScreen) {
		this.kontrol = kontrol;
		this.gameScreen = gameScreen;
	}
	
	public boolean update() {
		if(kontrol.getNilai()) {
			gameScreen.pressUpAction();
			System.out.println("Press up");
			return true;
		}
		
		return false;
			
	}
}
