import com.qbhy.apiboot.ApiApplication;
import com.qbhy.apiboot.app.repositories.UserRepository;
import com.qbhy.apiboot.framework.auth.AuthManager;
import com.qbhy.apiboot.framework.auth.guard.JwtGuard;
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

    @Test
    public void testAuthService() throws Throwable {
        System.out.println(authManager.guard("jwt").login(userRepository.findById(1L).orElse(null)));
        System.out.println(((JwtGuard) authManager.guard("jwt")).user("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlkIjoiMSJ9.Tq7E8jH9luxEOtsaySyQFSmdPEnnAkYg_SYwIaCBHjc"));
    }
}