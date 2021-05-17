package com.example.demo.models;

import lombok.*;

/**
 * a table which contains friendships for each person
 * <p>
 * primaryKey       strangeKey name                                      name
 * an unique id     an id corresponding with a name from person table    friend's name
 */
@Getter
@EqualsAndHashCode
@ToString
public class Friendship {
    private final String primaryKey;
    private final String strangeKey;
    @Setter
    private String name;

    public Friendship(String primaryKey, String strangeKey, String name) {
        this.primaryKey = primaryKey;
        this.strangeKey = strangeKey;
        this.name = name;
    }
}
