package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 关卡失败界面
 * @author DengYuhan
 * @date 2020/3/28
 */
public class LosePanel extends JPanel implements MouseListener, MouseMotionListener, Runnable{
	/**鼠标坐标*/
	private int px;
	private int py;

	/**关卡层数*/
	private int level;
	private Thread thread;
	private boolean play;
	private JFrame frame;
	/**存储所有关卡的分数*/
	private int[] scores = {-1, -1, -1};
	
	private Image loseGame = new ImageIcon("pictures/loseGame.png").getImage();
	
	public LosePanel(JFrame frame, int level, int[] scores) {
		super();
		play = true;
		this.level = level;
		this.frame = frame;
		this.scores = scores;
		if (thread == null || !thread.isAlive()) {
			thread = new Thread(this);
		}
		      thread.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(loseGame, 0, 0, this);
		int tmp = 0;
		//显示得分
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
		//重新开始
		if(px >= 128 && py >= 238 && px <= 300 && py <=273) {
			play = false;
			scores[level-1] = -1;
			thread.stop();
			frame.dispose();
			GameManager g = new GameManager(level, true, scores);
			g.setVisible(true);
		}
		//返回主界面
		if(px >= 128 && py >= 296 && px <= 300 && py <=338) {
			play = false;
			for(int i=0; i<scores.length; i++) {
				scores[i] = -1;
			}
		    thread.stop();  
		    frame.dispose();
			//到主界面
			MyFrame f = new MyFrame();
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
