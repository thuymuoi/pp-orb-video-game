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
	}

}
