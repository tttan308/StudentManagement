package model;

public class ManageStudent {
    private String username;
    private String id; //id of student

    public ManageStudent() {
        this.username = "";
        this.id = "";
    }

    public ManageStudent(String username, String id) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
