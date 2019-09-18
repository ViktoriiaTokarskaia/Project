package models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Person{
    @Id
    @GeneratedValue
    Long id;

    String firstName;
    String lastName;
    String email;
    String password;

    @OneToOne(cascade = CascadeType.ALL)
    PersonType personType;

    @OneToOne(cascade = CascadeType.ALL)
    PersonStatus personStatus;

}


