package model;

public class TakePartInCourse {
    private String studentID;
    private String classID;
    private String lecture;
    private int year;
    private int semester;
    private float score;

    public TakePartInCourse(){
        studentID = "";
        classID = "";
        lecture = "";
        year = 0;
        semester = 0;
        score = 0;
    }

    public TakePartInCourse(String studentID, String classID, String lecture, int year, int semester, float score){
        this.studentID = studentID;
        this.classID = classID;
        this.lecture = lecture;
        this.year = year;
        this.semester = semester;
        this.score = score;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

}
