package garritsen.vxpush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author Garritsen
 * @Version 1.0.0
 * @Description xxx
 * Created at 2022/8/31 19:02
 */

@SpringBootApplication
@EnableScheduling
public class DailyCareApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyCareApplication.class, args);
    }
}
