package models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue
    Long id;

    String title;

    @OneToOne(cascade = CascadeType.ALL)
    Author author;

    @OneToOne(cascade = CascadeType.ALL)
    Genre genre;



}

