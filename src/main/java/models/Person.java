package models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonType personType;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonStatus personStatus;
}


