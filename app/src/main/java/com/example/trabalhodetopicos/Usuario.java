package com.example.trabalhodetopicos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Usuario implements Serializable  {
    private long id;
    private String name;
    private String phone;
    private String email;
    private Date birth;
    private String gender;
    private ArrayList<String> interests;

    public Usuario() {
    }

    public Usuario(long id, String name, String phone, String email, Date birth, String gender, ArrayList<String> interests) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.interests = interests;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public ArrayList<String> getInterests() {
        return interests;
    }
    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return name + " : " + gender;
    }

}

