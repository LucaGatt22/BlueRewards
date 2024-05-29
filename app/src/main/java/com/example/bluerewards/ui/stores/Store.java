package com.example.bluerewards.ui.stores;

// Store class with id, name and location (properties, constructor, getters and setters)
public class Store {
    private long id;
    private String name;
    private String location;

    // Constructor
    public Store(long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
