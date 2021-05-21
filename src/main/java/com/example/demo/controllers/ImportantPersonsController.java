package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.services.ImportantPersonsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/importantPersons")
public class ImportantPersonsController {

    public ImportantPersonsController() {
    }

    /**
     * obtaining the list of the persons, via a HTTP GET request.
     */
    @GetMapping
    public List<Person> getPersons() {
        ImportantPersonsService importantPersonsService = new ImportantPersonsService();
        return importantPersonsService.getImportantPersonList();
    }
}
