package moduletest.speeduptest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import game.GameManager;
import game.LevelPanel;

import javax.swing.*;

import static org.junit.Assert.*;
import static game.LevelPanel.SPEEDUP;

/**测试用例
 * 002-UT-46
 * @author BianLongyun
 * @date 2020/3/27
 */
public class SpeedUpNumTest {
    int[] scores = {200, -1, -1};
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void initialSpeedUp() {
        GameManager g = new GameManager(1, true, scores);
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[2], 2, scores);
        int [][] kind = panel.getKind();
        int actualNum=0;
        for (int i = 0; i < kind[0].length; i++) {
            for (int j = 0; j < kind.length; j++) {
                if (kind[i][j]==SPEEDUP) {
                    actualNum++;
                }
            }
        }
        assertEquals(5,actualNum);
    }
}