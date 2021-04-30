package com.jokerinya;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private int id;
    private String name;
    private String details;
    private double price;
    private Instructor instructor;
    private Set<Student> students; // Students must be uniqe in the course

    public Course(int id, String name, Instructor instructor ,String details, double price) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.instructor = instructor;
        this.price = price;
        this.students = new HashSet<>();
    }
    public boolean addStudent(Student student){
        return students.add(student);
    }
    public HashSet<Student> getStudents(){
        return new HashSet<>(this.students);
    }
    public String getName() {
        return name;
    }
}
