package com.ooyala.junittest;

import java.util.Random;

/**
 * Created by carlosandonaegui on 10/6/16.
 */
public class User {

    private int id;
    private String name;
    private String description;

    public User(String name, String description) {
        this.id = new Random().nextInt(100);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
