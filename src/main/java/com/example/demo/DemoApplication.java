package com.example.demo;

import com.example.demo.services.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@SpringBootApplication
public class DemoApplication {
    private static final Logger logger = Logger.getLogger(String.valueOf(DemoApplication.class));

    @RequestMapping
    public String command() {
        return "<h1>" + PersonService.addPerson("1", "Ana") + "</h1>\n<h1>" +
                PersonService.updatePerson("1", "Ana-Maria") + "</h1>\n<h1>" +
                PersonService.showList() + "</h1>\n<h1>" + PersonService.deletePlayer("5") + "</h1>";

    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        logger.info("Running...");
    }
}
