import com.qbhy.apiboot.ApiApplication;
import com.qbhy.apiboot.app.models.User;
import com.qbhy.apiboot.app.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class SampleTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        Iterable<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    public void testCreate() {
        System.out.println(("----- save method test ------"));
        User user = new User("测试name", "avatar");
        userRepository.save(user);
        System.out.println(user);
    }

}