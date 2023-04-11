package model;

public class ManageCourse {
    private String username;
    private String classID;
    private String lecture;
    private String year;
    private int semester;

    public ManageCourse(){
        username = "";
        classID = "";
        lecture = "";
        year = "";
        semester = 0;
    }

    public ManageCourse(String username, String classID, String lecture, String year, int semester){
        this.username = username;
        this.classID = classID;
        this.lecture = lecture;
        this.year = year;
        this.semester = semester;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
