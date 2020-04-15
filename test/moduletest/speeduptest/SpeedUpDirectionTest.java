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

import static org.junit.Assert.*;
import static game.LevelPanel.SPEEDUP;
/**测试用例
 * 002-UT-42
 * 002-UT-43
 * 002-UT-44
 * 002-UT-45
 * @author BianLongyun
 * @date 2020/3/27
 */
@RunWith(Parameterized.class)
public class SpeedUpDirectionTest {
    int [][] kind;
    private Man man;
    int[] scores = {200, -1, -1};
    private int directionInput;
    private int expectedStep;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { LevelPanel.UP, 2 }, { LevelPanel.DOWN, 2 }, { LevelPanel.LEFT, 2}, { LevelPanel.RIGHT, 2 }
        });
    }
    public SpeedUpDirectionTest(int directionInput, int expectedStep){
        this.directionInput = directionInput;
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
        man = new Man(5, 5, 1, directionInput);



    }

    @After
    public void tearDown() throws Exception {
        man =null;
    }

    @Test
    public void judgeLoseMan() {
//        int[] scores = {200, -1, -1};
        GameManager g = new GameManager(1, true, scores);
        LevelPanel panel = new LevelPanel(new JFrame(), g.kinds[0], 1, scores);
        panel.judgeLoseMan(kind,man);
        assertEquals(expectedStep,man.getStep(),0.001);


    }
}