package src;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager extends JFrame  {
 
   private LevelOne panel1;
   private LevelTwo panel2;
   private LevelThree panel3;
   public GameManager frame;
   
   public GameManager(int levelNum) {
      super("Pac man");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      if(levelNum == 1) {  //关卡1
    	  getContentPane().add(getPanel1(), BorderLayout.CENTER);
      }
      else if(levelNum == 2) {  //关卡2
    	  getContentPane().add(getPanel2(), BorderLayout.CENTER);
      } 
      else if(levelNum == 3) {   //关卡3
    	  getContentPane().add(getPanel3(), BorderLayout.CENTER);
      }
      setVisible(true);
      setSize(435,465);
      setLocationRelativeTo(null);
   }
   
   protected JPanel getPanel1() {
      if (panel1 == null) {
         panel1 = new LevelOne(this); 
         panel1.addMouseListener(panel1);
         panel1.addMouseMotionListener(panel1);
         this.addKeyListener(panel1);
      }
      return panel1;
   }
   
   protected JPanel getPanel2() {
      if (panel2 == null) {
         panel2 = new LevelTwo(this); 
         panel2.addMouseListener(panel2);
         panel2.addMouseMotionListener(panel2);
         this.addKeyListener(panel2);
      }
      return panel2;
   }
   
   protected JPanel getPanel3() {
      if (panel3 == null) {
         panel3 = new LevelThree(this); 
         panel3.addMouseListener(panel3);
         panel3.addMouseMotionListener(panel3);
         this.addKeyListener(panel3);
      }
      return panel3;
   }
   
}

