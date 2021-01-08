package misc;

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

public class TestCustomFont {

    public TestCustomFont() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

               
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new GridBagLayout());
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Raleway-VariableFont_wght.ttf"));
//                JLabel happy = new JLabel("Happy little Miss Chicken");
//                happy.setFont(font.deriveFont(Font.BOLD, 48f));
//                add(happy);
            } catch (FontFormatException | IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
