package database;

import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO implements DAOInterface<Student>{
    @Override
    public ArrayList selectAll() {
        ArrayList<Student> ketQua = new ArrayList<Student>();
        try{
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=STUDENT_MANAGEMENT;user=sa;password=123;trustServerCertificate=true");
            System.out.println("Success!");
            if(con != null){
                    String sql = "SELECT * FROM STUDENT";
                PreparedStatement st = con.prepareStatement(sql);

                ResultSet rs = st.executeQuery();
                System.out.println(rs);
                while(rs.next()){
                    String id = rs.getString("IDSTU");
                    String name = rs.getString("NAME");
                    float grade = rs.getFloat("GRADE");
                    Date birthday = rs.getDate("BIRTHDAY");
                    String address = rs.getString("ADDRESS");
                    String notes = rs.getString("NOTES");

                    Student stu = new Student(id, name, grade, birthday, address, notes);
                    ketQua.add(stu);
                }

                con.close();
            }
            else System.out.println("Not Success!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int insert(Student s) {
        return 0;
    }

    @Override
    public int delete(Student s) {
        return 0;
    }

    @Override
    public int update(Student s) {
        return 0;
    }

    public static void main(String args[]){
        StudentDAO student = new StudentDAO();
        ArrayList<Student> stu = student.selectAll();
        for(Student s : stu){
            System.out.println(s.getId() + " " + s.getName() + " " + s.getGrade() + " " + s.getBirthday() + " " + s.getAddress() + " " + s.getNotes());
        }
    }
}
