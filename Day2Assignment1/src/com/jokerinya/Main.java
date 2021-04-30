package com.jokerinya;

public class Main {

    public static void main(String[] args) {
	    Instructor enginDemirog = new Instructor(1, "Engin Demirog");
	    Course javaReact = new Course(
	            1,
                "Java + React Yazilim Gelistirici Kampi",
                enginDemirog,
                "2 ay surecek olan yazilim gelisitirici yetistirme kampi",
                0
        );

	    //Students
	    Student student1 = new Student(1,"Ali Kara");
	    Student student2 = new Student(2,"Veli Beyaz");
	    Student student3 = new Student(3,"Ali Yesil");
	    Student student4 = new Student(4,"Ali Kara");

	    Student[] students = {student1, student2, student3, student4};
		System.out.println("\t----All Students---");
		for (Student student : students){
			System.out.println(student);
		}

		// Register students to the course.
		CourseManager.registerStudent(javaReact, student1);
		CourseManager.registerStudent(javaReact, student2);
		CourseManager.registerStudent(javaReact, student3);
		CourseManager.registerStudent(javaReact, student4);
		// CourseManager.registerStudent(javaReact, student4); // Error same student can't added to same course.

		// Count the students
		CourseManager.countStudents(javaReact);





    }
}
