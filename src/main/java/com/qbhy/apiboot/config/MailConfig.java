package com.qbhy.apiboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Data
public class MailConfig {

    private String host;

    private int port;

    private String username;

    private String password;

    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(port);//默认端口，标准的SMTP端口
        mailSender.setUsername(username);//用户名
        mailSender.setPassword(password);//授权码
        return mailSender;
    }
}
