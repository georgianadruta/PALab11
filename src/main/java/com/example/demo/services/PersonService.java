package com.example.demo.services;

import com.example.demo.custom.CustomException;
import com.example.demo.database.Database;
import com.example.demo.models.Person;
import lombok.Data;
import lombok.var;

import java.sql.Connection;
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
    public static final List<Person> personList = new ArrayList<>();

    /**
     * add person in persons table
     */
    public static void addPerson(Person person) {
        personList.add(person);
        Connection con = Database.getConnection();
        try {
            var statement = con.prepareStatement("INSERT INTO PERSONS VALUES (?,?)");
            statement.setString(1, person.getId());
            statement.setString(2, person.getName());
            statement.executeQuery();
        } catch (SQLException exception) {
            logger.info("Exception in addPerson method.");
        }
    }

    /**
     * add person in persons table
     */
    public static void addPerson(String id, String name) {
        personList.add(new Person(id, name));
        Connection con = Database.getConnection();
        try {
            var statement = con.prepareStatement("INSERT INTO PERSONS VALUES (?,?)");
            statement.setString(1, id);
            statement.setString(2, name);
            statement.executeQuery();
        } catch (SQLException exception) {
            logger.info("Exception in addPerson method.");
        }
    }

    /**
     * update person with the give id in persons table
     */
    public static void updatePerson(String id, String name) {
        var con = Database.getConnection();
        for (Person person : personList) {
            if (person.getId().equals(id)) {
                person.setName(name);
                break;
            }
        }
        try {
            var statement = con.prepareStatement("UPDATE PERSONS SET NAME=? WHERE ID=?");
            statement.setString(1, name);
            statement.setString(2, id);
            statement.executeQuery();
        } catch (SQLException exception) {
            logger.info("Exception in updatePerson method.");
        }
    }

    /**
     * delete person with the given id in persons table
     */
    public static void deletePerson(String id) {
        var con = Database.getConnection();
        try {
            var statement = con.prepareStatement("DELETE FROM PERSONS WHERE ID=?");
            statement.setString(1, id);
            statement.executeQuery();
            personList.removeIf(person -> person.getId().equals(id));
        } catch (SQLException exception) {
            logger.info("Exception in deletePerson method.");
        }
    }

    /**
     * get the persons table
     */
    public static List<Person> showList() {
        if (personList.size() > 0) {
            return personList;
        } else {
            throw new CustomException("Empty list.");
        }
    }
}