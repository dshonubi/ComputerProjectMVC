package uk.ac.le.cs.CO3015;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"uk.ac.le.cs.CO3015.*"})
public class ComputerProjectMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputerProjectMvcApplication.class, args);
	}

}
