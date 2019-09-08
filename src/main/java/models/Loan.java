package models;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    Person person;

    @OneToOne(cascade = CascadeType.ALL)
    Book book;

    @Temporal(TemporalType.DATE)
    Date createdDate;

    @Temporal(TemporalType.DATE)
    Date updateDate;

    @OneToOne(cascade = CascadeType.ALL)
    LoanStatus loanStatus;



}
