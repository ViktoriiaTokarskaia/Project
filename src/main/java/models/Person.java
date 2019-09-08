package models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;


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



