package model;

public class Course {
    private String classID;
    private String name;
    private String lecture;
    private int year;
    private String semester;
    private String notes;
    private int credits;

    public Course(String classID, String name, String lecture, int year, String semester, String notes, int credits){
        this.classID = classID;
        this.name = name;
        this.lecture = lecture;
        this.year = year;
        this.semester = semester;
        this.notes = notes;
        this.credits = credits;
    }
}
