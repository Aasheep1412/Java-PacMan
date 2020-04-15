package moduletest;

import moduletest.judgelosetest.JudgeLoseManTest;
import moduletest.judgelosetest.JudgeLoseMonsterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**死亡模块测试套包
 * 测试用例002-UT-31 ~ 002-UT-41
 * @author LuoBingqian
 * @date 2020/3/27
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JudgeLoseManTest.class,
        JudgeLoseMonsterTest.class})
public class JudgeLoseTestSuite {

}