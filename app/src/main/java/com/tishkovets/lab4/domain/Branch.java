package com.tishkovets.lab4.domain;

public class Branch {

    private Integer id;
    private String name;

    public Branch(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
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
