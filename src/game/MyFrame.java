package game;

import javax.swing.*;
import java.awt.*;

/**
 * 初始界面
 * @author DengYuhan
 * @date 2020/3/28
 */
public class MyFrame extends JFrame{
	/**开始界面面板*/
	private StartPanel sPanel;
	
	public MyFrame() {
		super("Pac man");
		sPanel = new StartPanel(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		sPanel.addMouseListener(sPanel);
		sPanel.addMouseMotionListener(sPanel);
        getContentPane().add(sPanel, BorderLayout.CENTER);
       
        setSize(435,465);   
	    setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		try {
			JFrame frame = new MyFrame();
			frame.setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
