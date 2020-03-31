package src;

import java.awt.BorderLayout;

import javax.swing.JFrame;

//初始界面
public class MyFrame extends JFrame{
	private StartPanel st;//开始界面面板
	
	public MyFrame() {
		super("Pac man");
		st = new StartPanel(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
	    st.addMouseListener(st);
	    st.addMouseMotionListener(st);
        getContentPane().add(st, BorderLayout.CENTER);
       
        setSize(435,465);   
	    setLocationRelativeTo(null);
	}
}
