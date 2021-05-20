package com.example.demo.services;

import com.example.demo.custom.CustomException;
import com.example.demo.database.Database;
import com.example.demo.models.Person;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * an helpful class for sql methods
 */
@Data
public class PersonService {
    private static final Logger logger = Logger.getLogger(String.valueOf(PersonService.class));
    private static Database db = Database.getInstance();
    public static final List<Person> personList = new ArrayList<>();

    public static String addPerson(String id, String name) {
        String query = "INSERT INTO persons(id, name) VALUES('" + id + "','" + name + "')";
        db.doUpdate(query);
        personList.add(new Person(id, name));
        return name + " has been added to the list." + "\n" + personList;
    }

    public static String addPerson(Person person) {
        String query = "INSERT INTO persons(id, name) VALUES('" + person.getId() + "','" + person.getName() + "')";
        db.doUpdate(query);
        personList.add(new Person(person.getId(), person.getName()));
        return person.getName() + " has been added to the list." + "\n" + personList;
    }

    public static String updatePerson(String id, String newName) {
        String query = "UPDATE persons SET name= '" + newName + "' WHERE id='" + id + "'";
        db.doUpdate(query);
        for (Person person : personList) {
            if (person.getId().equals(id)) {
                person.setName(newName);
                return "Person with id=" + id + " got updated to " + newName + "\n" + personList;
            }
        }
        throw new CustomException("The person with the id " + id + " not found"+ "\n" + personList);
    }

    public static String deletePerson(String id) {
        String query = "DELETE FROM persons WHERE id='" + id + "'";
        db.doUpdate(query);
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId().equals(id)) {
                personList.remove(i);
                return "The person with the id " + id + " has been deleted successfully" + "\n" + personList;
            }
        }
        throw new CustomException("The person with the id " + id + " not found"+ "\n" + personList);
    }

    public static List<Person> showList() {
        ResultSet rs = db.setResultSet("SELECT id, name FROM persons");
        try {
            while (rs.next()) {
                Person person = new Person(rs.getString(1), rs.getString(2));
                personList.add(person);
            }
        } catch (Exception exception) {
            logger.info("Empty table");
        }
        return personList;
    }

    public static Person showPerson(String id) {
        Person person = null;
        ResultSet rs = db.setResultSet("SELECT id, name FROM persons WHERE id= " + id + "'");
        try {
            while (rs.next()) {
                person = new Person(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException exception) {
            throw new CustomException("Person not found for id=" + id);
        }
        return person;
    }
}
