import com.qbhy.apiboot.ApiApplication;
import com.qbhy.apiboot.config.MailConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class MailTest {

    @Autowired
    MailSender mailSender;

    @Autowired
    MailConfig mailConfig;

    @Test
    public void testSendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom(mailConfig.getUsername());//发件人
        message.setTo("572490755@qq.com");//收件人
        message.setSubject("Spring Email Test");//主题
        message.setText("hello world!!");//正文
        mailSender.send(message);
        System.out.println("邮件发送完毕");

    }
}