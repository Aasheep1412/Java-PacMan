package moduletest;

import moduletest.scoretest.EatBeanScoreTest;
import moduletest.scoretest.GameWinOrLoseScoreTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import moduletest.scoretest.GameWinOrLoseScoreTest;

/**
 * 测试用例002-UT-21 ~ 002-UT-30
 * 测试得分及通关模块
 * @author DengYuhan
 * @date 2020/3/27
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({EatBeanScoreTest.class,
        GameWinOrLoseScoreTest.class})
public class ScoreTestSuite {

}
