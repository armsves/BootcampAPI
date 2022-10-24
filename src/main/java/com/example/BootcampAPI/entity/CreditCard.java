package com.example.BootcampAPI.entity;

import com.example.BootcampAPI.embeddables.Money;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*CreditCard accounts have a default creditLimit of 100
CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
CreditCards have a default interestRate of 0.2
CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1 */
@Entity
public class CreditCard extends Account {
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);
    private LocalDateTime lastInterestApplied = LocalDateTime.now();
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "creditLimit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "creditLimit_amount"))
    })
//    @DecimalMax(value = "100000.00") TODO HACER EN EL SETTER!!!
    private Money creditLimit = new Money(BigDecimal.valueOf(100));

    public CreditCard() {}
    public CreditCard(BigDecimal interestRate, Money creditLimit) {
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }
    public BigDecimal getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
    public Money getCreditLimit() {
        return creditLimit;
    }
    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }
}