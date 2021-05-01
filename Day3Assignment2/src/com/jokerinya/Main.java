package com.jokerinya;

public class Main {

    public static void main(String[] args) {
        // Instructor
        Instructor engin = new Instructor("12345678901", "Engin Demirog", 35);
        // Course & Its CourseManager
        Course javaReact = new Course("Java - React");
        CourseManager javaCourseManager = new CourseManager(javaReact);
        // Students
        Student joker = new Student("98765432109", "Jokerinya M", 32);
        Student expStudent = new Student("23456789012", "Example Student", 32);
        // Lets add users to DB w/ help of loop
        UserManager userManager = new UserManager();
        User[] users = new User[]{engin, joker, expStudent};
        for (User user : users){
            userManager.addToDatabase(user);
        }
        // Let's add Students to Course
        javaCourseManager.addUserToCourse(joker);
        javaCourseManager.addUserToCourse(expStudent);
        // Students and Instructor should attend the lesson of course
        StudentManager studentManager = new StudentManager();
        studentManager.attendToLesson(joker, javaCourseManager);
        InstructorManager instructorManager = new InstructorManager();
        instructorManager.attendToLesson(engin, javaCourseManager);
        // Instructor should be able to add resource to the course
        instructorManager.addResourceToCourse(engin, "Day 4 Assignments", javaCourseManager);
        // Send email to students by CourseManager W/ override method at StudentManager
        javaCourseManager.sendEmail(studentManager);
        // and send email to Instructor too.
        javaCourseManager.sendEmail(instructorManager);
    }
}
