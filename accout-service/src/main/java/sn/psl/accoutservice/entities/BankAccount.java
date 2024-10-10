package sn.psl.accoutservice.entities;

import jakarta.persistence.*;
import lombok.*;
import sn.psl.accoutservice.models.Customer;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private LocalDate date;
    private String currency;
    //@Enumerated(EnumType.ORDINAL) c'est le numero de l'element enum qui est stocker dans la BD 0=>CURRENT_ACCOUNT et 1=>SAVING_ACCOUNT
    //@Enumerated(EnumType.STRING) ce sont les valeur de l'enum CURRENT_ACCOUNT et SAVING_ACCOUNT qui seront stocker dans la BD
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;

}
