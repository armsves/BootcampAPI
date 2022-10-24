package com.example.BootcampAPI.entity;

import com.example.BootcampAPI.embeddables.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends Users{
    private LocalDate dateOfBirth;
    @Embedded
    private Address address;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "postAddress", column = @Column(name = "mailingAddress_postAddress")),
            @AttributeOverride(name = "city", column = @Column(name = "mailingAddress_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mailingAddress_postalCode"))
    })
    private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> primaryOwnerList;

    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryOwnerList;


    public AccountHolder() {}

    public AccountHolder(String name, String username, String password, LocalDate dateOfBirth, Address address, Address mailingAddress) {
        super(name, username, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = mailingAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Address getMailingAddress() {
        return mailingAddress;
    }
    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
    public List<Account> getPrimaryOwnerList() {return primaryOwnerList; }
    public void setPrimaryOwnerList(List<Account> primaryOwnerList) {this.primaryOwnerList = primaryOwnerList;}
    public List<Account> getSecondaryOwnerList() {return secondaryOwnerList; }
    public void setSecondaryOwnerList(List<Account> secondaryOwnerList) {this.secondaryOwnerList = secondaryOwnerList;}
}