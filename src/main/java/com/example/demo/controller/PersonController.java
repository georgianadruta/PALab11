package com.example.demo.controller;

import com.example.demo.services.PersonService;
import com.example.demo.model.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
     * @return
     */
    @GetMapping("/{id}")
    public static Person getPerson(@PathVariable("id") String id) {
        return personList.stream()
                .filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }


    /**
     * adding a new person in the database, via a HTTP POST request.
     */
    @PostMapping
    public ResponseEntity<String> createPerson(@RequestParam("name") String name) {
        Random rand = new Random();
        String id = String.valueOf(rand.nextInt(9999));
        personList.add(new Person(id, name));
        PersonService.addPerson(id, name);
        return new ResponseEntity<>(
                "Person added successfully, id: " + id, HttpStatus.CREATED);
    }


    /**
     * adding a new person in the database, via a HTTP POST request.
     */
    @PostMapping(value = "/create", consumes = "application/json")
    public static ResponseEntity<String> createPerson(@RequestBody Person person) {
        ResponseEntity<String> anotherPerson = PersonController.createPerson(person);
        personList.add(person);
        return new ResponseEntity<>(String.valueOf(anotherPerson), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * modifying the name of a person, via a HTTP PUT request.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable String id, @RequestParam("name") String name) {
        PersonService.updatePerson(id, name);
        return new ResponseEntity<>(
                "Person's name updated successfully to " + name, HttpStatus.OK);
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
