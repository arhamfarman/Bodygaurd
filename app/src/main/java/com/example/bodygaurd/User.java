package com.example.bodygaurd;

public class User {
    public String firstName, lastName, email;
    public User(){

    }

    public  User(String email,String firstName, String lastName ){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }
}
