package com.example.demo.services;

import com.example.demo.Database;
import com.example.demo.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * an helpful class for sql methods
 */
public class PersonService {
    private static final Logger logger = Logger.getLogger(String.valueOf(PersonService.class));
    private static final Database db = Database.getInstance();
    private static final List<Person> personList = new ArrayList<>();

    public static void addPerson(String id, String name) {
        String query = "INSERT INTO persons(id, name) VALUES('" + id + "','" + name + "')";
        db.doUpdate(query);
        personList.add(new Person(id, name));
        logger.info(name + " has been added to the list.");
    }

    public static void addPlayer(Person person) {
        String query = "INSERT INTO persons(id, name) VALUES('" + person.getId() + "','" + person.getName() + "')";
        db.doUpdate(query);
        personList.add(new Person(person.getId(), person.getName()));
        logger.info(person.getName() + " has been added to the database.");
    }

    public static void updatePerson(String id, String newName) {
        String query = "UPDATE persons SET name= '" + newName + "' WHERE id='" + id + "'";
        db.doUpdate(query);
        logger.info("Person with id=" + id + " got updated to " + newName);
    }

    public static void deletePlayer(String id) {
        String query = "DELETE FROM persons WHERE id='" + id + "'";
        db.doUpdate(query);
        logger.info("The person with the id " + id + " has been deleted successfully");
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

    public static Person showPerson(int id) {
        Person person = null;
        ResultSet rs = db.setResultSet("SELECT id, name FROM persons WHERE id= " + id + "'");
        try {
            while (rs.next()) {
                person = new Person(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException exception) {
            logger.info("Player not found for id " + id);
        }
        return person;
    }
}
