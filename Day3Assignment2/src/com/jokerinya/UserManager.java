package com.jokerinya;

public class UserManager {
    public void addToDatabase(User user){
        System.out.println(user.getNationalId() + " - " + user.getFullName() + " has been recorded to DB.");
    }

    public void sendEmail(){
        System.out.println("Default email.");
    }

}
