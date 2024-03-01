package tech.nocountry.printopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PrintopiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintopiaApplication.class, args);
	}

}
