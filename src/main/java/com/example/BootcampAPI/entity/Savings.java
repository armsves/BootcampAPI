package com.example.BootcampAPI.entity;

import com.example.BootcampAPI.embeddables.Money;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDate.now;

/*Savings accounts have a default interest rate of 0.0025
Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5
Savings accounts should have a default minimumBalance of 1000
Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100 */
@Entity
public class Savings extends Account implements HasSecretKey {
    private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimumBalance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimumBalance_amount"))
    })
//    @DecimalMin(value = "100.00") TODO validar en el setter!!!
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));
    @DecimalMax(value = "0.50")
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "penaltyFee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "penaltyFee_amount"))
    })
    private final Money penaltyFee = new Money(BigDecimal.valueOf(40));
    private LocalDateTime lastInterestApplied = LocalDateTime.now();

    public Savings() {}
    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
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
    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
    public BigDecimal getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}