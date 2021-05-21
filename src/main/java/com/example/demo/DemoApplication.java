package com.example.demo;

import com.example.demo.services.FriendshipService;
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
    public String executeCommand() {
        PersonService.addPerson("0", "p0");
        PersonService.addPerson("1", "p1");
        PersonService.addPerson("2", "p2");
        PersonService.addPerson("3", "p3");
        PersonService.addPerson("4", "p4");
        PersonService.addPerson("5", "p5");
        PersonService.addPerson("6", "p6");
        PersonService.addPerson("7", "p7");
        FriendshipService.addFriendship("0", "0", "1");
        FriendshipService.addFriendship("1", "0", "2");
        FriendshipService.addFriendship("2", "0", "3");
        FriendshipService.addFriendship("3", "1", "4");
        FriendshipService.addFriendship("4", "1", "5");
        FriendshipService.addFriendship("5", "1", "6");
        FriendshipService.addFriendship("5", "1", "7");
        return "<h1>Hello world!</h1>";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        logger.info("Running...");
    }
}
