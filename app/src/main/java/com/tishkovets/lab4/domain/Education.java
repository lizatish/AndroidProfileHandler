package com.tishkovets.lab4.domain;

public class Education {

    private int id;
    private String name;

    public Education(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
