package misc;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import user_interface.GameScreen;
import user_interface.GameWindow;
//import misc.TestCustomFont.TestPane;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Controls{
	
	private int jawBenar;
//	private JFrame frame;
//	public JPanel panel;
	private Kata data_kata[];
	private boolean nilai;
	private GameScreen gameScreen;
	private String level;
	
//	private static final int FOCUS_STATE = JComponent.WHEN_IN_FOCUSED_WINDOW;
//	
//	private static final String UP = "UP";
//	private static final String DOWN = "DOWN";
//	private static final String W_UP = "W_UP";
//	private static final String S_DOWN = "S_DOWN";
//	private static final String SPACE_UP = "SPACE_UP";
//	private static final String DEBUG_MENU = "DEBUG_MENU";
//	private static final String P_PAUSE = "P";
//	private static final String ESCAPE_PAUSE = "ESCAPE";
//	
//	private static final String RELEASED_UP = "RELEASED_UP";
//	private static final String RELEASED_DOWN = "RELEASED_DOWN";
//	private static final String RELEASED_W_UP = "RELEASED_W_UP";
//	private static final String RELEASED_S_DOWN = "RELEASED_S_DOWN";
//	private static final String RELEASED_SPACE_UP = "RELEASED_SPACE_UP";
	
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
	
	private boolean isPressedUp = false;
	private boolean isPressedDown = false;
		
	public Controls(JFrame jframe, GameScreen gamescreen) {
		
		try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Raleway-VariableFont_wght.ttf"));
            //JLabel happy = new JLabel("Happy little Miss Chicken");
            //happy.setFont(font.deriveFont(Font.BOLD, 48f));
            //add(happy);
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }
		
		level = "Easy";
		textIng = new JLabel("");
		//setFont(font.deriveFont(Font.BOLD, 48f));
		button = new JButton("Mulai");
		//button.setFont(font.deriveFont(Font.BOLD, 48f));
		button.addActionListener (new Action1());
		button.setPreferredSize(new Dimension(100, 40));
		
		button2 = new JButton("");
		button2.addActionListener (new Action2());
		button2.setPreferredSize(new Dimension(100, 40));
		this.data_kata = makeData();
		this.nilai = false;
		this.gameScreen = gamescreen;
		
		button3 = new JButton("Level");
		button3.addActionListener (new Action3());
		button3.setPreferredSize(new Dimension(100, 40));
		
		lEasy = new JButton("Mudah");
		lEasy.addActionListener (new Action3());
		lEasy.setPreferredSize(new Dimension(100, 40));
		
		lHard = new JButton("Susah");
		lHard.addActionListener (new Action3());
		lHard.setPreferredSize(new Dimension(100, 40));

		
	}
	
//	public void TestPane() {
//        //setLayout(new GridBagLayout());
//        try {
//            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Raleway-VariableFont_wght.ttf"));
//            //JLabel happy = new JLabel("Happy little Miss Chicken");
//            //happy.setFont(font.deriveFont(Font.BOLD, 48f));
//            //add(happy);
//        } catch (FontFormatException | IOException ex) {
//            ex.printStackTrace();
//        }
//    }
	
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
				gameScreen.addButton(button2);
				gameScreen.addLabel(textIng);
				UpdateTombol();
				gameScreen.releaseUpAction();
			}
			else if (e.getActionCommand().equals("Mulai Lagi")) {
				gameScreen.addButton(button2);
				gameScreen.addLabel(textIng);
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
//		    if (e.getActionCommand().equals("Main Menu")) {
//		    	System.out.println("MAIN MENU");
//		    	gameScreen.setGameState(GameState.GAME_STATE_INTRO);
//		    }
			
		  }
		  
	}
	
	class Action3 implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			if(e.getActionCommand().equals("Level")) {
				System.out.println("Masuk");
				gameScreen.addButton(lEasy);
				gameScreen.addButton(lHard);
				lEasy.setText("Easy");
				lHard.setText("Hard");
//				UpdateTombol();
//				gameScreen.setGameState(GameState.GAME_STATE_START);
			}
			else if(e.getActionCommand().equals("Easy")) {
				gameScreen.rButton(lEasy);
				gameScreen.rButton(lHard);
				level = "Easy";
			}
			else if(e.getActionCommand().equals("Hard")) {
				gameScreen.rButton(lEasy);
				gameScreen.rButton(lHard);
				level = "Expert";
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

	private class PressUpAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
//			System.out.println("up");
			isPressedUp = true;
		}
	}
	
	private class ReleaseUpAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
//			System.out.println("released up");
			gameScreen.releaseUpAction();
			isPressedUp = false;
		}
	}
	
//	private class PressDownAction extends AbstractAction {
//		@Override
//		public void actionPerformed(ActionEvent e) {
////			System.out.println("down");
//			isPressedDown = true;
//		}
//	}
//	
//	private class ReleaseDownAction extends AbstractAction {
//		@Override
//		public void actionPerformed(ActionEvent e) {
////			System.out.println("released down");
//			gameScreen.releaseDownAction();
//			isPressedDown = false;
//		}
//	}
//	
//	private class PressDebugAction extends AbstractAction {
//		@Override
//		public void actionPerformed(ActionEvent e) {
////			System.out.println("press debug");
//			gameScreen.pressDebugAction();
//		}
//	}
//	
//	private class PressPauseAction extends AbstractAction {
//		@Override
//		public void actionPerformed(ActionEvent e) {
////			System.out.println("press pause");
//			gameScreen.pressPauseAction();
//		}
//	}
	

}