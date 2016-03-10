package com.theironyard.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alexanderhughes on 3/9/16.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;
    @NotNull
    @Column(unique = true)
    String userName;
    @NotNull
    private String passwordHash;

    public User() {
    }

    public User(String userName, String passwordHash) {
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
