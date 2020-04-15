package moduletest.speeduptest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import game.GameManager;
import game.LevelPanel;
import game.roles.Man;

import javax.swing.*;

import static org.junit.Assert.*;
import static game.LevelPanel.BEAN;

/**
 * 测试用例002-UT-51
 * @author BianLongyun
 * @date 2020/3/27
 */
public class SpeedUpKindTest {
    int [][] kind;
    private Man man;
    int[] scores = {200, -1, -1};

    @Before
    public void setUp() throws Exception {
        kind =new int[LevelPanel.GRIDSNUM][LevelPanel.GRIDSNUM];
        for(int i=0;i<LevelPanel.GRIDSNUM;i++){
            for (int j=0;j<LevelPanel.GRIDSNUM;j++){
                kind[i][j]=BEAN;

            }
        }
        man = new Man(5, 5, 1, 1);
    }

    @After
    public void tearDown() throws Exception {
        man =null;
    }

    @Test
    public void judgeLoseMan() {
        GameManager g = new GameManager(1, true, scores);
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
        panel.judgeLoseMan(kind,man);
        assertEquals(1,man.getStep(),0.001);
    }
}