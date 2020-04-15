package moduletest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import game.GameManager;
import game.LevelPanel;
import game.roles.Monster;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

/**
 * 测试用例002-UT-11 ~ 002-UT-20
 * 测试怪物的移动
 * @author LeiZhou
 * @date 2020/3/27
 */
public class MonsterTest {
	
	static int[][] map= {{0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0},
				  {0,4,4,0,4,4,0,4,0,4,4,0,4,0,4,4,0,4,4,0},
				  {0,4,4,0,4,4,0,4,0,4,4,0,4,0,4,4,0,4,4,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,4,4,4,0,0,0,4,4,4,4,4,4,0,0,0,4,4,4,0},
				  {0,0,0,0,0,4,0,0,0,4,4,0,0,0,4,0,0,0,0,0},
				  {4,4,4,4,0,4,0,0,0,4,4,0,0,0,4,0,4,4,4,4},
				  {0,0,0,4,0,4,4,4,0,0,0,0,4,4,4,0,4,0,0,0},
				  {0,4,4,4,0,4,0,0,0,0,0,0,0,0,4,0,4,4,4,0},
				  {0,0,0,0,0,4,0,4,4,0,0,4,4,0,4,0,0,0,0,0},
				  {0,4,4,4,0,0,0,4,0,0,0,0,4,0,0,0,4,4,4,0},
				  {0,0,0,4,0,0,0,4,4,4,4,4,4,0,0,0,4,0,0,0},
				  {4,4,4,4,0,4,0,0,0,0,0,0,0,0,4,0,4,4,4,4},
				  {0,0,0,0,0,4,0,4,4,4,4,4,4,0,4,0,0,0,0,0},
				  {0,4,4,0,0,4,4,4,4,4,4,4,4,4,4,0,0,4,4,0},
				  {0,4,4,4,0,0,0,4,4,4,4,4,4,0,0,0,4,4,4,0},
				  {0,4,4,4,4,0,0,0,0,0,0,0,0,0,0,4,4,4,4,0},
				  {0,0,4,4,4,4,0,0,0,4,4,0,0,0,4,4,4,4,0,0},
				  {4,0,0,0,4,4,0,0,4,4,4,4,0,0,4,4,4,0,0,4},
				  {4,4,0,0,0,0,0,4,4,4,4,4,4,0,0,0,0,0,4,4}};
	static Monster[] mons=new Monster[3];
	static int[] expectedX=new int[3];
	static int[] expectedY=new int[3];
	static int[] actualX=new int[3];
	static int[] actualY=new int[3];
	public int[] scores = {-1, -1, -1};
	public GameManager g = new GameManager(1, true, scores);
	public LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
	
