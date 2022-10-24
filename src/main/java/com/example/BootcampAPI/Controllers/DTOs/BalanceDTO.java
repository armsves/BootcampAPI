package com.example.BootcampAPI.Controllers.DTOs;

import com.example.BootcampAPI.embeddables.Money;

import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;

public class BalanceDTO {
    @NotEmpty
    private Long id;
    @NotEmpty
    @Embedded
    private Money balance;

    public BalanceDTO(Long id, Money balance) {
        setId(id);
        setBalance(balance);
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Money getBalance() { return balance; }
    public void setBalance(Money balance) { this.balance = balance; }
}