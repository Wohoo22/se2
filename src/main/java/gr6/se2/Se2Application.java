package gr6.se2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("gr6.se2.*")
@ComponentScan(basePackages = { "gr6.se2.*" })
@EntityScan("gr6.se2.*")
public class Se2Application {

	public static void main(String[] args) {
		SpringApplication.run(Se2Application.class, args);
	}

}
