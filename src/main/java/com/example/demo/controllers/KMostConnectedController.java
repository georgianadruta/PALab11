package com.example.demo.controllers;

import com.example.demo.services.FriendshipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kMostConnected")
public class KMostConnectedController {

    @GetMapping
    public String getKLeastConnected() {
        return FriendshipService.getKMostConnected(3);
    }
}

