package com.example.BootcampAPI.embeddables;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {
    @NotNull
    private String postAddress;
    @NotNull
    private String city;
    @NotNull
    private Integer postalCode;

    public Address() {
    }

    public Address(String postAddress, String city, Integer postalCode) {
        this.postAddress = postAddress;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
