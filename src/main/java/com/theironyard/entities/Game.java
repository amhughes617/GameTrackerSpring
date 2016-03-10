package com.theironyard.entities;

import com.theironyard.entities.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alexanderhughes on 3/8/16.
 */
@Entity
public class Game {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String platform;
    @NotNull
    private String genre;
    private int releaseYear;//won't be null, it's a primitive type, throws error if null passed into it
    @ManyToOne
    private User user;

    public Game() {} //need blank constructor for hibernate findAll() for method

    public Game(String name, String platform, String genre, int releaseYear, User user) {
        this.name = name;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
