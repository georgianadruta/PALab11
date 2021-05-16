package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * a table which contains friendships for each person
 *
 * primaryKey       strangeKey name                                      name
 * an unique id     an id corresponding with a name from person table    friend's name
 */
@Data
@EqualsAndHashCode
@ToString
public class Friendship {
    private final String primaryKey;
    private final String strangeKey;
    private final String name;
}
