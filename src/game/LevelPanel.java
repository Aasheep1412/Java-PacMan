package game;

import game.roles.Man;
import game.roles.Monster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * 关卡界面
 * @author DengYuhan
 * @date 2020/3/28
 */
public class LevelPanel extends JPanel implements Runnable, KeyListener{
	public static final int LEVELONE = 1;
	public static final int LEVELTWO = 2;
	public static final int LEVELTHREE = 3;
	public static final int LEVELLOSE = 0;
	public static final int LEVELPASS = 100;

	public static final int GRIDSNUM = 20;
	public static final int MONSNUM = 3;
	public static final int SPEEDUPNUM = 5;
	public static final int FLOORNUM = 100;

	public static final int PASSSCORE = 500;

	public static final int STEPONE = 1;
	public static final int STEPTWO = 2;

	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	public static final int MAN = -1;
	public static final int BEAN = 0;
	public static final int MONSTER_1 = 1;
	public static final int MONSTER_2 = 2;
	public static final int MONSTER_3 = 3;
	public static final int WALL = 4;
	public static final int FLOOR = 5;
	/**加速丸*/
	public static final int SPEEDUP = 6;

	/**人对象*/
	private Man man;
	/**怪兽对象*/
	private Monster[] mons = new Monster[3];
	/**计时器*/
	private int timer;
	private Thread thread;
	/**是否继续*/
	private boolean play;
	private JFrame frame;
	/**关卡层数*/
	private int level;
	private Random rand;
	/**本关分数*/
	private int score;
	/**所有关卡分数*/
	private int[] scores = {-1, -1, -1};
	/**豆子数量*/
	private int beanNum;

	/**网格坐标*/
	private int[] gridsX = new int[20];
	private int[] gridsY = new int[20];
	/**地面坐标*/
	private int[] floorx = new int[100];
	private int[] floorY = new int[100];
	/**标识格子属性，人-1，豆子0，墙4，空白5，怪物1,2,3*/
	private int[][] kind;

	/**图片*/
	private Image background = new ImageIcon("pictures/background.png").getImage();
	private Image bean = new ImageIcon("pictures/bean.png").getImage();
	private Image wall = new ImageIcon("pictures/wall.png").getImage();
	private Image speedup = new ImageIcon("pictures/speedup.png").getImage();

	/**初始化20*20地图网格*/
	public void initialGirds() {
		int startX = 62, startY = 80;
		for(int i = 0; i < GRIDSNUM; i++) {
			gridsX[i] = startX + i*15;
			gridsY[i] = startY + i*15;
		}
	}

	/**初始化人的位置*/
	public void initialMan(Random rand) {
		man = new Man(0, 5, 1,4);
		kind[5][0] = MAN;
	}

	/**初始化怪物*/
	public void initialMonsters(Random rand) {
		int num = 0;
		while(num < MONSNUM) {
			int tmpX = rand.nextInt(20), tmpY = rand.nextInt(20), tmpDir = rand.nextInt(4)+1;
			if(kind[tmpY][tmpX] != BEAN) {
                continue;
            }
			mons[num] = new Monster(tmpX, tmpY, tmpDir, num+1);
			if(num == 0) {
                kind[tmpY][tmpX] = MONSTER_1;
            } else if(num == 1) {
                kind[tmpY][tmpX] = MONSTER_2;
            } else if(num == 2) {
                kind[tmpY][tmpX] = MONSTER_3;
            }
			num++;
		}
	}

	/**初始化地面*/
	public void initialFloor(Random rand) {
		int num = 0;
		while(num < FLOORNUM) {
			int tmpX = rand.nextInt(20), tmpY = rand.nextInt(20);
			if(kind[tmpY][tmpX]!=BEAN && kind[tmpY][tmpX] <= FLOOR) {
                continue;
            }
			floorx[num] = gridsX[tmpX];
			floorY[num] = gridsY[tmpY];
			kind[tmpY][tmpX] = FLOOR;
			num++;
		}
	}

	/**初始化加速丸*/
	public void initialSpeedUp(Random rand){
		int num = 0;
		while(num < SPEEDUPNUM) {
			int tmpX = rand.nextInt(20), tmpY = rand.nextInt(20);
			if(kind[tmpY][tmpX]!=BEAN) {
                continue;
            }
			kind[tmpY][tmpX] = SPEEDUP;
			num++;
		}
	}

