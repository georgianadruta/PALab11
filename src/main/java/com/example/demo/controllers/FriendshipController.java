package com.example.demo.controllers;

import com.example.demo.services.FriendshipService;
import com.example.demo.models.Friendship;
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
@RequestMapping("/friendships")
public class FriendshipController {
    List<Friendship> friendshipList = new ArrayList<>();

    @GetMapping
    public List<Friendship> getFriendship() {
        return FriendshipService.showList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friendship> getFriendship(@PathVariable String id, @RequestParam("name") String name) {
        String key = String.valueOf(friendshipList.size() + 1);
        Friendship friendship = new Friendship(key, id, name);
        friendshipList.add(friendship);
        return new ResponseEntity<>(friendship, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createFriendship(@RequestBody Friendship friendship) {
        friendshipList.add(friendship);
        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFriendship(@PathVariable String id, @RequestParam String name) {
        FriendshipService.updateFriendship(id, name);
        return new ResponseEntity<>(
                "Person's name updated successfully to " + name, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public static ResponseEntity<String> deleteFriendship(@PathVariable String id) {
        ResponseEntity<String> response = FriendshipController.deleteFriendship(id);
        return new ResponseEntity<>(String.valueOf(response), new HttpHeaders(), HttpStatus.OK);
    }
}
