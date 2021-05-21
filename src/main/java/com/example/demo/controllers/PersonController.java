package com.example.demo.controllers;

import com.example.demo.custom.CustomException;
import com.example.demo.services.PersonService;
import com.example.demo.models.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public static Person getPerson(@PathVariable("id") String id) {
        for (int i = 0; i < PersonService.personList.size(); i++) {
            if (PersonService.personList.get(i).getId().equals(id)) {
                return PersonService.personList.get(i);
            }
        }
        throw new CustomException("Not found");
    }

    /**
     * obtaining the count of the persons, via a HTTP GET request.
     */
    @GetMapping("/count")
    public static int getCountPersons() {
        return PersonService.personList.size();
    }

    /**
     * modifying the name of a person, via a HTTP POST request.
     */
    @PostMapping("/{id}/{name}")
    public ResponseEntity<String> updatePerson(@PathVariable("id") String id, @PathVariable("name") String name) {
        PersonService.updatePerson(id, name);
        return new ResponseEntity<>("Person's name updated successfully", new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * adding a new person in the database, via a HTTP PUT request.
     */
    @PutMapping("/{name}")
    public ResponseEntity<String> addPerson(@PathVariable("name") String name) {
        String id = String.valueOf(PersonService.personList.size());
        PersonService.addPerson(id, name);
        return new ResponseEntity<>("Person added successfully", new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * deleting a person, via a HTTP DELETE request.
     */
    @DeleteMapping(value = "/{id}")
    public static ResponseEntity<String> deletePerson(@PathVariable String id) {
        PersonService.deletePerson(id);
        return new ResponseEntity<>("Deleted successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
