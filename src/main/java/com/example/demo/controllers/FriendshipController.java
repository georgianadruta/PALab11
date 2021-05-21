package com.example.demo.controllers;

import com.example.demo.models.Friendship;
import com.example.demo.services.FriendshipService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private static final List<Friendship> friendshipList = new ArrayList<>();

    @GetMapping
    public List<Friendship> getFriendship() {
        return FriendshipService.showList();
    }

    @GetMapping("/{id}")
    public Friendship getFriendship(@PathVariable int id) {
        for (int i = 0; i < FriendshipService.friendshipList.size(); i++) {
            if (Integer.parseInt(FriendshipService.friendshipList.get(i).getId()) == id) {
                return FriendshipService.friendshipList.get(i);
            }
        }
        return null;
    }

    @GetMapping("/count")
    public static int getCountFriendships() {
        return FriendshipService.friendshipList.size();
    }


    @PostMapping
    public ResponseEntity<String> createFriendship(@RequestBody Friendship friendship) {
        friendshipList.add(friendship);
        return new ResponseEntity<>("Friendship created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFriendship(@PathVariable String id, @RequestParam String name) {
        FriendshipService.updateFriendship(id, name);
        return new ResponseEntity<>(
                "Person's name updated successfully to " + name, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public static ResponseEntity<String> deleteFriendship(@PathVariable String id) {
        FriendshipService.deleteFriendship(id);
        return new ResponseEntity<>("Delete with success", new HttpHeaders(), HttpStatus.OK);
    }

    public static ResponseEntity<String> getKLeastConnected(@PathVariable int k) {
        String response = FriendshipService.getKLeastConnected(k);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    public static ResponseEntity<String> getKMostConnected(@PathVariable int k) {
        String response = FriendshipService.getKMostConnected(k);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}

