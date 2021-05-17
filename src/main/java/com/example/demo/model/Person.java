package com.example.demo.model;

import lombok.*;

/**
 * a table which contains person's name
 *
 * primaryKey       name
 */

@EqualsAndHashCode
@ToString
@Getter
public class Person{

    private final String id;
    @Setter
    private String name;

    public Person(String id, String name) {
        this.id=id;
        this.name=name;
    }
}
