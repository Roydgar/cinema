package tk.roydgar.cinema.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import tk.roydgar.cinema.util.constants.FilePaths;

@Configuration
@PropertySource(FilePaths.APPLICATION_PROPERTIES)
public class AppConfig{

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("application");
    }

}
