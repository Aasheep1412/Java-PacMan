package moduletest.scoretest;

import org.junit.Test;
import game.GameManager;
import game.LevelPanel;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

/**
 * 测试用例002-UT-21 ~ 002-UT-22
 * 测试得分及通关模块
 * @author DengYuhan
 * @date 2020/3/27
 */
public class EatBeanScoreTest {

    public int[] scores = {-1, -1, -1};
    public GameManager g = new GameManager(1, true, scores);

    @Test
    public void eatBeanTrueTest() {
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
        //置为豆子
        g.kinds[0][1][0] = LevelPanel.BEAN;
        int expected = 60;
        int actual = panel.eatBean(40, 1, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void eatBeanFalseTest() {
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
        //置为空地
        g.kinds[0][1][0] = LevelPanel.FLOOR;
        int expected = 40;
        int actual = panel.eatBean(40, 1, 0);
        assertEquals(expected, actual);
    }
}
