package com.example.BootcampAPI.entity;

import javax.persistence.*;

@Entity
public class ThirdParty{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hashedKey;
    private String name;

    public ThirdParty() { }
    public ThirdParty(String name, String hashedKey) {
        this.hashedKey = hashedKey;
        this.name = name;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getHashedKey() {return hashedKey;}
    public void setHashedKey(String hashedKey) {this.hashedKey = hashedKey;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name; }
}