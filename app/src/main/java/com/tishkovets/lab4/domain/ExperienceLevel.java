package com.tishkovets.lab4.domain;

public class ExperienceLevel {
    private int id;
    private String name;

    public ExperienceLevel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
