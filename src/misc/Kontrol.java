package misc;

import static user_interface.GameWindow.SCREEN_HEIGHT;
import static user_interface.GameWindow.SCREEN_WIDTH;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import user_interface.GameScreen;

public class Kontrol {
	private int jawBenar;
	private Kata data_kata[];
	private boolean nilai;
	private GameScreen gameScreen;
	private String level;
	

	
	public JLabel pressUp = new JLabel();

	public JLabel textIng;
	public JButton button;
	public JButton button2;
	public JButton button3;
	public JButton lEasy;
	public JButton lHard;
	public Font font;
	public JLabel levelLabel;
	
	private boolean isPressedUp = false;
	private boolean isPressedDown = false;
	

		
	public Kontrol(JFrame jframe, GameScreen gamescreen) throws Exception {
		
//		
		//font
		//String fName = "/Raleway-VariableFont_wght.ttf";
	    //InputStream is = Kontrol.class.getResourceAsStream(fName);
//	    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
	    
	    //font textIng
	    String fName2 = "/OpenSans-SemiBold.ttf";
	    InputStream is2 = Kontrol.class.getResourceAsStream(fName2);
	    Font font2 = Font.createFont(Font.TRUETYPE_FONT, is2);
	    
	    //image
	    ImageIcon bBesar1 = new ImageIcon(getClass().getResource("/b-240x60.png"));
		
		level = "Easy";
		textIng = new JLabel("");
		textIng.setFont(font2.deriveFont(Font.BOLD, 30f));
		
		button = new JButton("Mulai", bBesar1);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFont(font2.deriveFont(Font.BOLD, 20f));
		button.addActionListener (new Action1());
		//button.setPreferredSize(new Dimension(240, 60));
		
		button2 = new JButton("", bBesar1);
		button2.setVerticalTextPosition(JButton.CENTER);
		button2.setHorizontalTextPosition(JButton.CENTER);
		button2.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setFont(font2.deriveFont(Font.BOLD, 20f));
		button2.addActionListener (new Action2());
		//button2.setPreferredSize(new Dimension(240, 60));
		
		this.data_kata = makeData();
		this.nilai = false;
		this.gameScreen = gamescreen;
		
		button3 = new JButton("Level", bBesar1);
		button3.setVerticalTextPosition(JButton.CENTER);
		button3.setHorizontalTextPosition(JButton.CENTER);
		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button3.setFont(font2.deriveFont(Font.BOLD, 20f));
		button3.addActionListener (new Action3());
		//button3.setPreferredSize(new Dimension(240, 60));
		
		lEasy = new JButton("Mudah", bBesar1);
		lEasy.setVerticalTextPosition(JButton.CENTER);
		lEasy.setHorizontalTextPosition(JButton.CENTER);
		lEasy.setBorderPainted(false);
		lEasy.setContentAreaFilled(false);
		lEasy.setFont(font2.deriveFont(Font.BOLD, 20f));
		lEasy.addActionListener (new Action3());
		//lEasy.setPreferredSize(new Dimension(240, 60));
		
		lHard = new JButton("Susah", bBesar1);
		lHard.setVerticalTextPosition(JButton.CENTER);
		lHard.setHorizontalTextPosition(JButton.CENTER);
		lHard.setBorderPainted(false);
		lHard.setContentAreaFilled(false);
		lHard.setFont(font2.deriveFont(Font.BOLD, 20f));
		lHard.addActionListener (new Action3());
		//lHard.setPreferredSize(new Dimension(240, 60));
		
		levelLabel = new JLabel("Level: " + this.level);
		levelLabel.setFont(font2.deriveFont(Font.BOLD, 30f));

		
	}
			
	public Kata[] makeData() {
		String noise[] = {"kebisingan", "suara", "bunyi", "keributan", "kegaduhan"};
		String courage[] = {"keberanian", "keteguhan hati"};
		String juvenile[] = {"muda", "remaja"};
		String enchant[] = {"mempesona", "mengasyikkan", "memikat"};
		String hill[] = {"tumpukan", "bukit", "timbunan"};
		String deliberate[] = {"tidak tergesa-gesa", "tenang", "sengaja"};
		String arouse[] = {"membangkitkan", "membangunkan", "menggugah"};
		String illuminate[] = {"menerangi", "menyinari"};
		String elucidate[] = {"menjelaskan", "menguraikan", "menerangkan"};
		String sanity[] = {"Kewarasan", "Kesehatan", "Kesehatan jiwa", "Kesegaran"};
		String lizard[] = {"Cicak", "Kadal"};
		String bridge[] = {"Jembatan", "Batang", "Kuda-kuda", "Titian"};
		String carry[] = {"membawa", "mengangkat"};
		String efficacy[] = {"kemanjuran", "kemujaraban"};
		String post[] = {"pos", "tiang", "surat", "memasang"};
		String underlying[] = {"pokok", "dasar"};
		
		Kata kata[] = {
				new Kata(" Noise", noise),
				new Kata("Sanity", sanity),
				new Kata("Lizard", lizard),
				new Kata("Bridge", bridge),
				new Kata("Courage", courage),
				new Kata("Juvenile", juvenile),
				new Kata(" Hill", hill),
				new Kata("Deliberate", deliberate),
				new Kata("Arouse", arouse),
				new Kata("Illuminate", illuminate),
				new Kata("Elucidate", elucidate),
				new Kata(" Carry", carry),
				new Kata("Efficacy", efficacy),
				new Kata(" Post", post),
				new Kata("Underlying", underlying),
				new Kata("Enchant", enchant)
		};
		
		return kata;
	}
	
