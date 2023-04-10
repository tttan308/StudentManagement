package model;

public class Course {
    private String classID;
    private String name;
    private String lecture;
    private int year;
    private int semester;
    private String notes;
    private int credits;

    public Course(){
        this.classID = "";
        this.name = "";
        this.lecture = "";
        this.year = 0;
        this.semester = 0;
        this.notes = "";
        this.credits = 0;
    }

    public Course(String classID, String name, String lecture, int year, int semester, String notes, int credits){
        this.classID = classID;
        this.name = name;
        this.lecture = lecture;
        this.year = year;
        this.semester = semester;
        this.notes = notes;
        this.credits = credits;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
