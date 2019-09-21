package models;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    private Book book;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @OneToOne(cascade = CascadeType.ALL)
    private LoanStatus loanStatus;


}
