package cz.engeto.projekt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class SystemApplication {



	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(SystemApplication.class, args);


		/*Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/registrationSystem?user=root&password=Eva171292");

		//Statement statement = connect.createStatement();

		//statement.execute("INSERT INTO Users(name, surname, person_id, person_uuid) VALUES('Eva', 'Marko', '123456', 'DHUDJDUD')");



		connect.close();*/
	}
}
