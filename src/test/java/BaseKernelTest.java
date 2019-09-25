import com.qbhy.apiboot.ApiApplication;
import com.qbhy.apiboot.app.models.User;
import com.qbhy.apiboot.app.repositories.UserRepository;
import com.qbhy.apiboot.config.HashingConfig;
import com.qbhy.apiboot.framework.auth.AuthManager;
import com.qbhy.apiboot.framework.auth.guard.JwtGuard;
import com.qbhy.apiboot.framework.hashing.HashManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class BaseKernelTest {

    @Autowired
    private AuthManager authManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashManager hashManager;

    @Autowired
    HashingConfig hashingConfig;

    @Test
    public void testAuthService() throws Throwable {
        Object token = authManager.driver("jwt").login(userRepository.findById(1L).orElseThrow(() -> new Exception("抛异常")));
        System.out.println(token);
        System.out.println(((JwtGuard) authManager.driver("jwt")).user((String) token));
    }

    @Test
    public void testHashing() throws Throwable {
        String hashedValue = hashManager.make("string", hashingConfig);
        System.out.println(hashedValue);
        Assert.assertTrue("结果", hashManager.check("string", hashedValue, hashingConfig));
    }
}