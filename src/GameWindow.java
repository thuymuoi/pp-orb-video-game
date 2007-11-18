import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameWindow extends JFrame {
	JPanel mainPanel;
	Graphics graphics = null;
	public GameWindow (){
		super("Orbit");
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		setVisible(true);
		setSize(new Dimension(640,480));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphics = getGraphics();
	}
	public void draw(Player player){
		graphics.fillOval(player.getMainPosition().x-7, player.getMainPosition().y-7, 14, 14);
		graphics.drawOval(player.getThrustPosition().x-2, player.getThrustPosition().y-2, 4, 4);
	}
	public void clear() {
		graphics.clearRect(0,0, 640, 480);
		
	}

}
