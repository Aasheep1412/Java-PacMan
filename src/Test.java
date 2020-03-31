package src;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Test extends JFrame{

	public static void main(String[] args) {
		try {
			JFrame frame = new MyFrame();
			frame.setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
