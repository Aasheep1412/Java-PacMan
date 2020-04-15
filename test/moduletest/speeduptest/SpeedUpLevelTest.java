package moduletest.speeduptest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import game.GameManager;
import game.LevelPanel;
import game.roles.Man;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static game.LevelPanel.SPEEDUP;
/**
 * 测试用例
 * 002-UT-47
 * 002-UT-48
 * 002-UT-49
 * 002-UT-50
 * @author BianLongyun
 * @date 2020/3/27
 */
@RunWith(Parameterized.class)
public class SpeedUpLevelTest {
    int [][] kind;
    private Man man;
    int[] scores = {200, -1, -1};
    private int levelInput;
    private int expectedStep;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1, 2 }, { 2, 2 }, { 3, 2},{100,2}
        });
    }
    public SpeedUpLevelTest(int levelInput, int expectedStep){
        this.levelInput = levelInput;
        this.expectedStep = expectedStep;
    }
    @Before
    public void setUp() throws Exception {
        kind =new int[LevelPanel.GRIDSNUM][LevelPanel.GRIDSNUM];
        for(int i=0;i<LevelPanel.GRIDSNUM;i++){
            for (int j=0;j<LevelPanel.GRIDSNUM;j++){
                kind[i][j]=SPEEDUP;

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
        GameManager g = new GameManager(levelInput,true,scores);
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], levelInput, scores);
        panel.judgeLoseMan(kind,man);
        assertEquals(expectedStep,man.getStep(),0.001);
    }
}