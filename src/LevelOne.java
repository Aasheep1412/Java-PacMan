package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelOne extends JPanel implements MouseListener, MouseMotionListener, Runnable, KeyListener{
	
	private int px;
	private int py; 
	
	private int time;
	private Thread thread;
	private boolean play;
	private JFrame frame;
	private int level;
	private Random rand;
	private int direction;//上1下2左3右4
	private int score;
	
	private int[] grids_x = new int[20];//网格坐标
	private int[] grids_y = new int[20];
	private int[] mons_x = new int[3];//怪兽坐标
	private int[] mons_y = new int[3];
	private int[] mons_dir = new int [3];//怪兽方向
	private int[] walls_x = new int[100];
	private int[] walls_y = new int[100];
	private int[] floor_x = new int[100];
	private int[] floor_y = new int[100];
	private int[][] kind = new int[20][20];//标识格子属性，人-1，豆子0，墙4，空白5，怪物1,2,3
	
	private Image background = new ImageIcon("pictures/background.png").getImage();
	private Image bean = new ImageIcon("pictures/bean.png").getImage();
	private Image man_r = new ImageIcon("pictures/man_r.png").getImage();
	private Image man_l = new ImageIcon("pictures/man_l.png").getImage();
	private Image man_d = new ImageIcon("pictures/man_d.png").getImage();
	private Image man_u = new ImageIcon("pictures/man_u.png").getImage();
	private Image mons_1 = new ImageIcon("pictures/mons_1.png").getImage();
	private Image mons_2 = new ImageIcon("pictures/mons_2.png").getImage();
	private Image mons_3 = new ImageIcon("pictures/mons_3.png").getImage();
	private Image wall = new ImageIcon("pictures/wall.png").getImage();
	private Image lose = new ImageIcon("pictures/lose.png").getImage();
	private Image man;
	
	public void initialGirds() {
		int start_x = 62, start_y = 80;
		for(int i = 0; i < 20; i++) {
			grids_x[i] = start_x + i*15;
			grids_y[i] = start_y + i*15;
		}
		for(int i = 0; i< 20; i++)
			for(int j = 0; j< 20; j++)
				kind[i][i] = 0;	
	}
	
	public void initialMan(Random rand) {
		int tmp_x = rand.nextInt(20), tmp_y = rand.nextInt(20);
		px = tmp_x;//生成0-19的随机整数,人的初始位置
		py = tmp_y;
		kind[tmp_y][tmp_x] = -1;
	}
	
	public void initialMonsters(Random rand) {
		int num = 0;
		while(num < 3) {
			int tmp_x = rand.nextInt(20), tmp_y = rand.nextInt(20);
			if(kind[tmp_y][tmp_x] != 0) continue;
			mons_x[num] = tmp_x;
			mons_y[num] = tmp_y;
			if(num == 0)kind[tmp_y][tmp_x] = 1;
			else if(num == 1) kind[tmp_y][tmp_x] = 2;
			else if(num == 2) kind[tmp_y][tmp_x] = 3;
			num++;
		}
	}
	
	public void initialWall(Random rand) {
		int num = 0;
		while(num < 100) {
			int tmp_x = rand.nextInt(20), tmp_y = rand.nextInt(20);
			if(kind[tmp_y][tmp_x]!=0 && kind[tmp_y][tmp_x] <= 4) continue;
			walls_x[num] = grids_x[tmp_x];
			walls_y[num] = grids_y[tmp_y];
			kind[tmp_y][tmp_x] = 4;
			num++;
		}
	}
	
	public void initialFloor(Random rand) {
		int num = 0;
		while(num < 100) {
			int tmp_x = rand.nextInt(20), tmp_y = rand.nextInt(20);
			if(kind[tmp_y][tmp_x]!=0 && kind[tmp_y][tmp_x] <= 5) continue;
			floor_x[num] = grids_x[tmp_x];
			floor_y[num] = grids_y[tmp_y];
			kind[tmp_y][tmp_x] = 5;
			num++;
		}
	}
	
	public LevelOne(JFrame frame) {
		super();
		play = true;
		this.frame = frame;
		level = 1;
		rand = new Random();
		time = 0;
		direction = 4;
		man = man_r;
		score = 0;
		initialGirds();
		initialMan(rand);
		initialMonsters(rand);
		initialWall(rand);
		initialFloor(rand);
//		for(int i=0; i<20;i++) {
//			for(int j=0;j<20;j++)
//				System.out.print(kind[i][j]);
//			System.out.println();
//		}
		if (thread == null || !thread.isAlive())
		      thread = new Thread(this);
		      thread.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		time++;
		//人运动
		g.drawImage(background, 0, 0, this);
		String tmp_s = "Score: "+String.valueOf(score);
		g.drawChars(tmp_s.toCharArray(), 0, tmp_s.length(), 190, 400);
		switch(direction) {
			case 1: man = man_u;if(canMove(px, py, 1)) {kind[py][px]=5;py-=1;if(kind[py][px]==0) {score+=20;}kind[py][px]=-1;}break;
			case 2: man = man_d;if(canMove(px, py, 2)) {kind[py][px]=5;py+=1;if(kind[py][px]==0) {score+=20;}kind[py][px]=-1;}break;
			case 3: man = man_l;if(canMove(px, py, 3)) {kind[py][px]=5;px-=1;if(kind[py][px]==0) {score+=20;}kind[py][px]=-1;}break;
			case 4: man = man_r;if(canMove(px, py, 4)) {kind[py][px]=5;px+=1;if(kind[py][px]==0) {score+=20;}kind[py][px]=-1;}break;
			default:break;
		}
		//怪兽运动
		for(int i=0;i<3;i++) {
			mons_dir[i] = 1+rand.nextInt(4);//1~4
			switch(mons_dir[i]) {
				case 1:if(canMove(mons_x[i], mons_y[i], 1)) {kind[mons_y[i]][mons_x[i]]=5;mons_y[i]-=1;kind[mons_y[i]][mons_x[i]]=i+1;}break;
				case 2:if(canMove(mons_x[i], mons_y[i], 2)) {kind[mons_y[i]][mons_x[i]]=5;mons_y[i]+=1;kind[mons_y[i]][mons_x[i]]=i+1;}break;
				case 3:if(canMove(mons_x[i], mons_y[i], 3)) {kind[mons_y[i]][mons_x[i]]=5;mons_x[i]-=1;kind[mons_y[i]][mons_x[i]]=i+1;}break;
				case 4:if(canMove(mons_x[i], mons_y[i], 4)) {kind[mons_y[i]][mons_x[i]]=5;mons_x[i]+=1;kind[mons_y[i]][mons_x[i]]=i+1;}break;
				default:break;
			}
		}
		//判断是否撞怪物
		for(int i=0;i<3;i++) {
			switch(monsAround(i)){ //怪物i在人周围哪个方向
				case 1:if(direction==1 && mons_dir[i]== 2) {g.drawImage(lose, 80, 0, this);gameLose();}break;
				case 2:if(direction==2 && mons_dir[i]== 1) {g.drawImage(lose, 80, 0, this);gameLose();}break;
				case 3:if(direction==3 && mons_dir[i]== 4) {g.drawImage(lose, 80, 0, this);gameLose();}break;
				case 4:if(direction==4 && mons_dir[i]== 3) {g.drawImage(lose, 80, 0, this);gameLose();}break;
				default:break;
			}
		}
		
		//画图
		for(int i = 0; i< 20; i++)
			for(int j = 0; j < 20; j++) {
				Image tmp = null;
				switch(kind[j][i]) {
					case -1:tmp = man;break;
					case 0:tmp = bean;break;
					case 1:tmp = mons_1;break;
					case 2:tmp = mons_2;break;
					case 3:tmp = mons_3;break;
					case 4:tmp = wall;break;
					default:break;
				}
				g.drawImage(tmp, grids_x[i], grids_y[j], this);
			}

	}
	
	public void gameLose() {
		play = false;
	    thread.stop();
	    
//	    frame.dispose();
//		MenuFrame f = new MenuFrame(); //到主界面
//		f.setVisible(true);
	}
	
	public int monsAround(int i) {//怪物在人的那一边，上1下2左3右4,0不邻接
		if(Math.abs(px-mons_x[i])==1 && py==mons_y[i]) {
			if(px>mons_x[i]) return 3;
			return 4;
		}
		else if(Math.abs(py-mons_y[i])==1 && px==mons_x[i]) {
			if(py>mons_y[i]) return 1;
			return 2;
		}
		return 0;
	}
	
	public boolean canMove(int px, int py, int direction) {
		int x=px,y=py;
		switch(direction) {
		case 1:y-=1;break;
		case 2:y+=1;break;
		case 3:x-=1;break;
		case 4:x+=1;break;
		default:break;
		}
		if(x<0 || x>19 || y<0 || y>19)return false;
		if(kind[y][x] == 0 || kind[y][x] == 5) return true;
		return false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		px = e.getX();
//		py = e.getY();
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (play) {
	         try {
	            Thread.sleep(300);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         repaint(); 
	      }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP) {direction = 1;}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {direction = 2;}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {direction = 3;}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {direction = 4;}
		//调用 repaint() 函数，来重绘制界面
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
