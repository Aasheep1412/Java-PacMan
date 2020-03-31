package src;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//游戏通关界面
public class WinPanel  extends JPanel{

	private int[] scores = {-1, -1, -1};

	private Image winGame = new ImageIcon("pictures/success.png").getImage();

	public  WinPanel(int[] scores){
		this.scores = scores;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(winGame, 0, 0, this);
		int tmp = 0;
		for(int i=0; i<scores.length; i++){
				tmp += scores[i];
				String tmpStr = "关卡" + String.valueOf(i+1) + "的得分是：" + String.valueOf(scores[i]);
				g.drawChars(tmpStr.toCharArray(), 0, tmpStr.length(), 150, 190 + 25*i);
		}
		String finalScore = "你的最终得分是：" + String.valueOf(tmp);
		g.drawChars(finalScore.toCharArray(), 0, finalScore.length(), 150, 265);
	}


}
