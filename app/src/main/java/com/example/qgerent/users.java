package com.example.qgerent;

public class users {
    private String name;
    private String email;
    private String gender;
    private String phone;
    private String role;

    public users() {

    }

    public users(String name, String email, String gender, String phone, String role) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
