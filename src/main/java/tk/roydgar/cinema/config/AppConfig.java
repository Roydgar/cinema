package tk.roydgar.cinema.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import tk.roydgar.cinema.util.constants.FilePaths;

@Configuration
@PropertySource(FilePaths.APPLICATION_PROPERTIES)
public class AppConfig {

}
