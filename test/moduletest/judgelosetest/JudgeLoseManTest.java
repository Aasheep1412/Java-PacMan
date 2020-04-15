package moduletest.judgelosetest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import game.GameManager;
import game.LevelPanel;
import game.roles.Man;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * 判断人是否撞怪的测试脚本
 * @author LuoBingQian
 * @date 2020/3/27
 */
public class JudgeLoseManTest {
    LevelPanel testObj;
    public int[] scores = {-1, -1, -1};
    public static int[][] kind;
    public GameManager g = new GameManager(1, true, scores);

    @Before
    public void setUp() throws Exception {
        testObj = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
    }

    @After
    public void tearDown() throws Exception {
        testObj = null;
    }

    @BeforeClass
    public static void prepareEnvironment() throws Exception {
        kind = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 4, 0, 4, 4, 0, 4, 0, 4, 4, 0, 4, 0, 4, 4, 0, 4, 4, 0},
                {1, 4, 4, 0, 4, 4, 0, 4, 0, 4, 4, 0, 4, 0, 4, 4, 0, 4, 4, 0},
                {-1, 2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 4, 4, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 4, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0},
                {4, 4, 4, 4, 0, 4, 0, 0, 0, 4, 4, 0, 0, 0, 4, 0, 4, 4, 4, 4},
                {0, 0, 0, 4, 0, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 0, 4, 0, 0, 0},
                {0, 4, 4, 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 4, 4, 4, 0},
                {0, 0, 0, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 0, 0, 0},
                {0, 4, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 4, 4, 4, 0},
                {0, 0, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 0, 0, 0},
                {4, 4, 4, 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 4, 4, 4, 4},
                {0, 0, 0, 0, 0, 4, 0, 4, 4, 4, 4, 4, 4, 0, 4, 0, 0, 0, 0, 0},
                {0, 4, 4, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 0},
                {0, 4, 4, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 4, 0},
                {0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0},
                {0, 0, 4, 4, 4, 4, 0, 0, 0, 4, 4, 0, 0, 0, 4, 4, 4, 4, 0, 0},
                {4, 0, 0, 0, 4, 4, 0, 0, 4, 4, 4, 4, 0, 0, 4, 4, 4, 0, 0, 4},
                {4, 4, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 4, 4}};
    }

    @Test
    public void testJudgeLoseManUp() {
        boolean expected = true;
        //上
        Man man = new Man(0,3,1,1);
        System.out.println("man的横坐标为0"+"，纵坐标为3");
        System.out.println("monster1的横坐标为0，"+"纵坐标为2");
        System.out.println("monster2的横坐标为1，"+"纵坐标为3");
        System.out.println("monster3的横坐标为0，"+"纵坐标为4");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMan(kind, man,1);
        System.out.println("向上移动1后，man的横坐标为"+man.getX()+"，纵坐标为"+man.getY());
        assertEquals(expected, actual);
    }

    @Test
    public void testJudgeLoseManDown() {
        boolean expected = true;
        //下
        Man man = new Man(0,3,1,2);
        System.out.println("man的横坐标为0，"+"纵坐标为3");
        System.out.println("monster1的横坐标为0，"+"纵坐标为2");
        System.out.println("monster2的横坐标为1，"+"纵坐标为3");
        System.out.println("monster3的横坐标为0，"+"纵坐标为4");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMan(kind, man,1);
        System.out.println("向下移动1后，man的横坐标为"+man.getX()+"，纵坐标为"+man.getY());
        assertEquals(expected, actual);
    }

    @Test
    public void testJudgeLoseManLeft() {
        boolean expected = true;
        //左
        Man man = new Man(2,3,1,3);
        System.out.println("man的横坐标为2，"+"纵坐标为3");
        System.out.println("monster1的横坐标为0，"+"纵坐标为2");
        System.out.println("monster2的横坐标为1，"+"纵坐标为3");
        System.out.println("monster3的横坐标为0，"+"纵坐标为4");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMan(kind, man,1);
        System.out.println("向左移动1后，man的横坐标为"+man.getX()+"，纵坐标为"+man.getY());
        assertEquals(expected, actual);
    }

    @Test
    public void testJudgeLoseManRight() {
        boolean expected = true;
        //右
        Man man = new Man(0,3,1,4);
        System.out.println("man的横坐标为0，"+"纵坐标为3");
        System.out.println("monster1的横坐标为0，"+"纵坐标为2");
        System.out.println("monster2的横坐标为1，"+"纵坐标为3");
        System.out.println("monster3的横坐标为0，"+"纵坐标为4");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMan(kind, man,1);
        System.out.println("向右移动1后，man的横坐标为"+man.getX()+"，纵坐标为"+man.getY());
        assertEquals(expected, actual);
    }

    @Test
    public void testJudgeLoseManWrongDir() {
        System.out.println("输入不存在的方向0");
        boolean expected = false;
        //错误的方向
        Man man = new Man(0,3,1,0);
        System.out.println("man的横坐标为0"+"，纵坐标为3");
        System.out.println("monster1的横坐标为0，"+"纵坐标为2");
        System.out.println("monster2的横坐标为1，"+"纵坐标为3");
        System.out.println("monster3的横坐标为0，"+"纵坐标为4");
        //false为没碰到怪物
        boolean actual = testObj.judgeLoseMan(kind, man,1);
        assertEquals(expected, actual);
    }

}