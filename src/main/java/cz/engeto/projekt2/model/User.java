package cz.engeto.projekt2.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

public class User {
    private long id;
    private String name;
    private String surname;
    private String personId;
    private String personUuid ;

    public User() {
    }

    public User(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname, String personId, String personUuid) throws SQLException {
        this.name = name;
        this.surname = surname;
        this.personId = getValidId(personId);
        this.personUuid = personUuid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonUuid() {
        return personUuid;
    }

    public void setPersonUuid(String personUuid) {
        this.personUuid = personUuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", personId='" + personId + '\'' +
                ", personUuid='" + personUuid + '\'' +
                '}';
    }

    public String getValidId(String id) throws SQLException {
        if (id.length() == 12 && isIdValid(id) && !existIdInDatabase(id)) {
            return id;
        }
        return null;
    }

    public boolean isIdValid(String id) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("personID.txt")))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(id)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean existIdInDatabase(String id) throws SQLException {
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/registrationSystem?user=root&password=Eva171292");
        Statement statement = connect.createStatement();
        String SQL = "SELECT * FROM Users WHERE personId = '"+ id +"'";
        ResultSet result = statement.executeQuery(SQL);
        return result.next();
    }
}
