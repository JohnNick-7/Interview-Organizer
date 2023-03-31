package com.example.myproject;

import java.time.LocalDate;

public class user {
    String name,gender,city,language,education,course,keyskills,phone;
    String dob;

    public user(String name, String gender, String city, String language, String education, String course, String keyskills, String phone, String dob) {

        this.name = name;
        this.gender = gender;
        this.city = city;
        this.language = language;
        this.education = education;
        this.course = course;
        this.keyskills = keyskills;
        this.phone = phone;
        this.dob = dob;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getKeyskills() {
        return keyskills;
    }

    public void setKeyskills(String keyskills) {
        this.keyskills = keyskills;
    }


}

