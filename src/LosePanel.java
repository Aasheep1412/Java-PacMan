package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LosePanel extends JPanel implements MouseListener, MouseMotionListener, Runnable{
	private int px;
	private int py; 
	
	private int level;
	private Thread thread;
	private boolean play;
	private JFrame frame;
	
	private Image loseGame = new ImageIcon("pictures/loseGame.png").getImage();
	
	public LosePanel(JFrame frame, int level) {
		super();
		play = true;
		this.level = level;
		this.frame = frame;
		if (thread == null || !thread.isAlive())
		      thread = new Thread(this);
		      thread.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(loseGame, 0, 0, this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (play) {
	         try {
	            Thread.sleep(100);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         repaint(); 
	      }
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		px = e.getX();
		py = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		px = e.getX();
		py = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(px >= 128 && py >= 238 && px <= 300 && py <=273) {//重新开始
			play = false;
			thread.stop();
			frame.dispose();
			GameManager g = new GameManager(level, true);
			g.setVisible(true);
		}
		if(px >= 128 && py >= 296 && px <= 300 && py <=338) {
			play = false;
		    thread.stop();  
		    frame.dispose();
			MyFrame f = new MyFrame(); //到主界面
			f.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
