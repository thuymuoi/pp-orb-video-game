import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameWindow extends JFrame {
	JPanel mainPanel;
	public GameWindow (){
		super("Orbit");
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		setVisible(true);
		setSize(new Dimension(640,480));
	}

}
