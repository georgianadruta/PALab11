package com.example.demo.controllers;

import com.example.demo.custom.CustomException;
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

    /**
     * obtaining the list of the friendships, via a HTTP GET request.
     */
    @GetMapping
    public List<Friendship> getFriendship() {
        return FriendshipService.showList();
    }

    /**
     * obtaining the list of the friendships, via a HTTP GET request.
     */
    @GetMapping("/{id}")
    public Friendship getFriendship(@PathVariable String id) {
        for (int i = 0; i < FriendshipService.friendshipList.size(); i++) {
            if (FriendshipService.friendshipList.get(i).getId().equals(id)) {
                return FriendshipService.friendshipList.get(i);
            }
        }
        throw new CustomException("Not found");
    }

    /**
     * obtaining the count of the friendships, via a HTTP GET request.
     */
    @GetMapping("/count")
    public static int getCountFriendships() {
        return FriendshipService.friendshipList.size();
    }

    /**
     * modifying the  friendship, via a HTTP POST request.
     */
    @PostMapping("/{id}/{anotherId}")
    public ResponseEntity<String> updateFriendship(@PathVariable("id") String id, @PathVariable("anotherId") String anotherId) {
        FriendshipService.updateFriendship(id, anotherId);
        return new ResponseEntity<>("Friendship updated successfully", HttpStatus.CREATED);
    }

    /**
     * adding a new friendship in the database, via a HTTP PUT request.
     */
    @PutMapping("/{id}/{anotherId}")
    public ResponseEntity<String> addFriendship(@PathVariable("id") String id, @PathVariable("anotherId") String anotherId) {
        String key = String.valueOf(FriendshipService.friendshipList.size());
        FriendshipService.addFriendship(key, id, anotherId);
        return new ResponseEntity<>("Friendship added successfully", HttpStatus.OK);
    }

    /**
     * deleting a friendship, via a HTTP DELETE request.
     */
    @DeleteMapping(value = "/{id}")
    public static ResponseEntity<String> deleteFriendship(@PathVariable String id) {
        FriendshipService.deleteFriendship(id);
        return new ResponseEntity<>("Deleted successfully", new HttpHeaders(), HttpStatus.OK);
    }
}

