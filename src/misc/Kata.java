package misc;

public class Kata {
	String kata_inggris;
	String kata_indo[];
	int panjang_kataIndo;
	
	public Kata(String input_inggris, String input_indo[]) {
		this.kata_inggris = input_inggris;
		this.kata_indo = input_indo.clone();
		this.panjang_kataIndo = input_indo.length;
	}

	public String getKata_inggris() {
		return kata_inggris;
	}

	public void setKata_inggris(String kata_inggris) {
		this.kata_inggris = kata_inggris;
	}

	public String[] getKata_indo() {
		return kata_indo;
	}

	public void setKata_indo(String[] kata_indo) {
		this.kata_indo = kata_indo;
	}

	public int getPanjang_kataIndo() {
		return panjang_kataIndo;
	}

	public void setPanjang_kataIndo(int panjang_kataIndo) {
		this.panjang_kataIndo = panjang_kataIndo;
	}
	
	public String get_arti(String level) {
		if (level.equals("Easy")){
			return this.kata_indo[0];
		}
		else {
			int min = 0;
			int max = this.panjang_kataIndo - 1;
			int index = (int)(Math.random() * (max - min + 1) + min);
			return this.kata_indo[index];
		}
	}

}
