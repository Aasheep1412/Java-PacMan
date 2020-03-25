package src;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class WinPanel  extends JPanel{
	
	private Image winGame = new ImageIcon("pictures/success.png").getImage();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(winGame, 0, 0, this);
	}


}
