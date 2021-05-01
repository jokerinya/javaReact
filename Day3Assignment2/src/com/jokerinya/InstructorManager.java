package com.jokerinya;

public class InstructorManager extends UserManager{
    public void addResourceToCourse(Instructor instructor, String name ,CourseManager courseManager){
        courseManager.addResourceToCourse(instructor, name);
    }
    public void attendToLesson(Instructor instructor, CourseManager courseManager){
        courseManager.attendToLesson(instructor);
        // do some other for instructor
    }

    @Override
    public void sendEmail() {
        System.out.println("Send email to instructors.");
    }
}
