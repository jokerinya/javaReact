package com.jokerinya;

public class CourseManager {

    public Course course;

    public CourseManager(Course course) {
        this.course = course;
    }

    public void addUserToCourse(User user){
        System.out.println(user.getFullName() +" has been added to " + this.course.getName()+ " course.");
    }

    public void addResourceToCourse(Instructor instructor, String name){
        System.out.println(instructor.getFullName() + " added to " + this.course.getName() + " course "+
                " new resources -> " + name);
    }

    public void attendToLesson(User user){
        System.out.println(user.getFullName() + " has attended the lesson of " + this.course.getName());
    }

    public void sendEmail(UserManager userManager){
        userManager.sendEmail();
    }
}