	public int makeRandomint(int min, int max) {
		int index = (int)(Math.random() * (max - min + 1) + min);
		return index;
	}
	
	public int RandomKecohInd(int min, int max, int index_ing) {
		int index = makeRandomint(min, max);
		if (index == index_ing) {
			if (index == max) {
				index = index - 1;
			}
			else {
				index = index + 1;
			}
		}
		
		return index;
	}
	
	public void showArti(String arti, String kecoh, String kataInggris) {
		int acak = makeRandomint(1, 2);
		String satu = "";
		String dua = "";
		jawBenar = acak;
		
		textIng.setText(kataInggris);		
		if (acak==1) {
			satu = arti;
			dua = kecoh;
		}
		
		else {
			satu = kecoh;
			dua = arti;
		}
		
		button.setText(satu);			
		button2.setText(dua);
		
	}
	
	class Action1 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {    
			  
			if (e.getActionCommand().equals("Mulai")) {
				gameScreen.rButton(button3);
				gameScreen.rButton(lEasy);
				gameScreen.rButton(lHard);
				gameScreen.rButton(button);
				gameScreen.addButton(button, SCREEN_WIDTH/2 - 240, SCREEN_HEIGHT/3, 240, 60);
				gameScreen.addButton(button2, SCREEN_WIDTH/2 , SCREEN_HEIGHT/3, 240, 60);
				gameScreen.addLabel(textIng, SCREEN_WIDTH/2 - 60, SCREEN_HEIGHT/4, 240, 60);
				UpdateTombol();
				gameScreen.releaseUpAction();
			}
			else if (e.getActionCommand().equals("Mulai Lagi")) {
				gameScreen.rButton(button);
				gameScreen.rButton(button3);
				gameScreen.rButton(lEasy);
				gameScreen.rButton(lHard);
				gameScreen.addButton(button, SCREEN_WIDTH/2 - 240, SCREEN_HEIGHT/3, 240, 60);
				gameScreen.addButton(button2, SCREEN_WIDTH/2 , SCREEN_HEIGHT/3, 240, 60);
				gameScreen.addLabel(textIng, SCREEN_WIDTH/2 - 60, SCREEN_HEIGHT/4, 240, 60);
				UpdateTombol();
				gameScreen.releaseUpAction();
			}
		    if (jawBenar==1) {
		    	setNilai(true);
		    	UpdateTombol();
			}
		    else {
		    	setNilai(false);
			}
		    
		 }
		  
	}
	
	
	class Action2 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     		    
		    if (jawBenar==2) {
		    	setNilai(true);
		    	UpdateTombol();
			}
		    else {
		    	setNilai(false);
			}
//		   
			
		  }
		  
	}
	
	class Action3 implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			if(e.getActionCommand().equals("Level")) {
				System.out.println("Masuk");
				gameScreen.rButton(button);
				gameScreen.rButton(button3);
				gameScreen.addButton(lEasy,  SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3, 240, 60);
				gameScreen.addButton(lHard, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3 + 80, 240, 60);
				lEasy.setText("Easy");
				lHard.setText("Hard");
//				
			}
			else if(e.getActionCommand().equals("Easy")) {
				gameScreen.rButton(lEasy);
				gameScreen.rButton(lHard);
				level = "Easy";
				levelLabel.setText("Level: " + level);
				gameScreen.addButton(button, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3, 240, 60);
				gameScreen.addButton(button3, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3 + 80, 240, 60);
			}
			else if(e.getActionCommand().equals("Hard")) {
				gameScreen.rButton(lEasy);
				gameScreen.rButton(lHard);
				level = "Hard";
				levelLabel.setText("Level: " + level);
				gameScreen.addButton(button, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3, 240, 60);
				gameScreen.addButton(button3, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3 + 80, 240, 60);
			}
		}
	}
	
	
	
	public void UpdateTombol() {
		int index_ing = makeRandomint(0, data_kata.length -1);
		int index_kecoh = RandomKecohInd(0, data_kata.length -1, index_ing);
		
		Kata kataBing = data_kata[index_ing];
		Kata kataKecoh = data_kata[index_kecoh];
		
		
		String bing = kataBing.getKata_inggris();
		String arti = kataBing.get_arti(level);
		String kecoh = kataKecoh.get_arti(level);
		
		showArti(arti, kecoh, bing);

	}
	
	
	public void setNilai(boolean inpNilai) {
		this.nilai = inpNilai;
	}
	
	public boolean getNilai() {
		return this.nilai;
	}
	
	public void setButton(JButton button, String text) {
		button.setText(text);
	}
	
	public boolean isPressedUp() {
		return isPressedUp;
	}

	public boolean isPressedDown() {
		return isPressedDown;
	}
}
