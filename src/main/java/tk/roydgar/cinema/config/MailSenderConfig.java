package tk.roydgar.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import tk.roydgar.cinema.util.StringHasher;

import java.util.Properties;

@Configuration
@PropertySource("mail.properties")
public class MailSenderConfig {

    private Environment environment;

    @Autowired
    public MailSenderConfig(Environment env) {
        this.environment = env;
    }

    @Bean
    public StringHasher stringHasher() {
        return new StringHasher();
    }

    @Bean
    public JavaMailSender getJavaMailSender(StringHasher hasher) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(hasher.decrypt(environment.getProperty("gmail.username")));
        mailSender.setPassword(hasher.decrypt(environment.getProperty("gmail.password")));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;

    }

}
