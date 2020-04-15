package moduletest;

import game.roles.Monster;
import org.junit.Test;
import game.GameManager;
import game.LevelPanel;
import game.roles.Man;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

/**
 * 测试用例002-UT-1 ~ 002-UT-10
 * 测试人的移动
 * @author XiongShiyuan
 * @date 2020/3/27
 */
public class ManTest {

    public int[] scores = {-1, -1, -1};
    public GameManager g = new GameManager(1, true, scores);
    public LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
    private KeyEvent e;

    @Test
    public void testMove1() {
        Man man = new Man(0, 5, 1, 4);
        panel.setKind(5,1,5);
        int expectedX = 1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove2() {
        Man man = new Man(0, 5, 1, 4);
        panel.setKind(5,1,4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove3() {
        Man man = new Man(0, 5, 1, 4);
        panel.setKind(5,1,3);
        boolean expectedX = true;
        boolean actualX = panel.judgeLoseMan(panel.getKind(),man);
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove4() {
        Man man = new Man(0, 5, 1, 4);
        panel.setKind(5,1,6);
        int expectedX = 2;
        panel.judgeLoseMan(panel.getKind(),man);
        int actualX = (man.getStep());
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove5() {
        Man man = new Man(0, 0, 1, 3);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove6() {
        Man man = new Man(0, 5, 1, 4);
        panel.setKind(5,1,0);
        int expectedX = 1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove7() {
        Man man = new Man(0, 5, 2, 4);
        panel.setKind(5,1,5);
        panel.setKind(5,2,4);
        int expectedX = 1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove8() {
        Man man = new Man(0, 5, 2, 4);
        panel.setKind(5,1,5);
        panel.setKind(5,2,3);
        boolean expectedX = true;
        boolean actualX = panel.judgeLoseMan(panel.getKind(),man);
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove9() {
        Man man = new Man(0, 5, 2, 4);
        panel.setKind(5,1,3);
        panel.setKind(5,2,5);
        boolean expectedX = true;
        boolean actualX = panel.judgeLoseMan(panel.getKind(),man);
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove10() {
        Man man = new Man(0, 5, 2, 4);
        panel.setKind(5,1,5);
        panel.setKind(5,2,6);
        int expectedX = 2;
        panel.judgeLoseMan(panel.getKind(),man);
        int actualX = (man.getStep());
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove11() {
        Man man = new Man(0, 0, 2, 3);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove12() {
        Man man = new Man(1, 5, 2, 3);
        panel.setKind(5,0,4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove13() {
        Man man = new Man(0, 5, 2, 2);
        panel.setKind(6,0,3);
        boolean expectedX = true;
        boolean actualX = panel.judgeLoseMan(panel.getKind(),man);
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove14() {
        Man man = new Man(0, 0, 2, 1);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove15() {
        Man man = new Man(19, 0, 2, 4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove16() {
        Man man = new Man(0, 5, 2, 1);
        panel.setKind(3,0,4);
        int expectedX = 1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove17() {
        Man man = new Man(0, 1, 1, 1);
        panel.setKind(0,0,4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove18() {
        Man man = new Man(0, 3, 2, 1);
        panel.setKind(2,0,4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }
    @Test
    public void testMove19() {
        Man man = new Man(1, 0, 1, 3);
        panel.setKind(0,0,4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }
    @Test
    public void testMove20() {
        Man man = new Man(2, 0, 2, 3);
        panel.setKind(0,1,4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testMove21() {
        Man man = new Man(0, 0, 1, 2);
        panel.setKind(1,0,4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }
    @Test
    public void testMove22() {
        Man man = new Man(0, 0, 2, 3);
        panel.setKind(1,0,4);
        int expectedX = -1;
        int actualX = (man.canMove(panel.getKind()));
        assertEquals(expectedX, actualX);
    }


    @Test
    public void testImage1() {
        Man man = new Man(0, 5, 2, 1);
        Image expected = new ImageIcon("pictures/man_u.png").getImage();
        Image actual = man.getImage();
        assertEquals(expected, actual);
    }

    @Test
    public void testImage2() {
        Man man = new Man(0, 5, 2, 2);
        Image expected = new ImageIcon("pictures/man_d.png").getImage();
        Image actual = man.getImage();
        assertEquals(expected, actual);
    }

    @Test
    public void testImage3() {
        Man man = new Man(0, 5, 2, 3);
        Image expected = new ImageIcon("pictures/man_l.png").getImage();
        Image actual = man.getImage();
        assertEquals(expected, actual);
    }

    @Test
    public void testImage4() {
        Man man = new Man(0, 5, 2, 4);
        Image expected = new ImageIcon("pictures/man_r.png").getImage();
        Image actual = man.getImage();
        assertEquals(expected, actual);
    }
}