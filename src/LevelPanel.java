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

//关卡界面
public class LevelPanel extends JPanel implements MouseListener, MouseMotionListener, Runnable, KeyListener{
	
	private int px;//人所在坐标
	private int py; 
	
	private int timer;//计时器
	private Thread thread;
	private boolean play;//是否继续
	private JFrame frame;
	private int level;//关卡层数
	private Random rand;
	private int direction;//上1下2左3右4
	private int score;//本分数
	private int[] scores = {-1, -1, -1};//所有关卡分数
	private int beanNum;//豆子数量
//	private boolean loseLevel;//是否失败
	
	private int[] grids_x = new int[20];//网格坐标
	private int[] grids_y = new int[20];
	private int[] mons_x = new int[3];//怪兽坐标
	private int[] mons_y = new int[3];
	private int[] mons_dir = new int [3];//怪兽方向
//	private int[] walls_x = new int[100];
//	private int[] walls_y = new int[100];
	
	private int[] floor_x = new int[100];//地面坐标
	private int[] floor_y = new int[100];
//	private int[][] kind = new int[20][20];//标识格子属性，人-1，豆子0，墙4，空白5，怪物1,2,3
	private int[][] kind;//标识格子属性，人-1，豆子0，墙4，空白5，怪物1,2,3

	//图片
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

	//初始化20*20地图网格
	public void initialGirds() {
		int start_x = 62, start_y = 80;
		for(int i = 0; i < 20; i++) {
			grids_x[i] = start_x + i*15;
			grids_y[i] = start_y + i*15;
		}
	}

	//初始化人的位置
	public void initialMan(Random rand) {
//		int tmp_x = rand.nextInt(20), tmp_y = rand.nextInt(20);
//		px = tmp_x;//生成0-19的随机整数,人的初始位置
//		py = tmp_y;
//		kind[tmp_y][tmp_x] = -1;
		kind[5][0] = -1;
		px = 0;
		py = 5;
	}

	//初始化怪物的随机位置
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
	
//	public void initialWall(Random rand) {
//		int num = 0;
//		while(num < 100) {
//			int tmp_x = rand.nextInt(20), tmp_y = rand.nextInt(20);
//			if(kind[tmp_y][tmp_x]!=0 && kind[tmp_y][tmp_x] <= 4) continue;
//			walls_x[num] = grids_x[tmp_x];
//			walls_y[num] = grids_y[tmp_y];
//			kind[tmp_y][tmp_x] = 4;
//			num++;
//		}
//	}

	//初始化地面
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

