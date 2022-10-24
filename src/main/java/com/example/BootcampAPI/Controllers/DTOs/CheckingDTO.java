package com.example.BootcampAPI.Controllers.DTOs;

import com.example.BootcampAPI.embeddables.Money;
import com.example.BootcampAPI.entity.AccountHolder;
import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;

public class CheckingDTO {
    @NotEmpty
    private Long id;
    @NotEmpty
    @Embedded
    private Money balance;
    private AccountHolder primaryOwner;
    private AccountHolder secondaryOwner;
    private String secretKey;

    public CheckingDTO(Long id, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        this.id = id;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.secretKey = secretKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}