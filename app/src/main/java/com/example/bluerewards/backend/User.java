package com.example.bluerewards.backend;


// Store class is found under `stores` package, unlike this `User` class

// User class with id, name, surname, passwordHash and points (properties, constructor, getters and setters)
public class User {
    private long id;
    private String name;
    private String surname;
    private String passwordHash;
    private int points;

    public User(long id, String name, String surname, String passwordHash, int points) {
        this.id = id;
        this.name = name;
        this.surname = surname;
//        this.passwordHash = UserUtil.hashPassword(password);
        this.passwordHash = passwordHash;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
