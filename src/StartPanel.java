package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//开始界面面板
public class StartPanel extends JPanel implements MouseListener, MouseMotionListener, Runnable{
	private int px;//鼠标坐标
	private int py; 
	
	private Thread thread;
	private boolean play;
	private JFrame frame;
	private int[] scores = {-1, -1, -1};//所有关卡得分
//	private int level;//关卡层数
	
//	private Image background;
	private Image start = new ImageIcon("pictures/start.png").getImage();
//	private Image startIcon = new ImageIcon("pictures/startIcon.png").getImage();
	
	public StartPanel(JFrame frame) {
		super();
		play = true;
		this.frame = frame;
//		level = 0;
		if (thread == null || !thread.isAlive())
		      thread = new Thread(this);
		      thread.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(start, 0, 0, this);
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
		if(px >= 86 && py >= 324 && px <= 333 && py <=390) {//开始游戏，到关卡1
			play = false;
			thread.stop();
			frame.dispose();
			GameManager g = new GameManager(1, true, scores);
			g.setVisible(true);
		}
			
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
