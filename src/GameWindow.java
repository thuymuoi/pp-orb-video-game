import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
		double tailExtension;
		tailExtension = Math.random() * 10; 
		graphics.fillOval(player.getMainPosition().x-7, player.getMainPosition().y-7, 14, 14);
		graphics.drawOval(player.getThrustPosition().x-2, player.getThrustPosition().y-2, 4, 4);
		if(Orbit.key1 == true)
		{
			graphics.drawLine(player.getThrustPosition().x, player.getThrustPosition().y,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
			graphics.drawLine(player.getThrustPosition().x-2, player.getThrustPosition().y-2,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
			graphics.drawLine(player.getThrustPosition().x+2, player.getThrustPosition().y+2,  (int) (player.getThrustPosition().x-2+Math.cos(player.getThrusterAngle())*(10+tailExtension)), (int) (player.getThrustPosition().y-2+Math.sin(player.getThrusterAngle())*(10+tailExtension)));
		}
	}
	public void clear() {
		graphics.clearRect(0,0, 640, 480);
		
	}
	public void draw(ArrayList<Line> lines){
		for(Line line:lines){
			graphics.drawLine((int)(line.getStartP().x), (int)(line.getStartP().y), (int)(line.getEndP().x), (int)(line.getEndP().y));
		}
	}

}
