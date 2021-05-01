package com.jokerinya;

public class User {
    private String nationalId;  // added for demo purposes
    private String fullName;
    private int age;
    private String email;

    public User(String nationalId, String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
        if (nationalId.length()==11){
            this.nationalId = nationalId; // can be checked by some other mechanism !
        } else {
            this.nationalId = "00000000000";
        }
    }

    public String getNationalId() { // Exp: 1*********3
        return this.nationalId.charAt(0) + "*********" + this.nationalId.charAt(10);
    }
    public String getFullName() {
        return fullName;
    }
}
