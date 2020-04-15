package moduletest.judgelosetest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import game.GameManager;
import game.LevelPanel;
import game.roles.Monster;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * 判断怪是否撞人的测试脚本
 * @author LuoBingQian
 * @date 2020/3/27
 */
public class JudgeLoseMonsterTest {
    LevelPanel testObj;
    public int[] scores = {-1, -1, -1};
    public static int[][] kind;
    public Monster[] mons;
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
                {-1, 4, 4, 0, 4, 4, 0, 4, 0, 4, 4, 0, 4, 0, 4, 4, 0, 4, 4, 0},
                {1, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {-1, 4, 4, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 4, 0},
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
    public void judgeLoseMonsterUp() {
        boolean expected = true;
        //向上
        mons = new Monster[]{ new Monster(0,3,1,1) };
        System.out.println("man的横坐标为0"+"，纵坐标为2");
        System.out.println("monster1的横坐标为0，"+"纵坐标为3");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMonster(kind, mons,1);
        System.out.println("向上移动1后，monster1的横坐标为"+mons[0].getX()+"，纵坐标为"+mons[0].getY());
        assertEquals(expected, actual);
    }

    @Test
    public void judgeLoseMonsterDown() {
        boolean expected = true;
        //向下
        mons = new Monster[]{ new Monster(0,3,2,1) };
        System.out.println("man的横坐标为0"+"，纵坐标为4");
        System.out.println("monster1的横坐标为0，"+"纵坐标为3");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMonster(kind, mons,1);
        System.out.println("向下移动1后，monster1的横坐标为"+mons[0].getX()+"，纵坐标为"+mons[0].getY());
        assertEquals(expected, actual);
    }

    @Test
    public void judgeLoseMonsterLeft() {
        boolean expected = true;
        //向左
        mons = new Monster[]{ new Monster(2,3,3,1) };
        System.out.println("man的横坐标为1"+"，纵坐标为3");
        System.out.println("monster1的横坐标为2，"+"纵坐标为3");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMonster(kind, mons,1);
        System.out.println("向左移动1后，monster1的横坐标为"+mons[0].getX()+"，纵坐标为"+mons[0].getY());
        assertEquals(expected, actual);
    }

    @Test
    public void judgeLoseMonsterRight() {
        boolean expected = true;
        //向右
        mons = new Monster[]{ new Monster(0,3,4,1) };
        System.out.println("man的横坐标为1"+"，纵坐标为3");
        System.out.println("monster1的横坐标为0，"+"纵坐标为3");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMonster(kind, mons,1);
        System.out.println("向右移动1后，monster1的横坐标为"+mons[0].getX()+"，纵坐标为"+mons[0].getY());
        assertEquals(expected, actual);
    }

    @Test
    public void judgeLoseMonsterWrongDir() {
        System.out.println("输入不存在的方向0");
        boolean expected = false;
        //错误的方向
        mons = new Monster[]{ new Monster(0,3,0,1) };
        System.out.println("man的横坐标为1"+"，纵坐标为3");
        System.out.println("monster1的横坐标为0，"+"纵坐标为3");
        //false为没碰到怪物
        boolean actual = testObj.judgeLoseMonster(kind, mons,1);
        assertEquals(expected, actual);
    }

    @Test
    public void judgeLoseMonsterTwoMons() {
        boolean expected = true;
        mons = new Monster[]{ new Monster(2,3,4,1),
                              new Monster(0,3,4,2) };
        System.out.println("man的横坐标为1"+"，纵坐标为3");
        System.out.println("monster1的横坐标为2，"+"纵坐标为3");
        System.out.println("monster2的横坐标为0，"+"纵坐标为3");
        //true碰到怪物
        boolean actual = testObj.judgeLoseMonster(kind, mons,1);
        System.out.println("向右移动1后，monster1的横坐标为"+mons[0].getX()+"，纵坐标为"+mons[0].getY());
        System.out.println("向右移动1后，monster2的横坐标为"+mons[1].getX()+"，纵坐标为"+mons[1].getY());
        assertEquals(expected, actual);
    }

}