package com.jokerinya;

public class Student {
    private String name;
    private int id;
    public Student(int id, String name) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Id:" + id + " Name= " + name;
    }
}
