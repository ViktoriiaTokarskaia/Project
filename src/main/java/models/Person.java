package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person{
    @Id
    @GeneratedValue
    Long id;

    String name;




}



