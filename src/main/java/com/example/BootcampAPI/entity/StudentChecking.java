package com.example.BootcampAPI.entity;

import com.example.BootcampAPI.embeddables.Money;

import javax.persistence.Entity;
import java.time.LocalDate;
import static java.time.LocalDate.now;

@Entity
public class StudentChecking extends Account implements HasSecretKey {
    private String secretKey;
    public StudentChecking() {}

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
    }
    public String getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}