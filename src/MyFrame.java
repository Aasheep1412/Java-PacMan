package src;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	private StartPanel st;
	
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