	/**计算豆子的数量*/
	public int calcBeanNum() {
		int num = 0;
		for(int i=0;i<GRIDSNUM;i++) {
            for(int j=0;j<GRIDSNUM;j++) {
                if (kind[i][j] == BEAN) {
                    num++;
                }
            }
        }
		return num;
	}
	
	public LevelPanel(JFrame frame, int[][] kind, int level, int[] scores) {
		super();
		play = true;
		this.frame = frame;
		this.level = level;
		this.scores = scores;
		rand = new Random();
		//初始化计时器
		timer = 0;
		//加载关卡地图
		this.kind = kind;
		//初始化分数
		score = 0;
		//初始化地图
		initialGirds();
		initialMan(rand);
		initialMonsters(rand);
		initialFloor(rand);
		initialSpeedUp(rand);
		beanNum = calcBeanNum();

		if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
        }
		      thread.start();
	}

	/**判断人运动是否吃豆子对应的分数变化*/
	public int eatBean(int score, int y, int x){
		if(kind[y][x]==BEAN){
			score+=20;
			beanNum--;
		}
		return score;
	}

	/**人是否碰怪死亡*/
	public boolean judgeLoseMan(int[][] kind, Man man){
		int px = man.getX(), py = man.getY(), canMoveStep = man.canMove(kind);
		switch(man.getDirection()) {
			case UP: if(canMoveStep!=-1) {
				kind[py][px]=FLOOR;man.move(canMoveStep);
				for(int i=0;i<canMoveStep;i++){
					//碰到怪
					if(kind[py-i-1][px]>=MONSTER_1&&kind[py-i-1][px]<=MONSTER_3){return true;}
					if(kind[py-i-1][px]==SPEEDUP){man.setStep(2);}
					score = eatBean(score, py-i-1, px);
					kind[py-i-1][px]=FLOOR;
				}
				py-=canMoveStep;kind[py][px]=MAN;
			}break;
			case DOWN: if(canMoveStep!=-1) {
				kind[py][px]=FLOOR;man.move(canMoveStep);
				for(int i=0;i<canMoveStep;i++){
					//碰到怪
					if(kind[py+i+1][px]>=MONSTER_1&&kind[py+i+1][px]<=MONSTER_3){return true;}
					if(kind[py+i+1][px]==SPEEDUP){man.setStep(2);}
					score = eatBean(score, py+i+1, px);
					kind[py+i+1][px]=FLOOR;
				}
				py+=canMoveStep;;kind[py][px]=MAN;
			}break;
			case LEFT: if(canMoveStep!=-1) {
				kind[py][px]=FLOOR;man.move(canMoveStep);
				for(int i=0;i<canMoveStep;i++){
					//碰到怪
					if(kind[py][px-i-1]>=MONSTER_1&&kind[py][px-i-1]<=MONSTER_3){return true;}
					if(kind[py][px-i-1]==SPEEDUP){man.setStep(2);}
					score = eatBean(score, py, px-i-1);
					kind[py][px-i-1]=FLOOR;
				}
				px-=canMoveStep;;kind[py][px]=MAN;
			}break;
			case RIGHT: if(canMoveStep!=-1) {
				kind[py][px]=FLOOR;man.move(canMoveStep);
				for(int i=0;i<canMoveStep;i++){
					//碰到怪
					if(kind[py][px+i+1]>=MONSTER_1&&kind[py][px+i+1]<=MONSTER_3){return true;}
					if(kind[py][px+i+1]==SPEEDUP){man.setStep(2);}
					score = eatBean(score, py, px+i+1);
					kind[py][px+i+1]=FLOOR;
				}
				px+=canMoveStep;kind[py][px]=MAN;
			}break;
			default:break;
		}
		return false;
	}

	/**怪是否碰人使人死亡*/
	public boolean judgeLoseMonster(int[][] kind, Monster[] mons){
		int px, py;
		for(int i=0;i<MONSNUM;i++) {
			px = mons[i].getX();
			py = mons[i].getY();
			int dir = 1+rand.nextInt(4);
			//1~4随机方向
			mons[i].setDirection(dir);
			mons[i].setMonster(px, py, dir);
			switch(dir) {
				case UP:if(mons[i].canMove(kind)) {
					kind[py][px]=FLOOR;mons[i].move(dir);py-=1;
					//碰到人
					if(kind[py][px]==MAN){return true;}
					//吃到豆子
					if(kind[py][px]==BEAN){beanNum--;}kind[py][px]=i+1;}break;
				case DOWN:if(mons[i].canMove(kind)) {
					kind[py][px]=FLOOR;mons[i].move(dir);py+=1;
					//碰到人
					if(kind[py][px]==MAN){return true;}
					//吃到豆子
					if(kind[py][px]==BEAN) {beanNum--;}kind[py][px]=i+1;}break;
				case LEFT:if(mons[i].canMove(kind)) {
					kind[py][px]=FLOOR;mons[i].move(dir);px-=1;
					//碰到人
					if(kind[py][px]==MAN){return true;}
					//吃到豆子
					if(kind[py][px]==BEAN) {beanNum--;}kind[py][px]=i+1;}break;
				case RIGHT:if(mons[i].canMove(kind)) {
					kind[py][px]=FLOOR;mons[i].move(dir);px+=1;
					//碰到人
					if(kind[py][px]==MAN){return true;}
					//吃到豆子
					if(kind[py][px]==BEAN){beanNum--;}kind[py][px]=i+1;}break;
				default:break;
			}
		}
		return false;
	}

	/**测试用*/
	public  boolean judgeLoseMonster(int[][] kind, Monster mon){
		int px, py,dir;
		px = mon.getX();
		py = mon.getY();
		dir=mon.getDirection();
		switch(dir) {
			case UP:if(mon.canMove(kind)) {kind[py][px]=FLOOR;mon.move(dir);py-=1;if(kind[py][px]==MAN){return true;}if(kind[py][px]==BEAN){beanNum--;}kind[py][px]=mon.getIndex();}break;
			case DOWN:if(mon.canMove(kind)) {kind[py][px]=FLOOR;mon.move(dir);py+=1;if(kind[py][px]==MAN){return true;}if(kind[py][px]==BEAN){beanNum--;}kind[py][px]=mon.getIndex();}break;
			case LEFT:if(mon.canMove(kind)) {kind[py][px]=FLOOR;mon.move(dir);px-=1;if(kind[py][px]==MAN){return true;}if(kind[py][px]==BEAN) {beanNum--;}kind[py][px]=mon.getIndex();}break;
			case RIGHT:if(mon.canMove(kind)) {kind[py][px]=FLOOR;mon.move(dir);px+=1;if(kind[py][px]==MAN){return true;}if(kind[py][px]==BEAN) {beanNum--;}kind[py][px]=mon.getIndex();}break;
			default:break;
		}
		return false;
	}

	/**测试用*/
	public boolean judgeLoseMan(int[][] kind, Man man, int test){
		//人运动
		int px = man.getX(), py = man.getY(),canMoveStep = man.canMove(kind);
		switch(man.getDirection()) {
			case UP: if(canMoveStep!=-1) {
				kind[py][px]=FLOOR;man.move(canMoveStep);
				if(kind[py-canMoveStep][px]>=MONSTER_1&&kind[py-canMoveStep][px]<=MONSTER_3){return true;}
				kind[py-canMoveStep][px]=FLOOR;
				py-=canMoveStep;;kind[py][px]=MAN;
			}break;
			case DOWN: if(canMoveStep!=-1) {
				kind[py][px]=FLOOR;man.move(canMoveStep);
				if(kind[py+canMoveStep][px]>=MONSTER_1&&kind[py+canMoveStep][px]<=MONSTER_3){return true;}
				kind[py+canMoveStep][px]=FLOOR;
				py+=canMoveStep;kind[py][px]=MAN;
			}break;
			case LEFT: if(canMoveStep!=-1) {
				kind[py][px]=FLOOR;man.move(canMoveStep);
				if(kind[py][px-canMoveStep]>=MONSTER_1&&kind[py][px-canMoveStep]<=MONSTER_3){return true;}
				kind[py][px-canMoveStep]=FLOOR;
				px-=canMoveStep;kind[py][px]=MAN;
			}break;
			case RIGHT: if(canMoveStep!=-1) {
				kind[py][px]=FLOOR;man.move(canMoveStep);
				if(kind[py][px+canMoveStep]>=MONSTER_1&&kind[py][px+canMoveStep]<=MONSTER_3){return true;}
				kind[py][px+canMoveStep]=FLOOR;
				px+=canMoveStep;kind[py][px]=MAN;
			}break;
			default:break;
		}
		return false;
	}

	/**测试用*/
	public boolean judgeLoseMonster(int[][] kind, Monster[] mons, int test){
		//怪兽运动
		int px, py, dir;
		for(int i=0;i<mons.length;i++) {
			px = mons[i].getX();
			py = mons[i].getY();
			dir = mons[i].getDirection();
			switch(dir) {
				case UP:if(mons[i].canMove(kind)) {kind[py][px]=FLOOR;mons[i].move(dir);py-=1;if(kind[py][px]==MAN){return true;}kind[py][px]=i+1;}break;
				case DOWN:if(mons[i].canMove(kind)) {kind[py][px]=FLOOR;mons[i].move(dir);py+=1;if(kind[py][px]==MAN){return true;}kind[py][px]=i+1;}break;
				case LEFT:if(mons[i].canMove(kind)) {kind[py][px]=FLOOR;mons[i].move(dir);px-=1;if(kind[py][px]==MAN){return true;}kind[py][px]=i+1;}break;
				case RIGHT:if(mons[i].canMove(kind)) {kind[py][px]=FLOOR;mons[i].move(dir);px+=1;if(kind[py][px]==MAN){return true;}kind[py][px]=i+1;}break;
				default:break;
			}
		}
		return false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//500分则关卡通过
		if(score>=PASSSCORE) {
            gameWin();
        }
		//计时
		timer++;
		//加载背景图片
		g.drawImage(background, 0, 0, this);
		//显示分数
		String tmpS = "Score: "+score;
		g.drawChars(tmpS.toCharArray(), 0, tmpS.length(), 190, 400);

		//判断游戏是否失败
		if(judgeLoseMan(kind, man) || judgeLoseMonster(kind, mons)) {
            gameLose();
        }

		//画图
		for(int i = 0; i< GRIDSNUM; i++) {
            for(int j = 0; j < GRIDSNUM; j++) {
                Image tmp = null;
                switch(kind[j][i]) {
                    case MAN:tmp = man.getImage();break;
                    case BEAN:tmp = bean;break;
                    case MONSTER_1:tmp = mons[0].getImage();break;
                    case MONSTER_2:tmp = mons[1].getImage();break;
                    case MONSTER_3:tmp = mons[2].getImage();break;
                    case WALL:tmp = wall;break;
                    case SPEEDUP:tmp = speedup;break;
                    default:break;
                }
                g.drawImage(tmp, gridsX[i], gridsY[j], this);
            }
        }
	}

	/**关卡通过*/
	public void gameWin() {
		play = false;
		//本关分数
		scores[level-1] = score;
	    thread.stop();
	    frame.dispose();
	    GameManager g;
		//非关卡三则到失败界面
	    if(level != LEVELTHREE) {
            g = new GameManager(level+1, true, scores);
        }
	    //通关
	    else {
            g = new GameManager(100, true, scores);
        }
		g.setVisible(true);
	}

	/**关卡失败*/
	public void gameLose() {
		play = false;
		//本关分数
		scores[level-1] = score;
	    thread.stop();
	    frame.dispose();
		//到失败界面
		GameManager g = new GameManager(level, false, scores);
		g.setVisible(true);
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
		if(e.getKeyCode()==KeyEvent.VK_UP) {man.setDirection(UP);}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {man.setDirection(DOWN);}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {man.setDirection(LEFT);}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {man.setDirection(RIGHT);}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public int[][] getKind() {
		return kind;
	}

	public void setKind(int y, int x, int num){
		kind[y][x] = num;
	}

	public void setScore(int score){
		this.score = score;
	}

	public int[] getScores(){
		return this.scores;
	}

}
