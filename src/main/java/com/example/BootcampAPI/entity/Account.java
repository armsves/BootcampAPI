package com.example.BootcampAPI.entity;

import com.example.BootcampAPI.embeddables.Money;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDate.now;

@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount"))
    })
    private Money balance;

    @ManyToOne
//    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "primary_owner_id")
    @NotNull(message = "espaniol")
    private AccountHolder primaryOwner;
    @ManyToOne
//    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "secondary_owner_id")
    private AccountHolder secondaryOwner;
    private final LocalDate creationDate = now();
    private Status status = Status.ACTIVE;
    public Account() { }
    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
    }

    public Long getId() { return id; }
    public Money getBalance() {return balance;}
    public void setBalance(Money balance) {this.balance = balance;}
    public AccountHolder getPrimaryOwner() {return primaryOwner;}
    public void setPrimaryOwner(AccountHolder primaryOwner) {this.primaryOwner = primaryOwner;}
    public AccountHolder getSecondaryOwner() {return secondaryOwner;}
    public void setSecondaryOwner(AccountHolder secondaryOwner) {this.secondaryOwner = secondaryOwner;}
    public LocalDate getCreationDate() {return creationDate;}
    public Status getStatus() {return status; }
    public void setStatus(Status status) {this.status = status;}
}