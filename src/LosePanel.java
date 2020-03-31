package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//关卡失败界面
public class LosePanel extends JPanel implements MouseListener, MouseMotionListener, Runnable{
	private int px;//鼠标坐标
	private int py; 
	
	private int level;//关卡层数
	private Thread thread;
	private boolean play;
	private JFrame frame;
	private int[] scores = {-1, -1, -1};//存储所有关卡的分数
	
	private Image loseGame = new ImageIcon("pictures/loseGame.png").getImage();
	
	public LosePanel(JFrame frame, int level, int[] scores) {
		super();
		play = true;
		this.level = level;
		this.frame = frame;
		this.scores = scores;
		if (thread == null || !thread.isAlive())
		      thread = new Thread(this);
		      thread.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(loseGame, 0, 0, this);
		int tmp = 0;
		for(int i=0; i<scores.length; i++){
			if(scores[i] != -1) {
				tmp += scores[i];
				String tmpStr = "关卡" + String.valueOf(i+1) + "的得分是：" + String.valueOf(scores[i]);
				g.drawChars(tmpStr.toCharArray(), 0, tmpStr.length(), 150, 150 + 25*i);
			}
		}
		String finalScore = "你的最终得分是：" + String.valueOf(tmp);
		g.drawChars(finalScore.toCharArray(), 0, finalScore.length(), 150, 225);
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
			scores[level-1] = -1;
			thread.stop();
			frame.dispose();
			GameManager g = new GameManager(level, true, scores);
			g.setVisible(true);
		}
		if(px >= 128 && py >= 296 && px <= 300 && py <=338) {//返回主界面
			play = false;
			for(int i=0; i<scores.length; i++) scores[i] = -1;
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
