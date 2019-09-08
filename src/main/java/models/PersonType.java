package models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class PersonType {
    @Id
    @GeneratedValue
    Long id;

    String type;
}
