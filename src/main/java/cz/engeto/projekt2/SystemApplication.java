package cz.engeto.projekt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SystemApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(SystemApplication.class, args);

		Class.forName("com.mysql.cj.jdbc.Driver");
	}

}
