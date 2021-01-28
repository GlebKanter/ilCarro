package com.telran.ilcarro.qa26.model;

public class User {
    private  String fName;
    private  String lName;
    private  String email;
    private  String password;

    public User withfName(String fName) {
        this.fName = fName;
        return  this;
    }

    public User withlName(String lName) {
        this.lName = lName;
        return  this;
    }

    public User withEmail(String email) {
        this.email = email;
        return  this;
    }

    public User withPassword(String password) {
        this.password = password;
        return  this;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
