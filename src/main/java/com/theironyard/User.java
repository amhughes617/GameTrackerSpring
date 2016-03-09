package com.theironyard;

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

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }
}
