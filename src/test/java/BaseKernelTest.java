import com.qbhy.apiboot.ApiApplication;
import com.qbhy.apiboot.app.repositories.UserRepository;
import com.qbhy.apiboot.config.HashingConfig;
import com.qbhy.apiboot.framework.auth.AuthManager;
import com.qbhy.apiboot.framework.auth.guard.JwtGuard;
import com.qbhy.apiboot.framework.hashing.HashManager;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;
import java.util.Arrays;

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

        System.out.println(Arrays.toString(MessageDigestAlgorithms.values()));


        // sha1
        String[] arr = new String[]{"md5", "sha1", "sha256", "sha512", "sha384"};

        System.out.println(hashingConfig.getSecret());

        for (String name : arr) {
            String hashedValue = hashManager.driver(name).make("string");
            System.out.println("算法:" + name + ",哈希后:" + hashedValue);
            Assert.assertTrue("失败了", hashManager.driver(name).check("string", hashedValue));
        }

    }
}