package com.jokerinya;

public class StudentManager extends UserManager {
    public void attendToLesson(Student student, CourseManager courseManager){
        courseManager.attendToLesson(student);
        // do some other for student
    }

    @Override
    public void sendEmail() {
        System.out.println("Send email to students.");
    }
}
