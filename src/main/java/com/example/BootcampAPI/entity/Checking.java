package com.example.BootcampAPI.entity;

import com.example.BootcampAPI.embeddables.Money;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import static java.time.LocalDate.now;
/*
When creating a new Checking account, if the primaryOwner is less than 24,
a StudentChecking account should be created otherwise a regular Checking Account should be created.
Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12
 */
@Entity
public class Checking extends Account implements HasSecretKey {
    private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimumBalance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimumBalance_amount"))
    })
    private final Money minimumBalance = new Money(BigDecimal.valueOf(250));
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "penaltyFee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "penaltyFee_amount"))
    })
    private final Money penaltyFee = new Money(BigDecimal.valueOf(40));
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "monthlyMaintenanceFee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthlyMaintenanceFee_amount"))
    })
    private final Money monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));

    public Checking() {}
    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public Money getMinimumBalance() {
        return minimumBalance;
    }
    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }
    public Money getPenaltyFee() { return penaltyFee; }
}