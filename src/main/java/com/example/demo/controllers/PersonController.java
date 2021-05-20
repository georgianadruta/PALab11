package com.example.demo.controllers;

import com.example.demo.services.PersonService;
import com.example.demo.models.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * a REST controller containing methods for:
 * a HTTP GET request
 * a HTTP POST request
 * a HTTP PUT request
 * a HTTP DELETE request.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
    static List<Person> personList = new ArrayList<>();

    public PersonController() {
    }

    /**
     * obtaining the list of the persons, via a HTTP GET request.
     */
    @GetMapping
    public List<Person> getPersons() {
        return PersonService.showList();
    }


    /**
     * obtaining the list of the persons, via a HTTP GET request.
     */
    @GetMapping("/{id}")
    public static Person getPerson(@PathVariable("id") int id) {
        for (int i = 0; i < PersonService.personList.size(); i++) {
            if (Integer.parseInt(PersonService.personList.get(i).getId())==id) {
                return PersonService.personList.get(i);
            }
        }
        return null;
    }

    @GetMapping("/count")
    public static int getCountPersons() {
        return PersonService.personList.size();
    }

    /**
     * adding a new person in the database, via a HTTP POST request.
     */
    @PostMapping("/{name}")
    public ResponseEntity<String> createPerson(@RequestParam("name") String name) {
        String id = String.valueOf(PersonService.personList.size());
        personList.add(new Person(id, name));
        PersonService.addPerson(id, name);
        return new ResponseEntity<>("Person added successfully, id: " + id, HttpStatus.CREATED);
    }

    /**
     * modifying the name of a person, via a HTTP PUT request.
     */
    @PutMapping("/{id}/{name}")
    public ResponseEntity<String> updatePerson(@PathVariable String id, @RequestParam("name") String name) {
        PersonService.updatePerson(id, name);
        return new ResponseEntity<>("Person's name updated successfully to " + name, HttpStatus.OK);
    }

    /**
     * deleting a person, via a HTTP DELETE request.
     */
    @DeleteMapping(value = "/{id}")
    public static ResponseEntity<String> deletePerson(@PathVariable String id) {
        ResponseEntity<String> response = PersonController.deletePerson(id);
        return new ResponseEntity<>(String.valueOf(response), new HttpHeaders(), HttpStatus.OK);
    }
}
