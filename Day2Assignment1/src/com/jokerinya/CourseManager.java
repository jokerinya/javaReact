package com.jokerinya;

public class CourseManager {
    public static void registerStudent(Course course, Student student){
        boolean result = course.addStudent(student);
        if (result){
            System.out.println("Student registered correctly.");
        } else {
            System.out.println("Error occurred! " +
                    "Either student registered before or some other problem occurred.");
        }
    }

    public static void countStudents(Course course){
        int studentCount = course.getStudents().size();
        System.out.println("Course name: " + course.getName() +
                " student(s) Number: " + studentCount);
    }
}
