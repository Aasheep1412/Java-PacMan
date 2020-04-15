package moduletest.scoretest;

import org.junit.Test;
import game.GameManager;
import game.LevelPanel;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

/**
 * 测试用例002-UT-23 ~ 002-UT-30
 * 测试得分及通关模块
 * @author DengYuhan
 * @date 2020/3/27
 */
public class GameWinOrLoseScoreTest {

    public int[] scores = {-1, -1, -1};
    public GameManager g = new GameManager(1, true, scores);
    public static int LEVELNUM = 3;

    @Test
    public void gameWinScore(){
        int[] scores = {-1, -1, -1};
        scores[0] = 200;
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[1], 2, scores);
        int expected = 400;
        panel.setScore(400);
        panel.gameWin();
        int actual = panel.getScores()[1];
        assertEquals(expected, actual);
    }

    @Test
    public void gameWinScores(){
        int[] scores = {-1, -1, -1};
        scores[0] = 200;
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[1], 2, scores);
        int[] expected = {200, 400, -1};
        panel.setScore(400);
        panel.gameWin();
        int[] actual = panel.getScores();
        for(int i=0;i<LEVELNUM;i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void gameLoseScore(){
        int[] scores = {-1, -1, -1};
        scores[0] = 200;
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[1], 2, scores);
        int expected = 400;
        panel.setScore(400);
        panel.gameLose();
        int actual = panel.getScores()[1];
        assertEquals(expected, actual);
    }

    @Test
    public void gameLoseScores(){
        int[] scores = {-1, -1, -1};
        scores[0] = 200;
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[1], 2, scores);
        int[] expected = {200, 400, -1};
        panel.setScore(400);
        panel.gameLose();
        int[] actual = panel.getScores();
        for(int i=0;i<LEVELNUM;i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void gamePassTest1(){
        int[] scores = {-1, -1, -1};
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
        panel.setScore(500);
        panel.gameWin();
        panel = new LevelPanel(new JFrame(), g.kinds[1], 2, scores);
        panel.setScore(500);
        panel.gameWin();
        panel = new LevelPanel(new JFrame(), g.kinds[2], 3, scores);
        panel.setScore(500);
        panel.gameWin();
        int[] expected = {500, 500, 500};
        int[] actual = panel.getScores();
        for(int i=0;i<LEVELNUM;i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void gamePassTest2(){
        int[] scores = {-1, -1, -1};
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
        panel.setScore(500);
        panel.gameWin();
        panel = new LevelPanel(new JFrame(), g.kinds[1], 2, scores);
        panel.setScore(500);
        panel.gameWin();
        panel = new LevelPanel(new JFrame(), g.kinds[2], 3, scores);
        panel.setScore(450);
        panel.gameLose();
        int[] expected = {500, 500, 450};
        int[] actual = panel.getScores();
        for(int i=0;i<LEVELNUM;i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void gamePassTest3(){
        int[] scores = {-1, -1, -1};
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
        panel.setScore(500);
        panel.gameWin();
        panel = new LevelPanel(new JFrame(), g.kinds[1], 2, scores);
        panel.setScore(400);
        panel.gameLose();
        int[] expected = {500, 400, -1};
        int[] actual = panel.getScores();
        for(int i=0;i<LEVELNUM;i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void gamePassTest4(){
        int[] scores = {-1, -1, -1};
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
        panel.setScore(300);
        panel.gameLose();
        int[] expected = {300, -1, -1};
        int[] actual = panel.getScores();
        for(int i=0;i<LEVELNUM;i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
