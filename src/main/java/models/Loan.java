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

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Temporal(TemporalType.DATE)
    private Date updateDate;




}
