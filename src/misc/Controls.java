package misc;

import static user_interface.GameWindow.SCREEN_HEIGHT;
import static user_interface.GameWindow.SCREEN_WIDTH;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import javax.swing.*;
import user_interface.GameScreen;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Controls{
    
	
	private int jawBenar;
//	private JFrame frame;
//	public JPanel panel;
	private Kata data_kata[];
	private boolean nilai;
	private GameScreen gameScreen;
	private String level;
	

	
	public JLabel pressUp = new JLabel();
	public JLabel releaseUp = new JLabel();
	public JLabel pressDown = new JLabel();
	public JLabel releaseDown = new JLabel();
	public JLabel pressDebug = new JLabel();
	public JLabel pressPause = new JLabel();
	public JLabel textIng;
	public JButton button;
	public JButton button2;
	public JButton button3;
	public JButton lEasy;
	public JButton lHard;
	public Font font;
	
	private boolean isPressedUp = false;
	private boolean isPressedDown = false;
	

		
	public Controls(JFrame jframe, GameScreen gamescreen) throws Exception {
		
//		
		//font body
		String fName = "/Raleway-VariableFont_wght.ttf";
	    InputStream is = Controls.class.getResourceAsStream(fName);
	    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
	    
	    //font textIng
	    String fName2 = "/OpenSans-SemiBold.ttf";
	    InputStream is2 = Controls.class.getResourceAsStream(fName2);
	    Font font2 = Font.createFont(Font.TRUETYPE_FONT, is2);
	    
	    //image
	    ImageIcon imageForOne = new ImageIcon(getClass().getResource("/buttons.png"));
		
		level = "Easy";
		textIng = new JLabel("");
		textIng.setFont(font2.deriveFont(Font.BOLD, 30f));
		
		
		button = new JButton("Mulai");
		button.setVerticalTextPosition(JButton.CENTER);
		button.setHorizontalTextPosition(JButton.CENTER);
//		button.setBorderPainted(false);
//		button.setContentAreaFilled(false);
		button.setFont(font2.deriveFont(Font.BOLD, 20f));
		button.addActionListener (new Action1());
		//button.setPreferredSize(new Dimension(240, 60));
		
		button2 = new JButton("");
		button2.setVerticalTextPosition(JButton.CENTER);
		button2.setHorizontalTextPosition(JButton.CENTER);
//		button2.setBorderPainted(false);
//		button2.setContentAreaFilled(false);
		button2.setFont(font2.deriveFont(Font.BOLD, 20f));
		button2.addActionListener (new Action2());
		//button2.setPreferredSize(new Dimension(240, 60));
		
		this.data_kata = makeData();
		this.nilai = false;
		this.gameScreen = gamescreen;
		
		button3 = new JButton("Level");
		button3.setVerticalTextPosition(JButton.CENTER);
		button3.setHorizontalTextPosition(JButton.CENTER);
//		button3.setBorderPainted(false);
//		button3.setContentAreaFilled(false);
		button3.setFont(font2.deriveFont(Font.BOLD, 20f));
		button3.addActionListener (new Action3());
		//button3.setPreferredSize(new Dimension(240, 60));
		
		lEasy = new JButton("Mudah");
		lEasy.setVerticalTextPosition(JButton.CENTER);
		lEasy.setHorizontalTextPosition(JButton.CENTER);
//		lEasy.setBorderPainted(false);
//		lEasy.setContentAreaFilled(false);
		lEasy.addActionListener (new Action3());
		//lEasy.setPreferredSize(new Dimension(240, 60));
		
		lHard = new JButton("Susah");
		lHard.setVerticalTextPosition(JButton.CENTER);
		lHard.setHorizontalTextPosition(JButton.CENTER);
//		lHard.setBorderPainted(false);
//		lHard.setContentAreaFilled(false);
		lHard.addActionListener (new Action3());
		//lHard.setPreferredSize(new Dimension(240, 60));

		
	}
	
	public Kata[] makeData() {
		String noise[] = {"Kebisingan", "Suara", "Bunyi", "Keributan", "Kegaduhan"};
		String sanity[] = {"Kewarasan", "Kesehatan", "Kesehatan jiwa", "Kesegaran"};
		String lizard[] = {"Cicak", "Kadal"};
		//String chick[]
		Kata kata[] = {
				new Kata("Noise", noise),
				new Kata("Sanity", sanity),
				new Kata("Lizard", lizard)
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
		
		System.out.println("Masuk arti");
		
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
				gameScreen.addButton(button, SCREEN_WIDTH/2 - 240, SCREEN_HEIGHT/3, 200, 50);
				gameScreen.addButton(button2, SCREEN_WIDTH/2 , SCREEN_HEIGHT/3, 200, 50);
				gameScreen.addLabel(textIng, SCREEN_WIDTH/2 - 60, SCREEN_HEIGHT/4, 200, 50);
				UpdateTombol();
				gameScreen.releaseUpAction();
			}
			else if (e.getActionCommand().equals("Mulai Lagi")) {
				gameScreen.rButton(button3);
				gameScreen.rButton(button);
				gameScreen.addButton(button, SCREEN_WIDTH/2 - 240, SCREEN_HEIGHT/3, 200, 50);
				gameScreen.addButton(button2, SCREEN_WIDTH/2 , SCREEN_HEIGHT/3, 200, 50);
				gameScreen.addLabel(textIng, SCREEN_WIDTH/2 - 60, SCREEN_HEIGHT/4, 200, 50);
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
				gameScreen.addButton(button, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3, 240, 60);
				gameScreen.addButton(button3, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/3 + 80, 240, 60);
			}
			else if(e.getActionCommand().equals("Hard")) {
				gameScreen.rButton(lEasy);
				gameScreen.rButton(lHard);
				level = "Expert";
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

//	private class PressUpAction extends AbstractAction {
//		@Override
//		public void actionPerformed(ActionEvent e) {
////			System.out.println("up");
//			isPressedUp = true;
//		}
//	}
//	
//	private class ReleaseUpAction extends AbstractAction {
//		@Override
//		public void actionPerformed(ActionEvent e) {
////			System.out.println("released up");
//			gameScreen.releaseUpAction();
//			isPressedUp = false;
//		}
//	}
	

	

}