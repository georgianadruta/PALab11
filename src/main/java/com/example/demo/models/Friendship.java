package com.example.demo.models;

import lombok.*;

/**
 * a table which contains friendships for each person
 *
 * personKey       personId                                              anotherPersonId
 * an unique id    an id corresponding with a name from person table     an id corresponding with a name from person table
 */
@Getter
@EqualsAndHashCode
@ToString
public class Friendship {
    private final String id;
    private final String personId;
    @Setter
    private String anotherPersonId;

    public Friendship(String id, String personId, String anotherPersonId) {
        this.id = id;
        this.personId = personId;
        this.anotherPersonId = anotherPersonId;
    }
}
