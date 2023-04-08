package model;

import java.sql.Date;

public class Student {
    private String id;
    private String name;
    private float grade; //GPA
    private Date birthday;
    private String address;
    private String notes;

    public Student() {
        this.id = "";
        this.name = "";
        this.grade = 0;
        this.birthday = null;
        this.address = "";
        this.notes = "";
    }

    public Student(String id, String name, float grade, Date birthday, String address, String notes){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.birthday = birthday;
        this.address = address;
        this.notes = notes;
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
