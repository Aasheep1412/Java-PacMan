package moduletest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import moduletest.speeduptest.*;

/**加速模块测试套包
 * @author BianLongyun
 * @date 2020/3/27
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SpeedUpLevelTest.class,
        SpeedUpKindTest.class,
        SpeedUpNumTest.class,
        SpeedUpDirectionTest.class})
public class SpeedUpTestSuite {

}
