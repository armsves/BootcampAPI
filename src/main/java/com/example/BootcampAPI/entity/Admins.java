package com.example.BootcampAPI.entity;

import javax.persistence.Entity;

@Entity
public class Admins extends Users{
    public Admins() {}

    public Admins(String name, String username, String password) {
        super(name, username, password);
    }
}
