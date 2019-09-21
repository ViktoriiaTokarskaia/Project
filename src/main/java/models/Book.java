package models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Genre> genres;


}

