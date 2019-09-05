import com.qbhy.apiboot.ApiApplication;
import com.qbhy.apiboot.framework.auth.AuthManager;
import com.qbhy.apiboot.framework.auth.GuardNotFoundException;
import com.qbhy.apiboot.framework.util.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class BaseKernelTest {

    @Test
    public void testAuthService() throws GuardNotFoundException {
        System.out.println(SpringContextUtil.getBean(AuthManager.class).guard("jwt"));
    }
}