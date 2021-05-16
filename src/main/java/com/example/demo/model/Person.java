package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * a table which contains person's name
 *
 * primaryKey       name
 */
@Data
@EqualsAndHashCode
@ToString
public class Person{
    private final String id;
    private final String name;
}
