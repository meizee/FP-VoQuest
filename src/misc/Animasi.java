package misc;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animasi {

	private List<BufferedImage> potonganGambar;
	private int currentPotonganGambarIndex = 0;
	private int updateWaktu;
	private long lastUpdateWaktu = 0;
	
	public Animasi(int updateWaktu) {
		this.updateWaktu = updateWaktu;
		potonganGambar = new ArrayList<BufferedImage>();
	}
	
	// update satuPotonganGambar setiap set milliseconds
	public void updatePotonganGambar() {
		if(System.currentTimeMillis() - lastUpdateWaktu >= updateWaktu) {
			currentPotonganGambarIndex++;
			if(currentPotonganGambarIndex >= potonganGambar.size())
				currentPotonganGambarIndex = 0;
			lastUpdateWaktu = System.currentTimeMillis();
		}
	}
	
	public void addPotonganGambar(BufferedImage satuPotonganGambar) {
		potonganGambar.add(satuPotonganGambar);
	}
	
	public BufferedImage getPotonganGambar() {
		if(potonganGambar.size() > 0) {
			return potonganGambar.get(currentPotonganGambarIndex);
		}
		return null;
	}
}