	//计算豆子的初始数量
	public int calcBeanNum() {
		int num = 0;
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++)
				if(kind[i][j]==0)num++;
		return num;
	}
	
	public LevelPanel(JFrame frame, int[][] kind, int level, int[] scores) {
		super();
		play = true;
//		loseLevel = false;
		this.frame = frame;
		this.level = level;
		this.scores = scores;
		rand = new Random();
		timer = 0;//初始化计时器
		direction = 4;//默认人的初始方向向右
		man = man_r;
		this.kind = kind;//加载关卡地图
		score = 0;
		//初始化地图
		initialGirds();
		initialMan(rand);
		initialMonsters(rand);
//		initialWall(rand);
		initialFloor(rand);
		beanNum = calcBeanNum();
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
		if(beanNum==0) gameWin();//豆子被吃完则关卡通过
//		if(score == 100) gameWin();
		timer++;
		g.drawImage(background, 0, 0, this);//加载背景图片
		String tmp_s = "Score: "+String.valueOf(score);//记录分数
		g.drawChars(tmp_s.toCharArray(), 0, tmp_s.length(), 190, 400);
		//人运动
		switch(direction) {
			case 1: man = man_u;if(canMoveMan(px, py, 1)) {kind[py][px]=5;py-=1;if(kind[py][px]>=1&&kind[py][px]<=3)/*碰到怪*/{g.drawImage(lose, 80, 0, this);gameLose();}if(kind[py][px]==0)/*吃到豆子*/ {score+=20;beanNum--;}kind[py][px]=-1;}break;
			case 2: man = man_d;if(canMoveMan(px, py, 2)) {kind[py][px]=5;py+=1;if(kind[py][px]>=1&&kind[py][px]<=3)/*碰到怪*/{g.drawImage(lose, 80, 0, this);gameLose();}if(kind[py][px]==0) /*吃到豆子*/{score+=20;beanNum--;}kind[py][px]=-1;}break;
			case 3: man = man_l;if(canMoveMan(px, py, 3)) {kind[py][px]=5;px-=1;if(kind[py][px]>=1&&kind[py][px]<=3)/*碰到怪*/{g.drawImage(lose, 80, 0, this);gameLose();}if(kind[py][px]==0) /*吃到豆子*/{score+=20;beanNum--;}kind[py][px]=-1;}break;
			case 4: man = man_r;if(canMoveMan(px, py, 4)) {kind[py][px]=5;px+=1;if(kind[py][px]>=1&&kind[py][px]<=3)/*碰到怪*/{g.drawImage(lose, 80, 0, this);gameLose();}if(kind[py][px]==0)/*吃到豆子*/ {score+=20;beanNum--;}kind[py][px]=-1;}break;
			default:break;
		}
		//怪兽运动
		for(int i=0;i<3;i++) {
			mons_dir[i] = 1+rand.nextInt(4);//1~4随机方向
			switch(mons_dir[i]) {
				case 1:if(canMoveMonster(mons_x[i], mons_y[i], 1)) {kind[mons_y[i]][mons_x[i]]=5;mons_y[i]-=1;if(kind[mons_y[i]][mons_x[i]]==-1)/*碰到人*/{g.drawImage(lose, 80, 0, this);gameLose();}if(kind[mons_y[i]][mons_x[i]]==0) /*吃到豆子*/{beanNum--;}kind[mons_y[i]][mons_x[i]]=i+1;}break;
				case 2:if(canMoveMonster(mons_x[i], mons_y[i], 2)) {kind[mons_y[i]][mons_x[i]]=5;mons_y[i]+=1;if(kind[mons_y[i]][mons_x[i]]==-1)/*碰到人*/{g.drawImage(lose, 80, 0, this);gameLose();}if(kind[mons_y[i]][mons_x[i]]==0)/*吃到豆子*/ {beanNum--;}kind[mons_y[i]][mons_x[i]]=i+1;}break;
				case 3:if(canMoveMonster(mons_x[i], mons_y[i], 3)) {kind[mons_y[i]][mons_x[i]]=5;mons_x[i]-=1;if(kind[mons_y[i]][mons_x[i]]==-1)/*碰到人*/{g.drawImage(lose, 80, 0, this);gameLose();}if(kind[mons_y[i]][mons_x[i]]==0) /*吃到豆子*/{beanNum--;}kind[mons_y[i]][mons_x[i]]=i+1;}break;
				case 4:if(canMoveMonster(mons_x[i], mons_y[i], 4)) {kind[mons_y[i]][mons_x[i]]=5;mons_x[i]+=1;if(kind[mons_y[i]][mons_x[i]]==-1)/*碰到人*/{g.drawImage(lose, 80, 0, this);gameLose();}if(kind[mons_y[i]][mons_x[i]]==0) /*吃到豆子*/{beanNum--;}kind[mons_y[i]][mons_x[i]]=i+1;}break;
				default:break;
			}
		}
//		//判断是否撞怪物
//		for(int i=0;i<3;i++) {
//			switch(monsAround(i)){ //怪物i在人周围哪个方向
//				case 1:if(direction==1 && mons_dir[i]== 2) {g.drawImage(lose, 80, 0, this);gameLose();}break;
//				case 2:if(direction==2 && mons_dir[i]== 1) {g.drawImage(lose, 80, 0, this);gameLose();}break;
//				case 3:if(direction==3 && mons_dir[i]== 4) {g.drawImage(lose, 80, 0, this);gameLose();}break;
//				case 4:if(direction==4 && mons_dir[i]== 3) {g.drawImage(lose, 80, 0, this);gameLose();}break;
//				default:break;
//			}
//		}
		
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
//		if(loseLevel){//关卡失败
//			g.drawImage(lose, 80, 0, this);gameLose();
//		}

	}

	//关卡通过
	public void gameWin() {
		play = false;
		scores[level-1] = score;//本关分数
	    thread.stop();
	    frame.dispose();
	    GameManager g;
	    if(level != 3)
	    	g = new GameManager(level+1, true, scores); //到失败界面
	    else 
	    	g = new GameManager(100, true, scores);//通关
		g.setVisible(true);
	}

	//关卡失败
	public void gameLose() {
		play = false;
		scores[level-1] = score;//本关分数
	    thread.stop();
	    frame.dispose();
		GameManager g = new GameManager(level, false, scores); //到失败界面
		g.setVisible(true);
	}

	//判断是否有怪兽在人的四周
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

	//判断在此方向上人是否可以继续走
	public boolean canMoveMan(int px, int py, int direction) {
		int x=px, y=py;
		switch(direction) {
		case 1:y-=1;break;
		case 2:y+=1;break;
		case 3:x-=1;break;
		case 4:x+=1;break;
		default:break;
		}
		if(x<0 || x>19 || y<0 || y>19)return false;//超出边界
		if(kind[y][x] !=  4) return true;//不能碰墙
		return false;
	}

	//判断在此方向上怪物是否可以继续走
	public boolean canMoveMonster(int px, int py, int direction) {
		int x=px, y=py;
		switch(direction) {
			case 1:y-=1;break;
			case 2:y+=1;break;
			case 3:x-=1;break;
			case 4:x+=1;break;
			default:break;
		}
		if(x<0 || x>19 || y<0 || y>19)return false;//超出边界
		if(kind[y][x] < 1 || kind[y][x] > 4) return true;//不能碰墙和怪
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
//		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