	@Before
	/**初始化怪物位置,设定上初始方向都是右边*/
	public  void ini(){
		mons[0]=new Monster(9,8,4,1);
		mons[1]=new Monster(15,0,4,2);
		mons[2]=new Monster(5,0,4,3);
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			map[mons[i].getX()][mons[i].getY()]=i+1;
		}
	}
	
	@After
	public void allTested() {
		System.out.println("一个测试已经完成");
	}

	
	/**该函数配合testMove进行移动测试*/
	public static Monster helpMove(int i,Monster mon) {
	 mon.move(i);
	 return mon;
	}
	
	/**用例002-UT-022*/
	@Test
	public void testUp()
	{
		Monster mon=new Monster(8,9,1,1);
		int expectedY=8,expectedX=8;
		mon=helpMove(1,mon);
		int actualY=mon.getY(),actualX=mon.getX();
		  assertEquals("打印这个说明upy坐标有问题",expectedY, actualY);
		  assertEquals("打印这个说明upx坐标有问题",expectedX, actualX);	
	}
	
	@Test
	public void testDown()
	{
		Monster mon=new Monster(8,9,1,1);
		int expectedY=10,expectedX=8;
		mon=helpMove(2,mon);
		int actualY=mon.getY(),actualX=mon.getX();
		  assertEquals("打印这个说明downy坐标有问题",expectedY, actualY);
		  assertEquals("打印这个说明downx坐标有问题",expectedX, actualX);	
	}
	
	@Test
	public void testLeft()
	{
		Monster mon=new Monster(8,9,1,1);
		int expectedY=9,expectedX=7;
		mon=helpMove(3,mon);
		int actualY=mon.getY(),actualX=mon.getX();
		  assertEquals("打印这个说明left移动y坐标有问题",expectedY, actualY);
		  assertEquals("打印这个说明leftx坐标有问题",expectedX, actualX);	
	}
	
	@Test
	public void testRight()
	{
		Monster mon=new Monster(8,9,1,1);
		int expectedY=9,expectedX=9;
		mon=helpMove(4,mon);
		int actualY=mon.getY(),actualX=mon.getX();
		  assertEquals("打印这个说明righty坐标有问题",expectedY, actualY);
		  assertEquals("打印这个说明rightx坐标有问题",expectedX, actualX);	
	}

	/**用例002-UT-021
	下面的模块用于测试贴图是否正确*/
	@Test
	public void testImage() {
		Monster mon = new Monster(0, 5, 4,1);
		Image expected = new ImageIcon("pictures/mons_1.png").getImage();
		Image actual = mon.getImage();
		assertEquals("打印这个说明贴图的哈希值不一致，有问题",expected, actual);
	}
	
	
	/**下面的模块用于测试judgeLoseMonster函数的判定是否有效，由于原函数存在随机数，所以另设计judgeLoseMonsterForTest
	除将rand得到的数据替换为输入变量参数外，无其他修改
	地图格子属性，人-1，豆子0，墙4，floor5，怪物1,2,3
	用例002-UT-023*/
	@Test
	public void testBean()
	{
		//设置怪物们前进的方向上的第一个格子为bean
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			map[mons[i].getY()][mons[i].getX()+1]=0;
			expectedX[i]=mons[i].getX()+1;
			expectedY[i]=mons[i].getY();
			panel.judgeLoseMonster(map, mons[i]);
		}
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			actualX[i]=mons[i].getX();
			actualY[i]=mons[i].getY();
			assertEquals("打印这个说明bean x坐标有问题",expectedX[i], actualX[i]);
			assertEquals("打印这个说明bean y坐标有问题",expectedY[i], actualY[i]);
		}
	}
	
	/**用例002-UT-028*/
	@Test
	public void testMan()
	{
		//设置怪物们前进的方向上的第一个格子为man
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			map[mons[i].getY()][mons[i].getX()+1]=-1;
			expectedX[i]=mons[i].getX()+1;
			expectedY[i]=mons[i].getY();
			panel.judgeLoseMonster(map, mons[i]);
		}
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			actualX[i]=mons[i].getX();
			actualY[i]=mons[i].getY();
			assertEquals("打印这个说明mon x坐标有问题"+i,expectedX[i], actualX[i]);
			assertEquals("打印这个说明mon y坐标有问题"+i,expectedY[i], actualY[i]);
		}
	}
	
	/**用例002-UT-024*/
	@Test
	public void testFloor()
	{
		//设置怪物们前进的方向上的第一个格子为Floor，方向向左
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			mons[i].setDirection(3);
			map[mons[i].getY()][mons[i].getX()-1]=5;
			expectedX[i]=mons[i].getX()-1;
			expectedY[i]=mons[i].getY();
			panel.judgeLoseMonster(map, mons[i]);
		}
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			actualX[i]=mons[i].getX();
			actualY[i]=mons[i].getY();
			assertEquals("打印这个说明floor x坐标有问题",expectedX[i], actualX[i]);
			assertEquals("打印这个说明floor y坐标有问题",expectedY[i], actualY[i]);
		}
	}
	
	/**用例002-UT-027*/
	@Test
	public void testWall()
	{
		//设置怪物们前进的方向上的第一个格子为wall,方向向上
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			mons[i].setY(8); 
			mons[i].setDirection(1);
			map[mons[i].getY()-1][mons[i].getX()]=4;
			expectedX[i]=mons[i].getX();
			expectedY[i]=mons[i].getY();
			panel.judgeLoseMonster(map, mons[i]);
		}
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			actualX[i]=mons[i].getX();
			actualY[i]=mons[i].getY();
			assertEquals("打印这个说明x坐标有问题",expectedX[i], actualX[i]);	
			assertEquals("打印这个说明y坐标有问题",expectedY[i], actualY[i]);
		}
	}
	
	
	/**用例002-UT-025*/
	@Test
	public void testMonster()
	{
		//设置怪物们前进的方向上的第一个格子为monster，方向向下
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			mons[i].setY(8); 
			mons[i].setDirection(2);
			map[mons[i].getY()+1][mons[i].getX()]=1;
			expectedX[i]=mons[i].getX();
			expectedY[i]=mons[i].getY();
			panel.judgeLoseMonster(map, mons[i]);
		}
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			actualX[i]=mons[i].getX();
			actualY[i]=mons[i].getY();
			assertEquals("打印这个说明x坐标有问题",expectedX[i], actualX[i]);	
			assertEquals("打印这个说明y坐标有问题",expectedY[i], actualY[i]);
		}
	}
	
	/**用例002-UT-026*/
	@Test
	public void testSpeedUp()
	{
		//设置怪物们前进的方向上的第一个格子为加速丸
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			map[mons[i].getY()][mons[i].getX()+1]=6;
			expectedX[i]=mons[i].getX();
			expectedY[i]=mons[i].getY();
			panel.judgeLoseMonster(map, mons[i]);
		}
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			actualX[i]=mons[i].getX();
			actualY[i]=mons[i].getY();
			assertEquals("打印这个说明x坐标有问题",expectedX[i], actualX[i]);	
			assertEquals("打印这个说明y坐标有问题",expectedY[i], actualY[i]);
		}
	}
	
	/**用例002-UT-029*/
	@Test
	public void testOverride()
	{
		//设置怪物们右边超出边界
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			mons[i].setX(19);
			expectedX[i]=mons[i].getX();
			expectedY[i]=mons[i].getY();
			panel.judgeLoseMonster(map, mons[i]);
		}
		for(int i=0;i<LevelPanel.MONSNUM;i++)
		{
			actualX[i]=mons[i].getX();
			actualY[i]=mons[i].getY();
			assertEquals("打印这个说明x坐标有问题",expectedX[i], actualX[i]);	
			assertEquals("打印这个说明y坐标有问题",expectedY[i], actualY[i]);
		}
	}
	
	/**用例002-UT-030*/
	@Test
	public void testBehindAgrid()
	{
		//设置怪物相差一个格子且方向相向
		mons[0].setY(9);
		mons[0].setX(8);
		mons[0].setDirection(4);
		mons[1].setY(9);
		mons[1].setX(10);
		mons[1].setDirection(3);
		map[9][8] = 1;
		map[9][9] = 0;
		map[9][10] = 2;
		expectedX[0]=mons[0].getX()+1;
		expectedY[0]=mons[0].getY();
		expectedX[1]=mons[1].getX();
		expectedY[1]=mons[1].getY();
		for(int i=0;i<LevelPanel.MONSNUM-1;i++)
		{
			panel.judgeLoseMonster(map, mons[i]);
			actualX[i]=mons[i].getX();
			actualY[i]=mons[i].getY();
			assertEquals("打印这个说明差一个的情况下x坐标有问题",expectedX[i], actualX[i]);		
			assertEquals("打印这个说明y坐标有问题",expectedY[i], actualY[i]);
		}
	}
}